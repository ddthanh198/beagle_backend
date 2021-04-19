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
        NetworkManager.shared.startObserver()
        configBeagle()
        setupRootView()
        return true
    }
}

extension AppDelegate {
    private func configBeagle() {
        let theme = AppTheme(styles: [
            "Title.Text.Orange": Styles.titleTextStyle,
            "Description.Text.Orange": Styles.descriptionTextStyle,
            "NormalBoldText": Styles.normalBoldTextStyle,
            "Title.Text.BasicDialog": Styles.basicDialogTextStyle,
            "Content.Text.BasicDialog": Styles.basicDialogContentStyle,
            "Accept.Button.BasicDialog": Styles.acceptButtonDialogStyle,
            "Cancel.Button.BasicDialog": Styles.cancelButtonDialogStyle
        ])
        
        let dependencies = BeagleDependencies()
        dependencies.theme = theme
        dependencies.urlBuilder = UrlBuilder(
            baseUrl: URL(string: Urls.HOST)!
        )
        dependencies.decoder.register(action: OpenSideMenuViewController.self)
        dependencies.decoder.register(action: ShowDialogAction.self)
        dependencies.decoder.register(action: DismissDialogAction.self)
        dependencies.decoder.register(action: ShowBottomSheetAction.self)
        dependencies.decoder.register(component: MyFloatingButton.self)
        dependencies.decoder.register(action: LoadingAction.self)
        dependencies.decoder.register(component: BarChartWidget.self)
        dependencies.decoder.register(component: PieChart.self)
        
        Beagle.dependencies = dependencies
        
        BeagleConfig.start(dependencies: dependencies)
    }
    
    private func setupRootView() {
        window = UIWindow(frame: UIScreen.main.bounds)
        let splashViewController = SplashViewController(nibName: "SplashViewController", bundle: nil)
        let navigationController = UINavigationController(rootViewController: splashViewController)
        navigationController.navigationBar.isHidden = true
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }
}

