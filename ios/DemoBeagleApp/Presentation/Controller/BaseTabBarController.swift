//
//  BaseTabBarController.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import UIKit
import RxSwift
import RxCocoa

class BaseTabBarController: UITabBarController {
    let disposeBag = DisposeBag()
    
    enum Constants {
        static let ERROR_AUTHEN_MESSAGE_FROM_SERVER = "Unauthenticated"
        static let ERROR_AUTHEN_MESSAGE = "Phiên đăng nhập đã hết hạn, bạn hãy đăng nhập lại để tiếp tục sử dụng"
        static let CANNOT_SEND_EMAIL = "Thiết bị này không thể gửi email"
        static let OK_DEFAULT_TITLE = "Đồng ý"
        static let COMMON_ERROR_MESSAGE = "Đã có lỗi xảy ra"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

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
