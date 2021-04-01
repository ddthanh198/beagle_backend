//
//  GetBottomAPI.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation

class GetBottomAPI: BaseAPI<BottomViewEntity> {
    override func path() -> String {
        return Urls.GET_BOTTOM_NAVIGATION_CONFIG_URL
    }
}
