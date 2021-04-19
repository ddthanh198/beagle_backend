//
//  NetworkManager.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 19/04/2021.
//

import Foundation
import Alamofire

class NetworkManager {
    static let shared = NetworkManager()
    
    private let reachabilityManager = NetworkReachabilityManager()
    
    func startObserver() {
        reachabilityManager?.listener = { status in
            BaseViewController.reachabilityStatus.onNext(status)
        }
        
        reachabilityManager?.startListening()
    }
    
    func isConnectInternet() -> Bool {
        return reachabilityManager?.networkReachabilityStatus == .reachable(.ethernetOrWiFi) || reachabilityManager?.networkReachabilityStatus == .reachable(.wwan)
    }
    
    var connection: NetworkReachabilityManager.NetworkReachabilityStatus {
        return reachabilityManager?.networkReachabilityStatus ?? .unknown
    }
}
