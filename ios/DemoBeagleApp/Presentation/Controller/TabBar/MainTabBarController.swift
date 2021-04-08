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
            .observe(on: MainScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler.init(qos: .background))
            .subscribe(onNext: { [weak self] _ in
                guard let `self` = self else { return }
                self.viewModel.getAllTabbarIcons()
            }, onError: { [weak self] error in
                guard let `self` = self else { return }
                self.showErrorAlert(error: error)
            })
            .disposed(by: disposeBag)
        
        SVProgressHUD.setDefaultMaskType(.clear)
        SVProgressHUD.show()
        viewModel.getBottomView()
    }
    
    private func configLayout() {
        guard let bottomView = self.viewModel.bottomView, let firstChild = bottomView.children.first else { return }
        tabBar.tintColor = UIColor(firstChild.selectedColor)
        tabBar.unselectedItemTintColor = UIColor(firstChild.unselectedColor)
        
        var listOfVC = [UIViewController]()
        var chartViewController = ChartViewController()
        for item in firstChild.menuItems {
            let tag = firstChild.menuItems.firstIndex(of: item) ?? 0
            let viewController = Beagle.screen(.remote(.init(url: item[2])))
            viewController.tabBarItem = UITabBarItem(title: item[1], image: self.viewModel.tabbarIcons[tag], tag: tag)
            listOfVC.append(viewController)
//            if item[1] == "Management" {
//                chartViewController.tabBarItem = UITabBarItem(title: item[1], image: nil, tag: tag)
//                listOfVC.append(chartViewController)
//            } else {
                // viewController.tabBarItem = UITabBarItem(title: item[1], image: nil, tag: tag)
                // listOfVC.append(viewController)
            //}
           
        }
        
        self.setViewControllers(listOfVC, animated: true)
    }
}
