//
//  OpenDateRangePicker.swift
//  DemoBeagleApp
//
//  Created by VTN-MINHPV21 on 06/04/2021.
//

import Foundation
import Beagle
import HorizonCalendar

class OpenDateRangePicker: Action {
    var context: Context?
    
    
    func execute(controller: BeagleController, origin: UIView) {
        let timeRangePicker = TimeRangeViewController()
        timeRangePicker.onClick = { (startDate, endDate, startDateString, endDateString) in
            print(startDateString + endDateString)
            let result = SetContext(contextId: "global", value: DynamicObject.init(stringLiteral: startDateString + endDateString))
            print(result)
        }
        let timePickerNavi = UINavigationController(rootViewController: timeRangePicker)
        timePickerNavi.modalPresentationStyle = .overFullScreen
        timePickerNavi.modalTransitionStyle = .crossDissolve
        controller.present(timePickerNavi, animated: true, completion: nil)
    }
}
