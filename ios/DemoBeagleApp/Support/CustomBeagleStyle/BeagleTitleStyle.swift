//
//  BeagleTitleStyle.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 02/04/2021.
//

import Foundation
import Beagle

struct Styles {
    static func titleTextStyle() -> (UITextView?) -> Void {
            return BeagleStyle.text(
                font: UIFont.systemFont(ofSize: 20),
                color: .white
            )
        }
        
    static func descriptionTextStyle() -> (UITextView?) -> Void {
        return BeagleStyle.text(
            font: UIFont.systemFont(ofSize: 15),
            color: .white
        )
    }
}
