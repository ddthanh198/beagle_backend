//
//  BottomSheetViewController.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/6/21.
//

import UIKit
import Beagle

class BottomSheetViewController: UIViewController {
    
    let bottomSheetVC = BeagleScreenViewController(.remote(.init(url: "\(Urls.HOST)/widgetController/bottomSheetDialog")))
 

    override func viewDidLoad() {
        super.viewDidLoad()
        configureView()
    }
    
    func configureView() {
        addChild(bottomSheetVC)
        guard let bottomSheetView = bottomSheetVC.view  else { return }
        self.view.addSubview(bottomSheetView)
        bottomSheetView.anchor(top: self.view.topAnchor, left: self.view.leftAnchor, bottom: self.view.bottomAnchor, right: self.view.rightAnchor,topConstant: 0, leftConstant: 0, bottomConstant: 0, rightConstant: 0)
        bottomSheetVC.didMove(toParent: self)
    }
}
