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
        let getTabbarIconUseCase = GetTabbarIconUseCase()
    }
    
    struct Output {
        let isGetBottomViewSuccess = PublishSubject<Bool>()
        let isGetAllTabbarIconsSuccess = PublishSubject<Bool>()
    }
    
    private let input = Input()
    let output = Output()
    var bottomView: BottomViewEntity?
    var tabbarIcons = [UIImage]()
    
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
    
    func getAllTabbarIcons() {
        guard let `bottomView` = bottomView, let firstChild = bottomView.children.first else { return }
        let tabbarIconUrls = firstChild.menuItems.map { (items) -> String in
            return items[0]
        }
        Observable.zip(tabbarIconUrls.map {
            return self.input.getTabbarIconUseCase.execute(param: $0)
        })
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] tabbarIcons in
                guard let `self` = self else { return }
                self.tabbarIcons = tabbarIcons
                self.output.isGetAllTabbarIconsSuccess.onNext(true)
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.output.isGetAllTabbarIconsSuccess.onError(error)
            })
            .disposed(by: disposeBag)
    }
}
