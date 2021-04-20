//
//  BarChartView.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/8/21.
//

import Foundation
import UIKit
import Beagle
import Charts

struct BarChart: ServerDrivenComponent {
    var isHorizontalBarChart: Bool
    var dataset: [BarchartEntity]
    var horizontalAxisMetadata: HorizontalAxisMetaData?
    var leftAxisMetadata: LeftAxisMetaData?
    var rightAxisMetadata: RightAxisMetaData?
    var unit: String?
    var barWidth: Float?
    var width: Int
    var height: Int
    
    func toView(renderer: BeagleRenderer) -> UIView {
        return configBarChart()
    }
    
    func configBarChart() -> BarChartView {
        let barChartEntities = dataset
        let yVals = barChartEntities.map {barChartEntity -> BarChartDataEntry in
            return BarChartDataEntry(x: Double(barChartEntity.x), yValues: barChartEntity.y.map { return Double($0)})
        }
        
        var chartDataSet: BarChartDataSet! = nil
        chartDataSet = BarChartDataSet(entries: yVals, label: "BarChart")
        
        let colors = barChartEntities.map{ entity -> [NSUIColor] in
            return entity.color.map{ return NSUIColor($0)}
        }
        chartDataSet.colors = [ChartColorTemplates.material()[0], ChartColorTemplates.material()[1], ChartColorTemplates.material()[2]]
        chartDataSet.drawValuesEnabled = false
        
        let barChartData = BarChartData(dataSet: chartDataSet)
        barChartData.setValueFont(UIFont(name: "HelveticaNeue-Light", size: 10)!)
        barChartData.barWidth = Double(barWidth!)
        
        if isHorizontalBarChart {
            return configHorizontalBarChart(barChartData: barChartData)
        }
        
        return configHorizontalBarChart(barChartData: barChartData)
    }
    
    func configHorizontalBarChart(barChartData: BarChartData) -> HorizontalBarChartView {
        let horizontalBarChart = HorizontalBarChartView()
        horizontalBarChart.frame.size.width = CGFloat(width)
        horizontalBarChart.frame.size.height = CGFloat(height)
        
        let xAxis = horizontalBarChart.xAxis
        xAxis.labelPosition = .bottom
        xAxis.labelFont = .systemFont(ofSize: 10)
        xAxis.granularity = 0.5
        xAxis.labelCount = 7
        xAxis.enabled = horizontalAxisMetadata?.isXAxisEnabled ?? true
        xAxis.drawGridLinesEnabled = horizontalAxisMetadata!.drawGridLinesEnabled
        xAxis.drawAxisLineEnabled = horizontalAxisMetadata!.drawAxisLineEnabled
        
        let leftAxisFormatter = NumberFormatter()
        leftAxisFormatter.minimumFractionDigits = 0
        leftAxisFormatter.maximumFractionDigits = 1
        leftAxisFormatter.negativeSuffix = unit
        leftAxisFormatter.positiveSuffix =  unit
        
        let leftAxis = horizontalBarChart.leftAxis
        leftAxis.labelFont = UIFont.systemFont(ofSize: CGFloat(leftAxisMetadata!.textSize!))
        leftAxis.labelCount = 8
        leftAxis.valueFormatter = DefaultAxisValueFormatter(formatter: leftAxisFormatter)
        leftAxis.labelPosition = .outsideChart
        leftAxis.spaceTop = 0.15
        leftAxis.axisMinimum = 0 // FIXME: HUH?? this replaces startAtZero = YES
        leftAxis.enabled = leftAxisMetadata?.isLeftAxisEnabled ?? true
        leftAxis.labelTextColor = UIColor(hex:leftAxisMetadata!.textColor!)!
        leftAxis.drawGridLinesEnabled = leftAxisMetadata!.drawGridLinesEnabled
        leftAxis.drawAxisLineEnabled = leftAxisMetadata!.drawAxisLineEnabled
        
        let rightAxis = horizontalBarChart.rightAxis
        rightAxis.enabled = rightAxisMetadata?.isRightAxisEnabled ??  true
        rightAxis.labelFont = UIFont.systemFont(ofSize: CGFloat(leftAxisMetadata!.textSize!))
        rightAxis.labelCount = 8
        rightAxis.valueFormatter = leftAxis.valueFormatter
        rightAxis.spaceTop = 0.15
        rightAxis.axisMinimum = 0
        rightAxis.labelTextColor = UIColor(hex: rightAxisMetadata!.textColor!)!
        rightAxis.drawGridLinesEnabled = rightAxisMetadata!.drawGridLinesEnabled
        rightAxis.drawAxisLineEnabled = rightAxisMetadata!.drawAxisLineEnabled
        
        horizontalBarChart.drawBarShadowEnabled = false
        horizontalBarChart.drawValueAboveBarEnabled = false
        
        horizontalBarChart.maxVisibleCount = 60
        let legend = horizontalBarChart.legend
        legend.enabled = false
        horizontalBarChart.data = barChartData
        horizontalBarChart.animate(yAxisDuration: 1.5)
        return horizontalBarChart
    }
    
    func configBarChart(barChartData: BarChartData) -> BarChartView {
        let barChart = BarChartView()
        barChart.frame.size.width = CGFloat(width)
        barChart.frame.size.height = CGFloat(height)
        
        let xAxis = barChart.xAxis
        xAxis.labelPosition = .bottom
        xAxis.labelFont = .systemFont(ofSize: 10)
        xAxis.granularity = 0.5
        xAxis.labelCount = 7
        xAxis.enabled = horizontalAxisMetadata?.isXAxisEnabled ?? true
        xAxis.drawGridLinesEnabled = horizontalAxisMetadata!.drawGridLinesEnabled
        xAxis.drawAxisLineEnabled = horizontalAxisMetadata!.drawAxisLineEnabled
      //  xAxis.enabled = isEnableXAxis
        
        let leftAxisFormatter = NumberFormatter()
        leftAxisFormatter.minimumFractionDigits = 0
        leftAxisFormatter.maximumFractionDigits = 1
        leftAxisFormatter.negativeSuffix = unit
        leftAxisFormatter.positiveSuffix =  unit
    
        let leftAxis = barChart.leftAxis
        leftAxis.labelFont = UIFont.systemFont(ofSize: CGFloat(leftAxisMetadata!.textSize!))
        leftAxis.labelCount = 8
        leftAxis.valueFormatter = DefaultAxisValueFormatter(formatter: leftAxisFormatter)
        leftAxis.labelPosition = .outsideChart
        leftAxis.spaceTop = 0.15
        leftAxis.axisMinimum = 0 // FIXME: HUH?? this replaces startAtZero = YES
        leftAxis.enabled = leftAxisMetadata?.isLeftAxisEnabled ?? true
        leftAxis.labelTextColor = UIColor(hex:leftAxisMetadata!.textColor!)!
        leftAxis.drawGridLinesEnabled = leftAxisMetadata!.drawGridLinesEnabled
        leftAxis.drawAxisLineEnabled = leftAxisMetadata!.drawAxisLineEnabled
        
        let rightAxis = barChart.rightAxis
        rightAxis.enabled = rightAxisMetadata?.isRightAxisEnabled ??  true
        rightAxis.labelFont = UIFont.systemFont(ofSize: CGFloat(leftAxisMetadata!.textSize!))
        rightAxis.labelCount = 8
        rightAxis.valueFormatter = leftAxis.valueFormatter
        rightAxis.spaceTop = 0.15
        rightAxis.axisMinimum = 0
        rightAxis.labelTextColor = UIColor(hex: rightAxisMetadata!.textColor!)!
        rightAxis.drawGridLinesEnabled = rightAxisMetadata!.drawGridLinesEnabled
        rightAxis.drawAxisLineEnabled = rightAxisMetadata!.drawAxisLineEnabled
        
        barChart.drawBarShadowEnabled = false
        barChart.drawValueAboveBarEnabled = false
        
        barChart.maxVisibleCount = 60
        let legend = barChart.legend
        legend.enabled = false
        barChart.data = barChartData
        barChart.animate(yAxisDuration: 1.5)
        return barChart
    }

}


