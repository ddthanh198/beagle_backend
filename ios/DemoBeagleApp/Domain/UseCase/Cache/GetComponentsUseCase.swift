//
//  GetComponentsUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation

class GetComponentsUseCase: UseCaseWithoutParam {
    typealias EM = [BeagleComponent]?
    
    private var repository: CacheRepository
    
    init(repository: CacheRepository) {
        self.repository = repository
    }
    
    func execute() -> [BeagleComponent]? {
        return self.repository.getComponents()
    }
}
