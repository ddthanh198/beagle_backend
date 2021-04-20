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
    
    static func normalBoldTextStyle() -> (UITextView?) -> Void {
        return BeagleStyle.text(font: UIFont.systemFont(ofSize: 20, weight: .semibold), color: .black)
    }
    
    static func basicDialogTextStyle() -> (UITextView?) -> Void {
        return BeagleStyle.text(
            font: UIFont.systemFont(ofSize: 20, weight: .semibold), color: .black
        )
    }
    
    static func basicDialogContentStyle() -> (UITextView?) -> Void {
        return BeagleStyle.text(
            font: UIFont.systemFont(ofSize: 18),
            color: .gray
        )
    }
    
    static func acceptButtonDialogStyle() -> (UIButton?) -> Void {
        return BeagleStyle.button(withTitleColor: .white)
    }
    
    static func cancelButtonDialogStyle() -> (UIButton?) -> Void {
        return BeagleStyle.button(withTitleColor: .gray)
    }
}


