//
//  BeagleTitleStyle.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 02/04/2021.
//

import Foundation
import Beagle

struct Styles {
    static func titleHelloStyle() -> (UITextView?) -> Void {
        return {
            $0?.font = UIFont.systemFont(ofSize: 20, weight: .semibold)
            $0?.textContainer.maximumNumberOfLines = 2
            $0?.tintColor = .white
        }
    }
}
