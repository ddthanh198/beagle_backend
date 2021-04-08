//
//  ShowDialogAction.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/6/21.
//

import Foundation
import UIKit
import Beagle

class ShowDialogAction: Action {
    
    let endpoint: String
    let numberOfItems: Int?
    
    
    init(endpoint: String, numberOfItems: Int?) {
        self.endpoint = endpoint
        self.numberOfItems = numberOfItems
    }
    
    func execute(controller: BeagleController, origin: UIView) {
        let dialogController = DialogLanguageViewController()
        dialogController.numberOfItems = numberOfItems
        dialogController.providesPresentationContextTransitionStyle = true
        dialogController.definesPresentationContext = true
        dialogController.modalPresentationStyle = UIModalPresentationStyle.overCurrentContext
        dialogController.modalTransitionStyle = UIModalTransitionStyle.crossDissolve
        controller.present(dialogController, animated: true, completion: nil)
        
    }
}
