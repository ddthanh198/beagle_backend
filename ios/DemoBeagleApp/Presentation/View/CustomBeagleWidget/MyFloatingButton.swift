//
//  MyFloatingButton.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 09/04/2021.
//

import Foundation
import Beagle
import UIKit
import SDWebImage

class MyFloatingButton: ServerDrivenComponent {
    let image: String
    let backgroundColor: String
    
    func toView(renderer: BeagleRenderer) -> UIView {
        let button = UIButton()
        button.backgroundColor = UIColor(hex: backgroundColor)
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.shadowOffset = CGSize(width: 0, height: 0)
        button.layer.shadowOpacity = 0.6
        button.layer.shadowRadius = 4.0
        button.anchor(widthConstant: 44, heightConstant: 44)
        button.layer.cornerRadius = 22
        button.addTarget(self, action: #selector(showDatePicker), for: .touchUpInside)
        button.setImage(UIImage(named: image), for: .normal)
        button.contentVerticalAlignment = .fill
        button.contentHorizontalAlignment = .fill
        button.imageEdgeInsets = UIEdgeInsets(top: 12, left: 12, bottom: 12, right: 12)
        return button
    }
    
    @objc func showDatePicker() {
        print("Minh Mon")
    }
}
