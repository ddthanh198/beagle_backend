//
//  CommonResponse.swift
//  govapp
//
//  Created by VTN-MINHPV21 on 1/8/21.
//

import Foundation

class CommonEntity: Decodable {
    var code: Int?
    var status: String?
    var message: String?
    var data: String?
    var success: Bool?
    var error_code_name: String?
}
