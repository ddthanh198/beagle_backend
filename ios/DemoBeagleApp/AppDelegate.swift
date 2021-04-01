//
//  AppDelegate.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import UIKit
import Beagle
import BeagleScaffold

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        configBeagle()
        setupRootView()
        return true
    }
}

extension AppDelegate {
    private func configBeagle() {
        let dependencies = BeagleDependencies()
        dependencies.urlBuilder = UrlBuilder(
            baseUrl: URL(string: Urls.HOST)!
        )
        
        BeagleConfig.start(dependencies: dependencies)
    }
    
    private func setupRootView() {
        window = UIWindow(frame: UIScreen.main.bounds)
        let tabBarController = MainTabBarController(nibName: "MainTabBarController", bundle: nil)
        let navigationController = UINavigationController(rootViewController: tabBarController)
        navigationController.navigationBar.isHidden = true
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }
}

