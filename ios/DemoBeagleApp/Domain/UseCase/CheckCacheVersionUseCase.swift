//
//  CheckCacheVersionUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

class CheckCacheVersionUseCase: UseCase {
    typealias EM = Observable<[BeagleComponent]>
    
    typealias DM = [BeagleComponent]?
    
    private var repository: CacheRepository
    private let disposeBag = DisposeBag()
    
    init(repository: CacheRepository) {
        self.repository = repository
    }
    
    func execute(param: [BeagleComponent]?) -> Observable<[BeagleComponent]> {
        self.repository.checkCacheVersion(components: param)
    }
}
