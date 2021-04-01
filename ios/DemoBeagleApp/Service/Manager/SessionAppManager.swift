//
//  SessionAppManager.swift
//  govapp
//
//  Created by VTN-MINHPV21 on 1/7/21.
//

import Foundation
import RxSwift
import RxCocoa

class SessionAppManager {
    static let shared = SessionAppManager()
    var errorAuthorization = PublishSubject<(Int, String, String?)>()
    
    var token: String? {
        return UserDefaults.standard.value(forKey: "__token__") as? String
    }
    
    var firebaseToken: String? {
        get {
            return UserDefaults.standard.value(forKey: "__firebase_token__") as? String
        }
        set {
            UserDefaults.standard.setValue(newValue, forKey: "__firebase_token__")
        }
    }
    
    var isNewUser: Bool? {
        return UserDefaults.standard.value(forKey: "__is_new_user__") as? Bool
    }
    
    var isEnabledBiometric: Bool? {
        get {
            return UserDefaults.standard.value(forKey: "__is_enabled_biometric__") as? Bool
        }
        set {
            UserDefaults.standard.setValue(newValue, forKey: "__is_enabled_biometric__")
        }
    }
    
    func clearSession() {
        UserDefaults.standard.removeObject(forKey: "__token__")
        UserDefaults.standard.removeObject(forKey: "__is_new_user__")
        UserDefaults.standard.synchronize()
    }
}
