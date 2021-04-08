//
//  GetTabbarIconUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 08/04/2021.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa

class GetTabbarIconUseCase: UseCase {
    typealias DM = String
    typealias EM = Observable<UIImage>
    
    private let repository = WidgetRepositoryImplement()
    
    func execute(param: String) -> Observable<UIImage> {
        return repository.getTabbarIcon(url: param)
    }
}
