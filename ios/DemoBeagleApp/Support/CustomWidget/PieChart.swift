//
//  PieChart.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/9/21.
//

import Foundation
import Beagle
import UIKit
import Charts

struct PieChart: ServerDrivenComponent {
    var dataset: [[String]]
    var valueTextColor: String
    var valueTextSize: Float
    var sliceSpace: Float
    var isHoleNeeded: Bool
    var holeRadius: Float
    var holeColor: String
    var holeText: String
    var holeTextColor: String
    var holeTextSize: Float
    var width: Int
    var height: Int
    
    func toView(renderer: BeagleRenderer) -> UIView {
        return configPieChart()
    }
    
    func configPieChart() -> PieChartView {
        let pieChart = PieChartView()
        
        pieChart.frame.size.width = CGFloat(width)
        pieChart.frame.size.height = CGFloat(height)

        let legend = pieChart.legend
        legend.enabled = false
        
        pieChart.usePercentValuesEnabled = true
        pieChart.drawSlicesUnderHoleEnabled = false
        pieChart.holeRadiusPercent = 0.58
        pieChart.transparentCircleRadiusPercent = 0.61
        pieChart.chartDescription!.enabled = false
        pieChart.setExtraOffsets(left: 5, top: 10, right: 5, bottom: 5)
        pieChart.drawCenterTextEnabled = true
        
        let paragraphStyle = NSParagraphStyle.default.mutableCopy() as! NSMutableParagraphStyle
        paragraphStyle.lineBreakMode = .byTruncatingTail
        paragraphStyle.alignment = .center
        
        let centerText = NSMutableAttributedString(string: "100 phản ánh")
        pieChart.centerAttributedText = centerText;
        pieChart.rotationAngle = 0
        pieChart.rotationEnabled = true
        pieChart.highlightPerTapEnabled = true
        
        let entries =  dataset.map { data -> (PieChartDataEntry) in
            print(data[0])
            return (PieChartDataEntry(value: Double(data[0])!))
        }
        
        let colors = dataset.map{data -> (UIColor) in
            return UIColor(hex: data[1])!
        }
        
        
        let set = PieChartDataSet(entries: entries)
        set.sliceSpace = CGFloat(self.sliceSpace)
        set.colors = colors
            
        let data = PieChartData(dataSet: set)
        let pFormatter = NumberFormatter()
        pFormatter.numberStyle = .percent
        pFormatter.maximumFractionDigits = 1
        pFormatter.multiplier = 1
        pFormatter.percentSymbol = "%"
        data.setValueFormatter(DefaultValueFormatter(formatter: pFormatter))
        
        data.setValueFont(.systemFont(ofSize: 11, weight: .light))
        data.setValueTextColor(.white)
        pieChart.data = data
        pieChart.highlightValue(nil)
        pieChart.animate(xAxisDuration: 1.4, easingOption: .easeOutBack)
        return pieChart
    }
}
