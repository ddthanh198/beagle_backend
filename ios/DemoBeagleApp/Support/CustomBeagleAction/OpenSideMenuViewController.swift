//
//  OpenSideMenuViewController.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 03/04/2021.
//

import Foundation
import Beagle
import SideMenu

class OpenSideMenuViewController: Action {
    private var url = ""
    
    init(url: String) {
        self.url = url
    }
    
    func execute(controller: BeagleController, origin: UIView) {
        let beagleScreen = Beagle.screen(.remote(.init(url: url)))
        let sideMenuViewController = SideMenuNavigationController(rootViewController: beagleScreen)
        sideMenuViewController.presentationStyle = .menuSlideIn
        controller.present(sideMenuViewController, animated: true, completion: nil)
    }
}
