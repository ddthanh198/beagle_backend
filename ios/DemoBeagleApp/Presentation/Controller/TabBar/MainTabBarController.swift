//
//  MainTabBarController.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import UIKit
import Beagle
import RxSwift
import RxCocoa

class MainTabBarController: BaseTabBarController {
    private let viewModel = MainTabBarViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configRx()
    }
    
    private func configRx() {
        viewModel.output.isGetBottomViewSuccess
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] _ in
                guard let `self` = self else { return }
                self.configLayout()
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.showErrorAlert(error: error)
            })
            .disposed(by: disposeBag)
        
        viewModel.getBottomView()
    }
    
    private func configLayout() {
        guard let bottomView = self.viewModel.bottomView, let firstChild = bottomView.children.first else { return }
        tabBar.tintColor = UIColor(firstChild.selectedColor)
        tabBar.unselectedItemTintColor = UIColor(firstChild.unselectedColor)
        
        var listOfVC = [UIViewController]()
        for item in firstChild.menuItems {
            let viewController = Beagle.screen(.remote(.init(url: item[2])))
            viewController.tabBarItem = UITabBarItem(title: item[1], image: nil, tag: firstChild.menuItems.firstIndex(of: item) ?? 0)
            listOfVC.append(viewController)
        }
        
        self.viewControllers = listOfVC
    }
}
