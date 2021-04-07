//
//  TimeRangeViewController.swift
//  govapp
//
//  Created by Apple on 1/11/21.
//

import UIKit
import HorizonCalendar

class TimeRangeViewController: UIViewController {
    
    // MARK: - Properties
    
    let monthsLayout: MonthsLayout = .vertical(options: VerticalMonthsLayoutOptions(
                                                pinDaysOfWeekToTop: false,
                                                alwaysShowCompleteBoundaryMonths: false))
    lazy var calendarView = CalendarView(initialContent: makeContent())
    lazy var calendar = Calendar.current
    lazy var dayDateFormatter: DateFormatter = {
        let dateFormatter = DateFormatter()
        dateFormatter.calendar = calendar
        dateFormatter.locale = calendar.locale
        dateFormatter.dateFormat = DateFormatter.dateFormat(
            fromTemplate: "EEEE, MMM d, yyyy",
            options: 0,
            locale: calendar.locale ?? Locale.current)
        return dateFormatter
    }()
    
    var startDateString = ""
    var endDateString = ""
    var startDate: Date!
    var endDate: Date!
    var onClick: ((Date, Date, String, String) -> ())?
    
    // MARK: - Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()
        if #available(iOS 13.0, *) {
            view.backgroundColor = .systemBackground
        } else {
            view.backgroundColor = .white
        }
        
        calendarView.scroll(toDayContaining: Date(), scrollPosition: .centered, animated: false)
        self.title = "Chọn khoảng thời gian"
        self.navigationController?.navigationBar.barTintColor = UIColor("#0000ff")
        self.navigationController?.navigationBar.tintColor = UIColor.white
        self.navigationController?.navigationBar.titleTextAttributes = [NSAttributedString.Key.foregroundColor : UIColor.white]
    
        let backButton = UIBarButtonItem(image: UIImage(named: "ic_back_button"), style: .plain, target: self, action: #selector(backButtonClicked))
        self.navigationItem.leftBarButtonItem = backButton
        self.navigationItem.rightBarButtonItem = nil
    
        view.addSubview(calendarView)
     
        calendarView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            calendarView.topAnchor.constraint(equalTo: view.layoutMarginsGuide.topAnchor),
            calendarView.bottomAnchor.constraint(equalTo: view.layoutMarginsGuide.bottomAnchor),
            calendarView.leadingAnchor.constraint(
                greaterThanOrEqualTo: view.layoutMarginsGuide.leadingAnchor),
            calendarView.trailingAnchor.constraint(
                lessThanOrEqualTo: view.layoutMarginsGuide.trailingAnchor),
            calendarView.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            calendarView.widthAnchor.constraint(lessThanOrEqualToConstant: 375)
        ])
        
        
        calendarView.daySelectionHandler = { [weak self] day in
            guard let self = self else { return }
            
            switch self.calendarSelection {
            case .singleDay(let selectedDay):
                if day > selectedDay {
                    self.calendarSelection = .dayRange(selectedDay...day)
                    
                    self.hanldeResultDay(selectedDay: selectedDay, day: day)
                //    self.dismiss(animated: true, completion: nil)
                } else if day == selectedDay {
                    self.calendarSelection = .dayRange(selectedDay...day)
                    self.hanldeResultDay(selectedDay: selectedDay, day: day)
                }
                
                else {
                    self.navigationItem.rightBarButtonItem = nil
                    self.title = "Chọn khoảng thời gian"
                    self.calendarSelection = .singleDay(day)
                }
            case .none, .dayRange:
                self.navigationItem.rightBarButtonItem = nil
                self.title = "Chọn khoảng thời gian"
                self.calendarSelection = .singleDay(day)
            }
            self.calendarView.setContent(self.makeContent())
        }
        
    }
    
    func hanldeResultDay(selectedDay: Day, day: Day) {
        
        let rightButton = UIBarButtonItem(image: UIImage(named: "ic_read_all")?.withRenderingMode(.alwaysOriginal), style: .plain, target: self, action: #selector(saveTimeRange))
        self.navigationItem.rightBarButtonItem = rightButton
        let dateFromatter = DateFormatter()
        dateFromatter.dateFormat = "dd-MM-yyyy"
        dateFromatter.timeZone = TimeZone(abbreviation: "UTC")
        let startDayString = selectedDay.components.day! >= 10 ? "\(selectedDay.components.day!)" : "0\(selectedDay.components.day!)"
        
        let startMonthString = selectedDay.components.month! >= 10 ? "\(selectedDay.components.month!)" : "0\(selectedDay.components.month!)"
        
        let endDayString = day.components.day! >= 10 ? "\(day.components.day!)" : "0\(day.components.day!)"
        
        let endMonthString =  day.components.month! >= 10 ? "\(day.components.month!)" : "0\(day.components.month!)"
        self.title = "Từ \(startDayString)/\(startMonthString)/\(selectedDay.components.year!) đến \(endDayString)/\(endMonthString)/\(day.components.year!)"
        startDateString = "\(startDayString)/\(startMonthString)/\(selectedDay.components.year!)"
        endDateString = "\(endDayString)/\(endMonthString)/\(day.components.year!)"
        self.startDate = dateFromatter.date(from: "\(selectedDay.components.day!)/\(selectedDay.components.month!)/\(selectedDay.components.year!)")
        self.endDate = dateFromatter.date(from: "\(day.components.day!)/\(day.components.month!)/\(day.components.year!)")
    }
    
    @objc func backButtonClicked() {
        self.dismiss(animated: true, completion: nil)
    }
    
    @objc func saveTimeRange() {
        if let `onClick` = self.onClick {
            onClick(startDate, endDate, startDateString, endDateString)
        }
        self.dismiss(animated: true, completion: nil)
    }
    
    func makeContent() -> CalendarViewContent {
        let startDate = calendar.date(from: DateComponents(year: 2000, month: 01, day: 01))!
        let endDate = calendar.date(from: DateComponents(year: 2050, month: 12, day: 31))!
        
        let calendarSelection = self.calendarSelection
        let dateRanges: Set<ClosedRange<Date>>
        if
            case .dayRange(let dayRange) = calendarSelection,
            let lowerBound = calendar.date(from: dayRange.lowerBound.components),
            let upperBound = calendar.date(from: dayRange.upperBound.components)
        {
            dateRanges = [lowerBound...upperBound]
        } else {
            dateRanges = []
        }
        
        return CalendarViewContent(
            calendar: calendar,
            visibleDateRange: startDate...endDate,
            monthsLayout: monthsLayout)
            
            .withInterMonthSpacing(24)
            .withVerticalDayMargin(8)
            .withHorizontalDayMargin(8)
            
            .withDayItemModelProvider { [weak self] day in
                let textColor: UIColor
                if #available(iOS 13.0, *) {
                    textColor = .label
                } else {
                    textColor = .black
                }
                
                let isSelectedStyle: Bool
                switch calendarSelection {
                case .singleDay(let selectedDay):
                    isSelectedStyle = day == selectedDay
                case .dayRange(let selectedDayRange):
                    isSelectedStyle = day == selectedDayRange.lowerBound || day == selectedDayRange.upperBound
                case .none:
                    isSelectedStyle = false
                }
                
                let dayAccessibilityText: String?
                if let date = self?.calendar.date(from: day.components) {
                    dayAccessibilityText = self?.dayDateFormatter.string(from: date)
                } else {
                    dayAccessibilityText = nil
                }
                
                return CalendarItemModel<DayView>(
                    invariantViewProperties: .init(textColor: textColor, isSelectedStyle: isSelectedStyle),
                    viewModel: .init(dayText: "\(day.day)", dayAccessibilityText: dayAccessibilityText))
            }
            
            .withDayRangeItemModelProvider(for: dateRanges) { dayRangeLayoutContext in
                CalendarItemModel<DayRangeIndicatorView>(
                    invariantViewProperties: .init(),
                    viewModel: .init(
                        framesOfDaysToHighlight: dayRangeLayoutContext.daysAndFrames.map { $0.frame }))
            }
    }
    
    // MARK: Private
    
    private enum CalendarSelection {
        case singleDay(Day)
        case dayRange(DayRange)
    }
    
    private var calendarSelection: CalendarSelection?
    
}
