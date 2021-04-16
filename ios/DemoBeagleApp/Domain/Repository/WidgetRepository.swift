//
//  WidgetRepository.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

protocol WidgetRepository {
    func getBottomView() -> BottomViewEntity?
    
    func getTabbarIcon(url: String) -> Observable<UIImage>
}
