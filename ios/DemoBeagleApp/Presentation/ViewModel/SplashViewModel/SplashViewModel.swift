//
//  SplashViewModel.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

class SplashViewModel: BaseViewModel {
    struct Input {
        let checkCacheVersionUseCase = CheckCacheVersionUseCase(repository: CahceRepositoryImplement())
    }
    
    struct Output {
        let isGetBeagleComponentsSuccess = PublishSubject<Bool>()
    }
    
    private let input = Input()
    let output = Output()
    var components = [BeagleComponent]()
    
    func checkCacheVersion() {
        self.input.checkCacheVersionUseCase
            .execute(param: nil)
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] components in
                guard let `self` = self else { return }
                self.components = components
                self.output.isGetBeagleComponentsSuccess.onNext(true)
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.output.isGetBeagleComponentsSuccess.onError(error)
            })
            .disposed(by: disposeBag)
    }
}
