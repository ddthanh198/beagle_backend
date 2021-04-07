//
//  ShowBottomSheetAction.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/6/21.
//

import Foundation
import Beagle
import UIKit
import FittedSheets

class ShowBottomSheetAction: Action {
    
    let endpoint: String
    let numberOfItems: Int?
    let heightConstants = 120
    let heightRow = 50
    
    init(endpoint: String, numberOfItems: Int?) {
        self.endpoint = endpoint
        self.numberOfItems = numberOfItems
    }
    
    func execute(controller: BeagleController, origin: UIView) {
        let bottomSheetBeagle = BottomSheetViewController()
        guard let numberOfItems = numberOfItems else { return }
        let height = self.heightConstants + self.heightRow * numberOfItems
        let bottomSheetVC = SheetViewController(controller: bottomSheetBeagle, sizes: [.fixed(CGFloat(height))], options: SheetOptions(useInlineMode: false))

        controller.present(bottomSheetVC, animated: true, completion: nil)
    }
}
