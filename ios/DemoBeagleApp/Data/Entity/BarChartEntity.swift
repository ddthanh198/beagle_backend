//
//  BarChartEntity.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/8/21.
//

import Foundation

class BarchartEntity: Decodable {
    var x: Double
    var y: Double
    var icon: String?
    var title: String
    var color: String
}

class StyleModel: Decodable {
    var size: Size
}

class Size: Decodable {
    var width: Width
    var height: Height
}

class Width: Decodable {
    var value: Double
}

class Height: Decodable {
    var value: Double
}

class RBGColorModel: Decodable {
    var red: Int
    var green: Int
    var blue: Int
    var alpha: Int
}

class BarChartDataItem: Decodable {
    var barchartDataSet: [BarchartEntity]
    var isEnableLeftAxis: Bool
    var isEnableRightAxis: Bool
    var isEnableXAxis: Bool
    var prefix: String
    var title: String
    
}

class BarChartViewChildren: Decodable {
    var barChartData: BarChartDataItem
    var style: StyleModel
}

class BarChartViewEntity: Decodable {
    var children: [BarChartViewChildren]
}
