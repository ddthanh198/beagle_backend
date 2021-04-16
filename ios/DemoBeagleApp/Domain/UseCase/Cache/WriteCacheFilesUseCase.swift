//
//  WriteCacheFilesUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation

class WriteCacheFilesUseCase: UseCase {
    typealias EM = Void
    typealias DM = [BeagleComponent]
    
    private var repository: CacheRepository
    
    init(repository: CacheRepository) {
        self.repository = repository
    }
    
    func execute(param: [BeagleComponent]) -> Void {
        self.repository.writeCacheFiles(components: param)
    }
}
