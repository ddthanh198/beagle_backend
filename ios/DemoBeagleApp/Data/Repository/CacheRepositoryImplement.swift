//
//  CacheRepositoryImplement.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

class CahceRepositoryImplement: CacheRepository {
    func checkCacheVersion(components: [BeagleComponent]?) -> Observable<[BeagleComponent]> {
        return CheckBeagleCacheVersion(components: components).request(isShowLoading: false)
    }
}
