//
//  CheckBeagleCacheVersion.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import Alamofire
import RxSwift
import RxCocoa
import SVProgressHUD
import RealmSwift

class BeagleComponent: Object {
    @objc dynamic var name: String = ""
    @objc dynamic var version: String = ""
    var json: String = ""
    
    override class func primaryKey() -> String? {
        return "name"
    }
}

class CheckBeagleCacheVersion {
    // Stored properties
    lazy var sessionManager: SessionManager = {
        let configuration = URLSessionConfiguration.default
        configuration.timeoutIntervalForRequest = AppConstants.TIME_OUT
        configuration.timeoutIntervalForResource = AppConstants.TIME_OUT
        return Alamofire.SessionManager(configuration: configuration)
    }()
    private let repository = CacheRepositoryImplement()
    let decoder = JSONDecoder()
    private var timer: Timer?
    
    func path() -> String {
        return Urls.BEAGLE_CACHE_VERSION_URL
    }
    
    func method() -> HTTPMethod {
        return .post
    }
    
    func params() -> Parameters? {
        guard let components = repository.getComponents() else { return nil }
        var body = [[String:String]]()
        for component in components {
            body.append([
                "component": component.name,
                "version": component.version
            ])
        }
        return [
            "components": body
        ]
    }
    
    func getErrorMessage(data: Data) -> String? {
        do {
            let object = try decoder.decode(CommonEntity.self, from: data)
            return object.message
        } catch {
            return nil
        }
    }
    
    func getErrorCodeName(data: Data) -> String? {
        do {
            let object = try decoder.decode(CommonEntity.self, from: data)
            return object.error_code_name
        } catch {
            return nil
        }
    }
    
    func request(isShowLoading: Bool = true) -> Observable<[BeagleComponent]> {
        // Headers
        let headers: HTTPHeaders = [
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": SessionAppManager.shared.token ?? "",
            "os_type": "iOS",
            "os_version": UIDevice.current.systemVersion,
            "app_version": Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "1.0.0"
        ]
        
        let url = Urls.HOST + "/" + self.path()
        print("REQUEST URL: \(url)")
        print("REQUEST PARAMS: \(params() ?? [:])")
        print("REQUEST HEADERS: \(headers)")
        
        if !NetworkManager.shared.isConnectInternet() {
            BaseViewController.reachabilityStatus.onNext(.unknown)
            return Observable.empty()
        }
        self.timer?.invalidate()
        self.timer = nil
        timer = Timer.scheduledTimer(withTimeInterval: AppConstants.TIME_OUT, repeats: false, block: { _ in
            BaseViewController.reachabilityStatus.onNext(.unknown)
        })
        
        return Observable.create { observable -> Disposable in
            if isShowLoading {
                DispatchQueue.main.async {
                    SVProgressHUD.setDefaultMaskType(.clear)
                    SVProgressHUD.show()
                }
            }
            
            self.sessionManager.session.getAllTasks { tasks in
                tasks.forEach { $0.cancel() }
            }
            
            self.sessionManager.request(url, method: self.method(), parameters: self.params(), encoding: JSONEncoding.default, headers: headers)
                .responseJSON { response in
                    guard let httpResponse = response.response, let data = response.data else { return }
                    print("STATUS: \(httpResponse.statusCode)")
                    self.timer?.invalidate()
                    self.timer = nil
                    DispatchQueue.main.async {
                        SVProgressHUD.dismiss()
                    }
                    if httpResponse.statusCode < 200 || httpResponse.statusCode > 300 {
                        let errorMessage = self.getErrorMessage(data: data) ?? ""
                        let errorCodeName = self.getErrorCodeName(data: data)
                        SessionAppManager.shared.errorAuthorization.onNext((httpResponse.statusCode, errorMessage, errorCodeName))
                        return
                    }
                    switch response.result {
                    case .success:
                        do {
                            if let dic = try JSONSerialization.jsonObject(with: data, options: []) as? [[String:Any]] {
                                var components = [BeagleComponent]()
                                try dic.forEach { beagleComponent in
                                    let name = beagleComponent["name"] as! String
                                    let version = beagleComponent["version"] as! String
                                    let json = beagleComponent["json"] as! [String:Any]
                                    let jsonData = try JSONSerialization.data(withJSONObject: json, options: .prettyPrinted)
                                    let jsonString = NSString(data: jsonData, encoding: String.Encoding.utf8.rawValue)!
                                    let component = BeagleComponent()
                                    component.name = name
                                    component.version = version
                                    component.json = jsonString as String
                                    components.append(component)
                                }
                                observable.onNext(components)
                                observable.onCompleted()
                            }
                        } catch let error {
                            observable.onError(error)
                            observable.onCompleted()
                        }
                    case .failure(let error):
                        observable.onError(error)
                        observable.onCompleted()
                    }
                }
            return Disposables.create()
        }
    }
}
