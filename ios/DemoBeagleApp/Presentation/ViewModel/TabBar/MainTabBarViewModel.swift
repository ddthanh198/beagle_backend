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
        let readCacheFileUseCase = ReadCacheFileUseCase(repository: CacheRepositoryImplement())
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
        guard let bottomView = self.input.getBottomViewUseCase.execute() else {
            self.output.isGetBottomViewSuccess.onNext(false)
            return
        }
        self.bottomView = bottomView
        self.output.isGetBottomViewSuccess.onNext(true)
    }
    
    func readCacheFile(fileName: String) -> String? {
        return self.input.readCacheFileUseCase.execute(param: fileName)
    }
    
    func getAllTabbarIcons() {
        guard let `bottomView` = bottomView, let firstChild = bottomView.children.first else { return }
        let tabbarIconUrls = firstChild.tabItems.map { (items) -> String in
            return items.remoteIconUrl
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
