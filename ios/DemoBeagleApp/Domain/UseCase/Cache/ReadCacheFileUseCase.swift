//
//  ReadCacheFileUseCase.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation

class ReadCacheFileUseCase: UseCase {
    typealias EM = String?
    typealias DM = String
    
    private var repository: CacheRepository
    
    init(repository: CacheRepository) {
        self.repository = repository
    }
    
    func execute(param: String) -> String? {
        return self.repository.readCacheFile(fileName: param)
    }
}
