//
//  CacheRepositoryImplement.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import RxSwift
import RxCocoa
import RealmSwift

class CacheRepositoryImplement: CacheRepository {
    func writeCacheFiles(components: [BeagleComponent]) {
        components.forEach {
            writeCacheFile(component: $0)
        }
    }
    
    func writeCacheFile(component: BeagleComponent) {
        if let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first {
            let fileURL = documentDirectory.appendingPathComponent(component.name + "-" + component.version + ".json")
            do {
                try component.json.write(to: fileURL, atomically: false, encoding: .utf8)
            } catch let error {
                print(error.localizedDescription)
            }
        }
    }
    
    func readCacheFile(component: BeagleComponent) -> String? {
        if let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first {
            let fileURL = documentDirectory.appendingPathComponent(component.name + "-" + component.version + ".json")
            do {
                let content = try String(contentsOf: fileURL)
                return content
            } catch let error {
                print(error.localizedDescription)
                return nil
            }
        } else {
            return nil
        }
    }
    
    func readCacheFile(fileName: String) -> String? {
        if let documentDirectory = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first {
            let fileURL = documentDirectory.appendingPathComponent(fileName + ".json")
            do {
                let content = try String(contentsOf: fileURL)
                return content
            } catch let error {
                print(error.localizedDescription)
                return nil
            }
        } else {
            return nil
        }
    }
    
    func saveComponents(components: [BeagleComponent]) {
        do {
            let realm = try Realm()
            let beagleComponents = Array(realm.objects(BeagleComponent.self))
            var filteredComponents = [BeagleComponent]()
            for beagleComponent in beagleComponents {
                for component in components {
                    if beagleComponent.name == component.name {
                        filteredComponents.append(beagleComponent)
                    }
                }
            }
            if realm.isInWriteTransaction {
                realm.delete(filteredComponents)
                realm.add(components)
            } else {
                try realm.write {
                    realm.delete(filteredComponents)
                    realm.add(components)
                }
            }
        } catch let error {
            print(error.localizedDescription)
        }
    }
    
    func saveComponent(component: BeagleComponent) {
        do {
            let realm = try Realm()
            let components = Array(realm.objects(BeagleComponent.self))
            let filterComponent = components.filter { return $0.name == component.name }
            realm.delete(filterComponent)
            realm.add(component, update: .modified)
        } catch let error {
            print(error.localizedDescription)
        }
    }
    
    func checkCacheVersion() -> Observable<[BeagleComponent]> {
        return CheckBeagleCacheVersion().request(isShowLoading: false)
    }
    
    func getComponents() -> [BeagleComponent]? {
        do {
            let realm = try Realm()
            return Array(realm.objects(BeagleComponent.self))
        } catch let error {
            print(error.localizedDescription)
            return nil
        }
    }
}
