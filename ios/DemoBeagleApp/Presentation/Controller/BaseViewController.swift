//
//  BaseViewController.swift
//  govapp
//
//  Created by minhpv21 on 12/24/20.
//

import UIKit
import RxSwift
import RxCocoa
import SVProgressHUD

protocol RefreshDataDelegate: class {
    func reloadData()
}

class BaseViewController: UIViewController {
    var statusBarView: UIView?
    let disposeBag = DisposeBag()
    static var isNeedAuthenAgain = false
    static let isNeedReloadUnreadNotification = PublishSubject<Bool>()
    
    enum Constants {
        static let ERROR_AUTHEN_MESSAGE_FROM_SERVER = "Unauthenticated"
        static let ERROR_AUTHEN_MESSAGE = "Phiên đăng nhập đã hết hạn, bạn hãy đăng nhập lại để tiếp tục sử dụng"
        static let CANNOT_SEND_EMAIL = "Thiết bị này không thể gửi email"
        static let OK_DEFAULT_TITLE = "Đồng ý"
        static let COMMON_ERROR_MESSAGE = "Đã có lỗi xảy ra"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configStatusBar()
        SessionAppManager.shared.errorAuthorization.subscribe(onNext: { [weak self] (httpStatusCode, errorMessage, errorCodeName) in
            guard let `self` = self else { return }
            if httpStatusCode == 401, errorCodeName == nil {
                BaseViewController.isNeedAuthenAgain = true
                self.showAlert(message: errorMessage.isEmpty || errorMessage == Constants.ERROR_AUTHEN_MESSAGE_FROM_SERVER ? Constants.ERROR_AUTHEN_MESSAGE : errorMessage, okTitle: "Đăng nhập") {
                    //
                }
            } else {
                self.showErrorAlert(message: errorMessage.isEmpty ? Constants.COMMON_ERROR_MESSAGE : errorMessage)
            }
        }).disposed(by: disposeBag)
    }
    
    func onBackViewController() {
        if let navigationController = self.navigationController {
            navigationController.popViewController(animated: true)
        } else {
            self.dismiss(animated: true, completion: nil)
        }
    }
    
    func getAttributeTextColor(text: String, location: Int, length: Int, color: UIColor) -> NSAttributedString {
        let attributeText = NSMutableAttributedString(string: text)
        print(attributeText)
        attributeText.addAttribute(.foregroundColor, value: color, range: NSRange(location: location, length: length))
        
        return attributeText
    }
    
    func configStatusBar() {
        // Set status bar color
        var statusBarHeight: CGFloat = 0
        if #available(iOS 13.0, *) {
            let window = UIApplication.shared.windows.filter {$0.isKeyWindow}.first
            statusBarHeight = window?.windowScene?.statusBarManager?.statusBarFrame.height ?? 0
        } else {
            statusBarHeight = UIApplication.shared.statusBarFrame.height
        }
        statusBarView = UIView(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: statusBarHeight))
        statusBarView?.backgroundColor = #colorLiteral(red: 0.05882352941, green: 0.5568627451, blue: 0.4392156863, alpha: 1)
        guard let statusBarViewTempt = statusBarView else { return }
        self.view.addSubview(statusBarViewTempt)
    }
    
    func setStatusBarColor(color: UIColor) {
        self.statusBarView?.backgroundColor = color
    }
    
    func removeStatusBar() {
        self.statusBarView?.removeFromSuperview()
    }
    
    func showErrorAlert(error: Error, okTitle: String = Constants.OK_DEFAULT_TITLE, completion: (() -> Void)? = nil) {
        let alert = UIAlertController(title: "Lỗi", message: error.localizedDescription, preferredStyle: .alert)
        let okAction = UIAlertAction(title: okTitle, style: .default) { _ in
            if completion != nil { completion!() }
            alert.dismiss(animated: true, completion: nil)
        }
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    func showErrorAlert(message: String, okTitle: String = Constants.OK_DEFAULT_TITLE, completion: (() -> Void)? = nil) {
        let alert = UIAlertController(title: "Lỗi", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: okTitle, style: .default) { _ in
            if completion != nil { completion!() }
            alert.dismiss(animated: true, completion: nil)
        }
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    func showErrorAlert(code: Int, message: String, okTitle: String = Constants.OK_DEFAULT_TITLE, completion: (() -> Void)? = nil) {
        let alert = UIAlertController(title: "Lỗi \(code)", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: okTitle, style: .default) { _ in
            if completion != nil { completion!() }
            alert.dismiss(animated: true, completion: nil)
        }
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    func showAlert(message: String, okTitle: String = Constants.OK_DEFAULT_TITLE, completion: (() -> Void)? = nil) {
        let alert = UIAlertController(title: "Thông báo", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: okTitle, style: .default) { _ in
            if completion != nil { completion!() }
            alert.dismiss(animated: true, completion: nil)
        }
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    func showAlertWithCancel(message: String, okTitle: String = Constants.OK_DEFAULT_TITLE, completion: (() -> ())? = nil) {
        let alert = UIAlertController(title: "Thông báo", message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: okTitle, style: .default, handler: { _ in
            self.dismiss(animated: true, completion: completion)
        })
        let cancelAction = UIAlertAction(title: "Huỷ", style: .default, handler: nil)
        
        alert.addAction(cancelAction)
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
}
