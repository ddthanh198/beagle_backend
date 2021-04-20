//
//  BarChartEntity.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/8/21.
//

import Foundation

class BarchartEntity: Decodable {
    var x: Float
    var y: [Float]
    var icon: String?
    var title: String
    var color: [String]
}

class BarChartDataItem: Decodable {
    var barchartDataSet: [BarchartEntity]
    var isEnableLeftAxis: Bool
    var isEnableRightAxis: Bool
    var isEnableXAxis: Bool
    var prefix: String
    var title: String
}

class HorizontalAxisMetaData: Decodable {
    var isXAxisEnabled: Bool?
    var axisPosition: String
    var textSize: Float
    var textColor: String
    var displayTitleAsLabel: Bool
    var drawGridLinesEnabled: Bool
    var drawAxisLineEnabled: Bool
}

class RightAxisMetaData: Decodable {
    var isRightAxisEnabled: Bool?
    var textSize: Float?
    var textColor: String?
    var disPlayUnit: Bool?
    var drawGridLinesEnabled: Bool
    var drawAxisLineEnabled: Bool
}

class LeftAxisMetaData: Decodable {
    var isLeftAxisEnabled: Bool?
    var textSize: Float?
    var textColor: String?
    var displayUnit: Bool?
    var drawGridLinesEnabled: Bool
    var drawAxisLineEnabled: Bool
}

enum XAxisPosition: String {
    case TOP = "TOP"
    case BOTTOM = "BOTTOM"
    case BOTH_SIDED = "BOT_SIDED"
    case TOP_INSIDE = "TOP_INSIDE"
    case BOTTOM_INSIDE = "BOTTOM_INSIDE"
}


