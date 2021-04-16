//
//  BottomViewEntity.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation

class TabItem: Decodable {
    var cacheFile: String
    var remoteIconUrl: String
    var api: String
    var title: String
}

class Children: Decodable {
    var tabItems: [TabItem]
    var selectedColor: String
    var unselectedColor: String
}

class BottomViewEntity: Decodable {
    var children: [Children]
}
