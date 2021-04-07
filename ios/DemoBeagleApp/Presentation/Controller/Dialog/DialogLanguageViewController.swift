//
//  DialogLanguageViewController.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/6/21.
//

import UIKit
import Beagle

class DialogLanguageViewController: UIViewController {
    
    let dialogLanguage = BeagleScreenViewController(.remote(.init(url: "\(Urls.HOST)/widgetController/selectLanguageDialog")))
    var numberOfItems: Int? = nil
    
    enum Constants {
        static let marginWidth = 140
        static let height = 100
        static let heightRow = 44
        static let rounderView = 16
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureView()
    }
    
    func configureView() {
        self.view.backgroundColor = UIColor.black.withAlphaComponent(0.1)
        addChild(dialogLanguage)
        guard let dialogLanguageView = dialogLanguage.view, let numberOfItems = numberOfItems else { return }
        self.view.addSubview(dialogLanguageView)
        
        
        let (heightDialogView, widthDialogView, topConstaintDialogView, leftConstaintDialogView) = getSizeOfDialogView(numberOfItems: numberOfItems)
        
        print("numberOfItems \(numberOfItems),\(heightDialogView),\(topConstaintDialogView)")
        dialogLanguageView.anchor(top: self.view.topAnchor, left: self.view.leftAnchor, topConstant:topConstaintDialogView, leftConstant: leftConstaintDialogView, widthConstant: widthDialogView, heightConstant: heightDialogView)
        
        dialogLanguageView.layer.masksToBounds = true
        dialogLanguageView.layer.cornerRadius = CGFloat(Constants.rounderView)
        
        dialogLanguage.didMove(toParent: self)
    }
    
    func getSizeOfDialogView(numberOfItems: Int) -> (CGFloat, CGFloat, CGFloat,CGFloat){
        let heightDialogView = (CGFloat(Constants.height + Constants.heightRow * numberOfItems) <= self.view.frame.height - CGFloat(100)) ? CGFloat(Constants.height + Constants.heightRow * numberOfItems) : self.view.frame.height - CGFloat(100)
        let widthDialogView = self.view.frame.width - CGFloat(Constants.marginWidth)
        let topConstaintDialogView = CGFloat(self.view.frame.height/2) - heightDialogView/2
        let leftConstaintDialogView = CGFloat(self.view.frame.width/2) - widthDialogView/2
        return (heightDialogView, widthDialogView, topConstaintDialogView, leftConstaintDialogView)
    }
}
