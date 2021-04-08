//
//  WidgetRepositoryImplement.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

class WidgetRepositoryImplement: WidgetRepository {
    func getBottomView() -> Observable<BottomViewEntity> {
        return GetBottomAPI().request(isShowLoading: false)
    }
    
    func getTabbarIcon(url: String) -> Observable<UIImage> {
        return GetTabbarIconAPI(endPoint: url).request()
    }
}
