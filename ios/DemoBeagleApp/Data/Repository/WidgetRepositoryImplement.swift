//
//  WidgetRepositoryImplement.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation
import RxSwift
import RxCocoa
import RealmSwift

class WidgetRepositoryImplement: WidgetRepository {
    enum Constants {
        static let bottomNavigationViewFileName = "BottomNavigationView"
    }
    
    func getBottomView() -> BottomViewEntity? {
        let cacheRepository = CacheRepositoryImplement()
        do {
            let realm = try Realm()
            let components = Array(realm.objects(BeagleComponent.self))
            guard let bottomNavigationViewFileNameComponent = components.filter ({ $0.name == Constants.bottomNavigationViewFileName }).first else { return nil }
            guard let json = cacheRepository.readCacheFile(component: bottomNavigationViewFileNameComponent) else { return nil }
            let decoder = JSONDecoder()
            let bottomNavigationView = try decoder.decode(BottomViewEntity.self, from: json.data(using: .utf8)!)
            return bottomNavigationView
        } catch let error {
            print(error.localizedDescription)
            return nil
        }
    }
    
    func getTabbarIcon(url: String) -> Observable<UIImage> {
        return GetTabbarIconAPI(endPoint: url).request()
    }
}
