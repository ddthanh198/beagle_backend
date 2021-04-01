//
//  GetBottomViewUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation
import RxCocoa
import RxSwift

class GetBottomViewUseCase: UseCaseWithoutParam {
    typealias EM = Observable<BottomViewEntity>
    
    private let repository = WidgetRepositoryImplement()
    
    func execute() -> Observable<BottomViewEntity> {
        return repository.getBottomView()
    }
}
