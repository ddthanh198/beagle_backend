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
import SDWebImage
import SVProgressHUD

class MainTabBarController: BaseTabBarController {
    private let viewModel = MainTabBarViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initFirstState()
        configRx()
    }
    
    private func initFirstState() {
        let viewController = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "ViewController") as! ViewController
        self.setViewControllers([viewController], animated: true)
    }
    
    private func configRx() {
        viewModel.output.isGetAllTabbarIconsSuccess
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
        
        viewModel.output.isGetBottomViewSuccess
            .subscribe(onNext: { [weak self] _ in
                guard let `self` = self else { return }
                self.viewModel.getAllTabbarIcons()
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.showErrorAlert(error: error)
            })
            .disposed(by: disposeBag)
        
        viewModel.getBottomView()
    }
    
    private func configLayout() {
        guard let bottomView = self.viewModel.bottomView, let firstChild = bottomView.children.first, !firstChild.tabItems.isEmpty else { return }
        tabBar.tintColor = UIColor(firstChild.selectedColor)
        tabBar.unselectedItemTintColor = UIColor(firstChild.unselectedColor)
        
        var listOfVC = [UIViewController]()
        
        for index in 0...firstChild.tabItems.count-1 {
            let item = firstChild.tabItems[index]
            let icon = self.viewModel.tabbarIcons[index]
            guard let content = self.viewModel.readCacheFile(fileName: item.cacheFile) else { return }
            let viewController = Beagle.screen(.declarativeText(content))
            viewController.tabBarItem = UITabBarItem(title: firstChild.tabItems[index].title, image: icon, tag: index)
            listOfVC.append(viewController)
        }
        
        self.setViewControllers(listOfVC, animated: true)
    }
}
