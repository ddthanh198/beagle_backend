//
//  MainTabBarViewModel.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import UIKit
import RxSwift
import RxCocoa

class MainTabBarViewModel: BaseViewModel {
    struct Input {
        let getBottomViewUseCase = GetBottomViewUseCase()
    }
    
    struct Output {
        let isGetBottomViewSuccess = PublishSubject<Bool>()
    }
    
    private let input = Input()
    let output = Output()
    var bottomView: BottomViewEntity?
    
    func getBottomView() {
        self.input.getBottomViewUseCase
            .execute()
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] bottomView in
                guard let `self` = self else { return }
                self.bottomView = bottomView
                self.output.isGetBottomViewSuccess.onNext(true)
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.output.isGetBottomViewSuccess.onError(error)
            })
            .disposed(by: disposeBag)
    }
}
