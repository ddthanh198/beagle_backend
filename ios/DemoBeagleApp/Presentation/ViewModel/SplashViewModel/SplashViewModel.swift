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
        let checkCacheVersionUseCase = CheckCacheVersionUseCase(repository: CacheRepositoryImplement())
        let writeCachesFileUseCase = WriteCacheFilesUseCase(repository: CacheRepositoryImplement())
        let saveComponentsUseCase = SaveComponentsUseCase(repository: CacheRepositoryImplement())
        let getComponentsUseCase = GetComponentsUseCase(repository: CacheRepositoryImplement())
    }
    
    struct Output {
        let isGetBeagleComponentsSuccess = PublishSubject<Bool>()
    }
    
    private let input = Input()
    let output = Output()
    var components = [BeagleComponent]()
    
    func checkCacheVersion() {
        self.input.checkCacheVersionUseCase
            .execute()
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] components in
                guard let `self` = self else { return }
                self.components = components
                self.input.saveComponentsUseCase.execute(param: components)
                self.input.writeCachesFileUseCase.execute(param: components)
                self.output.isGetBeagleComponentsSuccess.onNext(true)
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.output.isGetBeagleComponentsSuccess.onError(error)
            })
            .disposed(by: disposeBag)
    }
    
    func getComponents() -> [BeagleComponent]? {
        return self.input.getComponentsUseCase.execute()
    }
}
