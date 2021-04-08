//
//  OpenSideMenuViewController.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 03/04/2021.
//

import Foundation
import Beagle
import SideMenu

enum Gravity: String {
    case start = "START"
    case end = "END"
}

class OpenSideMenuViewController: Action {
    private var url = ""
    private var gravity = ""
    
    init(url: String, gravity: String) {
        self.url = url
        self.gravity = gravity
    }
    
    func execute(controller: BeagleController, origin: UIView) {
        let beagleScreen = Beagle.screen(.remote(.init(url: url)))
        let sideMenuViewController = SideMenuNavigationController(rootViewController: beagleScreen)
        sideMenuViewController.presentationStyle = .menuSlideIn
        guard let gravityType = Gravity(rawValue: gravity) else { return }
        sideMenuViewController.leftSide = gravityType == .end
        controller.present(sideMenuViewController, animated: true, completion: nil)
    }
}
