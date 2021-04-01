//
//  UseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 01/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

protocol UseCase {
    associatedtype EM
    
    func execute(param: Param) -> EM
}

protocol UseCaseWithoutParam {
    associatedtype EM
    
    func execute() -> EM
}

protocol Param { }
