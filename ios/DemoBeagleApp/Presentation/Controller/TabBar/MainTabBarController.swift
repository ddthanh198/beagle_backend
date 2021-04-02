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
            let tag = firstChild.menuItems.firstIndex(of: item) ?? 0
            let viewController = Beagle.screen(.remote(.init(url: item[2])))
            viewController.tabBarItem = UITabBarItem(title: item[1], image: nil, tag: tag)
            listOfVC.append(viewController)
            let imageView = UIImageView(frame: .zero)
            imageView.sd_setImage(with: URL(string: item[0])) { (image, error, _, _) in
                listOfVC[tag].tabBarItem.image = image?.withRenderingMode(.automatic).sd_resizedImage(with: CGSize(width: 30, height: 30), scaleMode: .aspectFit)
            }
        }
        
        self.viewControllers = listOfVC
    }
}
