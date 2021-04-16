//
//  CacheRepository.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import RxSwift
import RxCocoa

protocol CacheRepository {
    func checkCacheVersion() -> Observable<[BeagleComponent]>
    
    func writeCacheFile(component: BeagleComponent)
    
    func writeCacheFiles(components: [BeagleComponent])
    
    func readCacheFile(component: BeagleComponent) -> String?
    
    func readCacheFile(fileName: String) -> String?
    
    func saveComponents(components: [BeagleComponent])
    
    func saveComponent(component: BeagleComponent)
    
    func getComponents() -> [BeagleComponent]?
}
