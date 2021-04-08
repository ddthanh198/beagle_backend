//
//  GetTabbarIconAPI.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 08/04/2021.
//

import Foundation
import RxSwift
import RxCocoa
import UIKit

class GetTabbarIconAPI {
    private var endPoint = ""
    
    init(endPoint: String) {
        self.endPoint = endPoint
    }
    
    func request() -> Observable<UIImage> {
        return Observable.create { (observable) -> Disposable in
            guard let url = URL(string: self.endPoint) else {
                return Disposables.create()
            }
            do {
                let data = try Data(contentsOf: url)
                let image = UIImage(data: data)?.sd_resizedImage(with: CGSize(width: 30, height: 30), scaleMode: .aspectFit)
                observable.onNext(image!)
                observable.onCompleted()
            } catch let error {
                observable.onError(error)
                observable.onCompleted()
            }
            return Disposables.create()
        }
    }
}
