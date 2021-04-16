//
//  CheckCacheVersionUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

class CheckCacheVersionUseCase: UseCaseWithoutParam {
    typealias EM = Observable<[BeagleComponent]>
    
    private var repository: CacheRepository
    private let disposeBag = DisposeBag()
    
    init(repository: CacheRepository) {
        self.repository = repository
    }
    
    func execute() -> Observable<[BeagleComponent]> {
        self.repository.checkCacheVersion()
    }
}
