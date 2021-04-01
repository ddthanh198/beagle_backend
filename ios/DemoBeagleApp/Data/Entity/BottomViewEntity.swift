//
//  BottomViewEntity.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation

class Children: Decodable {
    var menuItems: [[String]]
    var selectedColor: String
    var unselectedColor: String
}

class BottomViewEntity: Decodable {
    var children: [Children]
}
