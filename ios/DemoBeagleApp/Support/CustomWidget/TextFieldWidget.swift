//
//  TextField.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/16/21.
//

import Foundation
import Beagle
import UIKit

struct TextFieldWidget: ServerDrivenComponent {
    var placeHolder: String?
    var height: Int?
    var isRequiredTextField: Bool?
    var isAlwaysVisibleClearButton: Bool?

    public var text: Expression<String>?
    public var onChange: [Action]?
    
    
    func toView(renderer: BeagleRenderer) -> UIView {
        let customtTextField = CustomTextField(self, renderer: renderer)
        customtTextField.frame.size.height = CGFloat(120)
        customtTextField.placeholder = placeHolder ?? ""
        customtTextField.isRequredTextField = isRequiredTextField ?? false
        customtTextField.isAlwaysVisibleClearButton = isAlwaysVisibleClearButton ?? false
        customtTextField.configTextField()
        
        return customtTextField
    }
}

extension TextFieldWidget {
    enum CodingKeys: String, CodingKey {
        case text
        case onChange
        case placeHolder
        case height
        case isRequiredTextField
        case isAlwaysVisibleClearButton
    }
    
    public init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        text = try container.decodeIfPresent(Expression<String>.self,forKey: .text)
        onChange = try container.decodeIfPresent(forKey: .onChange)
        placeHolder = try container.decodeIfPresent(String.self,forKey: .placeHolder)
        height = try container.decodeIfPresent(Int.self,forKey: .height)
        isRequiredTextField = try container.decodeIfPresent(Bool.self,forKey: .isRequiredTextField)
        isAlwaysVisibleClearButton = try container.decodeIfPresent(Bool.self,forKey: .isAlwaysVisibleClearButton)
    }
}
