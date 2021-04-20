//
//  PermissionAction.swift
//  DemoBeagleApp
//
//  Created by Apple on 4/13/21.
//

import Foundation
import Beagle
import Photos

class PermissionAction: Action {
    let permission: String
    
    init(permission: String) {
        self.permission = permission
    }
    
    func execute(controller: BeagleController, origin: UIView) {
        if permission == "requestCameraPermission" {
            requestCameraPermission(controller: controller)
        } else if permission == "requestPhotoPermission" {
            requestGalleryPermission(controller: controller)
        }
    }
    
    func requestCameraPermission(controller: BeagleController) {
        if AVCaptureDevice.authorizationStatus(for: .video) ==  AVAuthorizationStatus.authorized {
            self.openCamera(controller: controller)
        } else {
            AVCaptureDevice.requestAccess(for: .video, completionHandler: { (granted: Bool) -> Void in
                if granted == true {
                    DispatchQueue.main.async {
                        self.openCamera(controller: controller)
                    }
                } else {
                    self.openSetting(controller: controller)
                }
            })
        }
    }
    
    //Camera
    fileprivate func openCamera(controller: BeagleController) {
        let vc = UIImagePickerController()
        vc.sourceType = .camera
        vc.allowsEditing = true
        vc.delegate = controller.self
        controller.present(vc, animated: true, completion: nil)
    }
    
    
    //Gallery
    fileprivate func openGallery(controller: BeagleController) {
        let vc = UIImagePickerController()
        vc.sourceType = .photoLibrary
        vc.allowsEditing = true
        vc.delegate = controller.self
        controller.present(vc, animated: true, completion: nil)
    }
    
    func requestGalleryPermission(controller: BeagleController) {
        if PHPhotoLibrary.authorizationStatus() ==  .authorized {
            self.openGallery(controller: controller)
        } else {
            PHPhotoLibrary.requestAuthorization({ status in
                if status == .authorized {
                    DispatchQueue.main.async {
                        self.openGallery(controller: controller)
                    }
                } else {
                    self.openSetting(controller: controller)
                }
            })
        }
    }
    
    
    //openSetting
    private func openSetting(controller: BeagleController) {
        let alertController = UIAlertController (title: "Thông báo", message: "Bạn chưa cấp quyền cho ứng dụng. Vui lòng vào cài đặt và bật quyền cho ứng dụng", preferredStyle: .alert)
        
        let settingsAction = UIAlertAction(title: "Mở cài đặt", style: .default) { (_) -> Void in
            guard let settingsUrl = URL(string: UIApplication.openSettingsURLString) else {
                return
            }
            if UIApplication.shared.canOpenURL(settingsUrl) {
                UIApplication.shared.open(settingsUrl, completionHandler: { (success) in
                    
                })
            }
        }
        alertController.addAction(settingsAction)
        let cancelAction = UIAlertAction(title: "Huỷ bỏ", style: .default, handler: nil)
        alertController.addAction(cancelAction)
        DispatchQueue.main.async {
            controller.present(alertController, animated: true, completion: nil)
        }
    }
    
}

extension UIViewController: UIImagePickerControllerDelegate & UINavigationControllerDelegate {
    public func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        
        guard let image = info[.editedImage] as? UIImage else { return }
        ImagePicker.instance!.image = image
        picker.dismiss(animated: true, completion: nil)
    }
}
