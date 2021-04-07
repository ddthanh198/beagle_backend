//
//  DismissDialogAction.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/6/21.
//

import Foundation
import Beagle
import UIKit

class DismissDialogAction: Action {
    
    let endpoint: String
    
    init(endpoint: String) {
        self.endpoint = endpoint
    }
    
    func execute(controller: BeagleController, origin: UIView) {
        controller.dismiss(animated: true, completion: nil)
    }
    
}
