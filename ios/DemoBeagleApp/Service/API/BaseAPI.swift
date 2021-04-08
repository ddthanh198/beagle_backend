//
//  BaseAPI.swift
//  govapp
//
//  Created by minhpv21 on 12/24/20.
//

import Foundation
import Alamofire
import SVProgressHUD
import RxSwift
import RxCocoa

protocol BaseAPIProtocol: class {
    associatedtype EM
    
    func data() -> (Data, String, String, String)?
    func params() -> Parameters?
    func method() -> HTTPMethod
    func path() -> String
    func convertToObject(data: Data) -> EM?
    func getErrorMessage(data: Data) -> String?
    func getErrorCodeName(data: Data) -> String?
}

class BaseAPI<T: Decodable>: BaseAPIProtocol {
    typealias EM = T
    
    // Stored properties
    lazy var sessionManager: SessionManager = {
        let configuration = URLSessionConfiguration.default
        configuration.timeoutIntervalForRequest = AppConstants.TIME_OUT
        configuration.timeoutIntervalForResource = AppConstants.TIME_OUT
        return Alamofire.SessionManager(configuration: configuration)
    }()
    
    let decoder = JSONDecoder()
    private var timer: Timer?
    
    /*
     Theses methods need to be overided by every api
     */
    func data() -> (Data, String, String, String)? {
        return nil
    }
    
    func params() -> Parameters? {
        return nil
    }
    
    func method() -> HTTPMethod {
        return .get
    }
    
    func path() -> String {
        return ""
    }
    
    func convertToObject(data: Data) -> T? {
        do {
            let object = try decoder.decode(T.self, from: data)
            return object
        } catch {
            print(error)
            return nil
        }
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
    
    // Request
    func request(isShowLoading: Bool = true) -> Observable<T> {
        // Headers
        let headers: HTTPHeaders = [
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": SessionAppManager.shared.token ?? "",
            "os_type": "iOS",
            "os_version": UIDevice.current.systemVersion,
            "app_version": Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "1.0.0"
        ]
        
        let headersForUploadFile: HTTPHeaders = [
            "Accept": "application/json",
            "Content-Type": "multipart/form-data",
            "Authorization": SessionAppManager.shared.token ?? "",
            "os_type": "iOS",
            "os_version": UIDevice.current.systemVersion,
            "app_version": Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "1.0.0"
        ]
        
        let url = Urls.HOST + "/" + self.path()
        print("REQUEST URL: \(url)")
        print("REQUEST PARAMS: \(params() ?? [:])")
        if let (dataToUpload, keyName, _, _) = data() {
            print("REQUEST DATA: \(dataToUpload) WITH NAME: \(keyName)")
            print("REQUEST HEADERS: \(headersForUploadFile)")
        } else {
            print("REQUEST HEADERS: \(headers)")
        }
        
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
            
            if let (dataToUpload, keyName, fileName, fileType) = self.data() {
                self.sessionManager.upload(multipartFormData: { (multipartFormData) in
                    multipartFormData.append(dataToUpload, withName: keyName, fileName: fileName, mimeType: fileType)
                    
                    if let paramsToUpload = self.params() {
                        for (key, value) in paramsToUpload {
                            multipartFormData.append(((value as? String)?.data(using: .utf8))!, withName: key)
                        }
                    }
                }, usingThreshold: UInt64(), to: url, method: self.method(), headers: headersForUploadFile) { (encodingResult) in
                    self.timer?.invalidate()
                    self.timer = nil
                    switch encodingResult {
                    case .success(let upload, _, _):
                        upload.responseJSON { response in
                            DispatchQueue.main.async {
                                SVProgressHUD.dismiss()
                            }
                            guard let httpResponse = response.response, let data = response.data else { return }
                            print("STATUS: \(httpResponse.statusCode)")
                            if httpResponse.statusCode < 200 || httpResponse.statusCode > 300 {
                                let errorMessage = self.getErrorMessage(data: data) ?? ""
                                let errorCodeName = self.getErrorCodeName(data: data)
                                SessionAppManager.shared.errorAuthorization.onNext((httpResponse.statusCode, errorMessage, errorCodeName))
                                return
                            }
                            switch response.result {
                            case .success:
                                guard let object = self.convertToObject(data: data) else { return }
                                observable.onNext(object)
                                observable.onCompleted()
                            case .failure(let error):
                                observable.onError(error)
                                observable.onCompleted()
                            }
                        }
                    case .failure(let error):
                        DispatchQueue.main.async {
                            SVProgressHUD.dismiss()
                        }
                        observable.onError(error)
                        observable.onCompleted()
                    }
                }
            } else {
                self.sessionManager.request(url, method: self.method(), parameters: self.params(), encoding: JSONEncoding.default, headers: headers)
                    .responseJSON { response in
                        print(response)
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
                            guard let object = self.convertToObject(data: data) else { return }
                            observable.onNext(object)
                            observable.onCompleted()
                        case .failure(let error):
                            observable.onError(error)
                            observable.onCompleted()
                        }
                    }
            }
            return Disposables.create()
        }
    }
}
