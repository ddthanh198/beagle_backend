//
//  CheckBeagleCacheVersion.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 16/04/2021.
//

import Foundation
import Alamofire

class BeagleComponent: Decodable {
    var name: String?
    var version: String?
    var json: NSDictionary?
}

extension BeagleComponent {
    enum CodingKey: String, CodingKey {
        case name
        case version
        case json
    }
    
    required init(from decoder: Decoder) throws {
        let container = decoder.container(keyedBy: <#T##CodingKey.Protocol#>)
    }
}

class CheckBeagleCacheVersion: BaseAPI<[BeagleComponent]> {
    var components: [BeagleComponent]?
    
    init(components: [BeagleComponent]?) {
        self.components = components
    }
    
    override func path() -> String {
        return Urls.BEAGLE_CACHE_VERSION_URL
    }
    
    override func method() -> HTTPMethod {
        return .post
    }
    
    override func params() -> Parameters? {
        guard let `components` = components else {
            return nil
        }
        var body = [[String:String]]()
        for component in components {
            body.append([
                "component": component.name ?? "",
                "version": component.version ?? ""
            ])
        }
        return [
            "components": body
        ]
    }
}
