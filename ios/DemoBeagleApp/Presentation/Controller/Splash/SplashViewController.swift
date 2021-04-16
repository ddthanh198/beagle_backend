//
//  SplashViewController.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import UIKit
import RxSwift
import RxCocoa
import RealmSwift

class SplashViewController: BaseViewController {

    private let viewModel = SplashViewModel()
    let getComponentsUseCase = GetComponentsUseCase(repository: CacheRepositoryImplement())
    let readCacheFileUseCase = ReadCacheFileUseCase(repository: CacheRepositoryImplement())
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configRx()
        removeStatusBar()
    }
    
    private func configRx() {
        self.viewModel.output.isGetBeagleComponentsSuccess
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] _ in
                guard let `self` = self else { return }
                self.setMainTabBarControllerToRoot()
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.showErrorAlert(error: error)
            })
            .disposed(by: disposeBag)
        
        self.viewModel.checkCacheVersion()
    }
}
