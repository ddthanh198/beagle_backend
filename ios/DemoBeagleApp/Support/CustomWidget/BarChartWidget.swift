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

struct BarChartWidget: ServerDrivenComponent {
    
    var barchartDataSet: [BarchartEntity]
    var isEnableLeftAxis: Bool
    var isEnableRightAxis: Bool
    var isEnableXAxis: Bool
    var prefix: String
    var width: Int
    var height: Int
    
    func toView(renderer: BeagleRenderer) -> UIView {
        return configBarChart()
    }
    
    func configBarChart() -> BarChartView {
      
        let barChartEntities = barchartDataSet
        let yVals = barChartEntities.map {barChartEntity -> BarChartDataEntry in
            return BarChartDataEntry(x: barChartEntity.x, y: barChartEntity.y, icon: nil)
        }
        
        let barChart = BarChartView()
        barChart.frame.size.width = CGFloat(width)
        barChart.frame.size.height = CGFloat(height)
    
        barChart.drawBarShadowEnabled = false
        barChart.drawValueAboveBarEnabled = false
        
        barChart.maxVisibleCount = 60
        
        let xAxis = barChart.xAxis
        xAxis.labelPosition = .bottom
        xAxis.labelFont = .systemFont(ofSize: 10)
        xAxis.granularity = 1
        xAxis.labelCount = 7
        xAxis.enabled = isEnableXAxis
        
        let leftAxisFormatter = NumberFormatter()
        leftAxisFormatter.minimumFractionDigits = 0
        leftAxisFormatter.maximumFractionDigits = 1
        leftAxisFormatter.negativeSuffix = prefix
        leftAxisFormatter.positiveSuffix =  prefix
        
        let leftAxis = barChart.leftAxis
        leftAxis.labelFont = .systemFont(ofSize: 10)
        leftAxis.labelCount = 8
        leftAxis.valueFormatter = DefaultAxisValueFormatter(formatter: leftAxisFormatter)
        leftAxis.labelPosition = .outsideChart
        leftAxis.spaceTop = 0.15
        leftAxis.axisMinimum = 0 // FIXME: HUH?? this replaces startAtZero = YES
        
        let rightAxis = barChart.rightAxis
        rightAxis.enabled = true
        rightAxis.labelFont = .systemFont(ofSize: 10)
        rightAxis.labelCount = 8
        rightAxis.valueFormatter = leftAxis.valueFormatter
        rightAxis.spaceTop = 0.15
        rightAxis.axisMinimum = 0
        
        leftAxis.enabled = isEnableLeftAxis
        rightAxis.enabled = isEnableRightAxis
        
        let legend = barChart.legend
        legend.enabled = false
        
        var set1: BarChartDataSet! = nil
        set1 = BarChartDataSet(entries: yVals, label: "BarChart")
        set1.colors = barChartEntities.map {entity -> UIColor in
            return UIColor(hex: entity.color)!
        }
        set1.drawValuesEnabled = false
        
        let data = BarChartData(dataSet: set1)
        data.setValueFont(UIFont(name: "HelveticaNeue-Light", size: 10)!)
        data.barWidth = 0.9
        
        barChart.data = data
        
        return barChart
    }
}
