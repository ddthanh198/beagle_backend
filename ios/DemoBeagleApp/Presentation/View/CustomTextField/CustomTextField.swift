//
//  CustomTextField.swift
//  govapp
//
//  Created by Apple on 12/24/20.
//

import UIKit
import Beagle

@objc protocol CustomTextFieldDelegate: class {
    @objc optional func textFieldDidEndEditing(_ textField: CustomTextField)
    @objc optional func textFieldDidBeginEditing(_ textField: CustomTextField)
    @objc optional func textFieldShouldClear(_ textField: CustomTextField) -> Bool
    @objc optional func textFieldShouldReturn(_ textField: CustomTextField) -> Bool
    @objc optional func textFieldShouldEndEditing(_ textField: CustomTextField) -> Bool
    @objc optional func textFieldShouldBeginEditing(_ textField: CustomTextField) -> Bool
    @objc optional func textFieldDidEndEditing(_ textField: CustomTextField, reason: UITextField.DidEndEditingReason)
    @objc optional func valueChanged(_ textField: CustomTextField)
    @objc optional func textField(_ textField: CustomTextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool
}

class CustomTextField: UIView {
    // MARK: - UI Properties
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var placeholderTitleLabel: UILabel!
    @IBOutlet weak var placeholderTitleView: UIView!
    @IBOutlet weak var functionButton: UIButton!
    @IBOutlet weak var errorMessageLabel: UILabel!
    @IBOutlet weak var errorMessageHeightConstraint: NSLayoutConstraint!
    
    // MARK: - Stored Properties
    enum Constants {
        static let ERROR_MESSAGE_MAX_HEIGHT = CGFloat(40)
        static let ERROR_MESSAGE_MIN_HEIGHT = CGFloat(20)
        
        static let PASSWORD_LENGTH = 8
    }
    
    var text: String? {
        get {
            return self.textField.text
        }
        set {
            setPlaceholderTitleStatus(isHidden: (newValue ?? "").isEmpty)
            self.textField.text = newValue
        }
    }
    var component: TextFieldWidget?
    var controller: BeagleController?
    
    var onClick: (() -> Void)?
    var placeholder = ""
    var isPasswordTextField = false
    var isPhoneNumberTextField = false
    var isNewPasswordTextField = false
    var functionImage: UIImage? = nil
    var functionFocusedImage: UIImage? = nil
    var isEnabled = true
    var customDelegate: CustomTextFieldDelegate? = nil
    var isAlwaysVisibleClearButton = false
    var returnKeyType: UIReturnKeyType = .default
    var isRequredTextField = false
    var textContentType: UITextContentType? = nil
    var isInvalidRegex = false
    var errorMessage: String = "" {
        didSet {
            self.errorMessageLabel.text = errorMessage
        }
    }
    
    // MARK: - Init
    override init (frame: CGRect) {
        super.init(frame: frame)
        commitInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commitInit()
    }
    
    init(_ component: TextFieldWidget, renderer: BeagleRenderer) {
        super.init(frame: .zero)
        commitInit()
         self.component = component
         self.controller = renderer.controller
        textField.addTarget(self, action: #selector(valueChanged), for: .editingChanged)
         renderer.observe(component.text, andUpdate: \.text, in: self)
     }

    
    // MARK: - Actions
    @IBAction func clearButtonAction(_ sender: Any) {
        if let onClick = self.onClick {
            onClick()
        }
    }
    
    // MARK: - Functions
    private func commitInit() {
        Bundle.main.loadNibNamed("CustomTextField", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        configTextField()
    }
    
    func configTextField() {
        setPlaceholderTitleStatus(isHidden: true)
        placeholderTitleLabel.attributedText = getPlaceholderAttributedTitle()
        errorMessageHeightConstraint.constant = isNewPasswordTextField ? Constants.ERROR_MESSAGE_MAX_HEIGHT : Constants.ERROR_MESSAGE_MIN_HEIGHT
        errorMessageLabel.isHidden = true
        
        textField.delegate = self
        textField.returnKeyType = returnKeyType
        if let `textContentType` = self.textContentType {
            textField.textContentType = textContentType
        }
        
        //Change this properties to change the propperties of text
        textField.attributedPlaceholder = self.getAttributedPlaceholder()
        
        textField.isSecureTextEntry = isPasswordTextField
        textField.isEnabled = isEnabled
        textField.clearButtonMode = .whileEditing
        functionButton.isHidden = true
        
        if isPhoneNumberTextField {
            textField.keyboardType = .numberPad
        }
        
        if onClick != nil, isAlwaysVisibleClearButton {
            functionButton.isHidden = false
            guard let clearButton = textField.value(forKey: "_clearButton") as? UIButton else { return }
            clearButton.setImage(UIImage(named: "ic_blank_clear_button"), for: .normal)
            clearButton.setImage(UIImage(named: "ic_blank_clear_button"), for: .focused)
            clearButton.setImage(UIImage(named: "ic_blank_clear_button"), for: .highlighted)
            clearButton.isEnabled = false
            
            functionButton.setImage(functionImage?.withRenderingMode(.alwaysOriginal), for: .normal)
            functionButton.setImage(functionFocusedImage?.withRenderingMode(.alwaysOriginal), for: .focused)
            functionButton.setImage(functionFocusedImage?.withRenderingMode(.alwaysOriginal), for: .highlighted)
            functionButton.removeTarget(nil, action: nil, for: .allEvents)
            functionButton.addTarget(self, action: #selector(functionButtonClicked), for: .touchUpInside)
        }
        
        textField.addTarget(self, action: #selector(valueChanged), for: .editingChanged)
    }
    
    @objc func valueChanged() {
        guard let dlg = customDelegate else { return }
        dlg.valueChanged?(self)
    }
    
    func reSetupSecureTextEntry() {
        textField.isSecureTextEntry = isPasswordTextField
    }
    
    func setErrorMessage(isHidden: Bool) {
        errorMessageLabel.isHidden = isHidden
    }
    
    func reSetupFunctionImage() {
        guard let clearButton = textField.value(forKey: "_clearButton") as? UIButton else { return }
        clearButton.setImage(UIImage(named: "ic_blank_clear_button"), for: .normal)
        clearButton.setImage(UIImage(named: "ic_blank_clear_button"), for: .focused)
        clearButton.setImage(UIImage(named: "ic_blank_clear_button"), for: .highlighted)
        
        functionButton.setImage(functionImage?.withRenderingMode(.alwaysOriginal), for: .normal)
        functionButton.setImage(functionFocusedImage?.withRenderingMode(.alwaysOriginal), for: .focused)
        functionButton.setImage(functionFocusedImage?.withRenderingMode(.alwaysOriginal), for: .highlighted)
    }
    
    @objc func functionButtonClicked() {
        guard let `onClick` = self.onClick else { return }
        onClick()
    }
    
    private func getPlaceholderAttributedTitle() -> NSAttributedString {
        let attributes: [NSAttributedString.Key:Any] = [
            .foregroundColor: #colorLiteral(red: 0.2078431373, green: 0.2156862745, blue: 0.2235294118, alpha: 1),
            .font: UIFont.systemFont(ofSize: 14, weight: .semibold)
        ]
        let requireAttibutes: [NSAttributedString.Key:Any] = [
            .foregroundColor: #colorLiteral(red: 1, green: 0.1491314173, blue: 0, alpha: 1),
            .font: UIFont.systemFont(ofSize: 14, weight: .semibold)
        ]
        let placeholderAttributedTitle = NSMutableAttributedString(string: placeholder, attributes: attributes)
        if isRequredTextField {
            placeholderAttributedTitle.append(NSAttributedString(string: "*", attributes: requireAttibutes))
        }
        return placeholderAttributedTitle
    }
    
    private func getAttributedPlaceholder() -> NSAttributedString {
        let attributes: [NSAttributedString.Key:Any] = [
            .foregroundColor: #colorLiteral(red: 0.3803921569, green: 0.4705882353, blue: 0.5098039216, alpha: 1),
            .font: UIFont.systemFont(ofSize: 16, weight: .regular)
        ]
        let requireAttibutes: [NSAttributedString.Key:Any] = [
            .foregroundColor: #colorLiteral(red: 1, green: 0.1491314173, blue: 0, alpha: 1),
            .font: UIFont.systemFont(ofSize: 16, weight: .semibold)
        ]
        let attributedPlaceholder = NSMutableAttributedString(string: placeholder, attributes: attributes)
        if isRequredTextField {
            attributedPlaceholder.append(NSAttributedString(string: "*", attributes: requireAttibutes))
        }
        return attributedPlaceholder
    }
    
    private func setPlaceholderTitleStatus(isHidden: Bool) {
        UIView.animate(withDuration: 0.5) {
            self.placeholderTitleView.isHidden = isHidden
            self.placeholderTitleView.layer.opacity = isHidden ? 0 : 1
            self.textField.attributedPlaceholder = !isHidden ? NSAttributedString(string: "") : self.getAttributedPlaceholder()
        }
    }
}

extension CustomTextField: UITextFieldDelegate {
    func textFieldDidEndEditing(_ textField: UITextField) {
        guard let dlg = customDelegate else { return }
        dlg.textFieldDidEndEditing?(self)
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        guard let dlg = customDelegate else { return }
        dlg.textFieldDidBeginEditing?(self)
    }
    
    func textFieldShouldClear(_ textField: UITextField) -> Bool {
        guard let dlg = customDelegate else { return true }
        return dlg.textFieldShouldClear?(self) ?? true
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        guard let dlg = customDelegate else { return true }
        return dlg.textFieldShouldReturn?(self) ?? true
    }
    
    func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
        setPlaceholderTitleStatus(isHidden: (text ?? "").isEmpty)
        guard let dlg = customDelegate else { return true }
        return dlg.textFieldShouldEndEditing?(self) ?? true
    }
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        setPlaceholderTitleStatus(isHidden: false)
        guard let dlg = customDelegate else { return true }
        return dlg.textFieldShouldBeginEditing?(self) ?? true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField, reason: UITextField.DidEndEditingReason) {
        guard let dlg = customDelegate else { return }
        dlg.textFieldDidEndEditing?(self, reason: reason)
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        guard let dlg = customDelegate else { return true }
        return dlg.textField?(self, shouldChangeCharactersIn: range, replacementString: string) ?? true
    }
    
    override func becomeFirstResponder() -> Bool {
        return textField.becomeFirstResponder()
    }
    
    override func resignFirstResponder() -> Bool {
        return textField.resignFirstResponder()
    }
}

