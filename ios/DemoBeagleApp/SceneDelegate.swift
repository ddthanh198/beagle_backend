//
//  SceneDelegate.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import UIKit
import Beagle
import BeagleScaffold

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    @available(iOS 13.0, *)
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        // Use this method to optionally configure and attach the UIWindow `window` to the provided UIWindowScene `scene`.
        // If using a storyboard, the `window` property will automatically be initialized and attached to the scene.
        // This delegate does not imply the connecting scene or session are new (see `application:configurationForConnectingSceneSession` instead).
        configBeagle()
        guard let windowScene = (scene as? UIWindowScene) else { return }
        setupRootView(scene: windowScene)
    }
}

extension SceneDelegate {
    private func configBeagle() {
        let theme = AppTheme(styles: [
            "Title.Text.Orange": Styles.titleTextStyle,
            "Description.Text.Orange": Styles.descriptionTextStyle
        ])
        
        let dependencies = BeagleDependencies()
        dependencies.theme = theme
        dependencies.urlBuilder = UrlBuilder(
            baseUrl: URL(string: Urls.HOST)!
        )
        
        Beagle.dependencies = dependencies
        
        BeagleConfig.start(dependencies: dependencies)
    }
    
    @available(iOS 13.0, *)
    private func setupRootView(scene: UIWindowScene) {
        window = UIWindow(frame: scene.coordinateSpace.bounds)
        window?.windowScene = scene
        let tabBarController = MainTabBarController(nibName: "MainTabBarController", bundle: nil)
        let navigationController = UINavigationController(rootViewController: tabBarController)
        navigationController.navigationBar.isHidden = true
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }
}

