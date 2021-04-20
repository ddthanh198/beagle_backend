//
//  Constants.swift
//  govapp
//
//  Created by minhpv21 on 12/24/20.
//

import Foundation

enum AppConstants {
    // Biometric
    static let LOGIN_BY_TOUCHID = "Đăng nhập bằng vân tay"
    static let LOGIN_BY_FACEID = "Đăng nhập bằng FaceID"
    
    // Errors
    static let VALIDATE_EMTY = "%@ không được để trống"
    static let INVALID_EMAIL = "Email không hợp lệ"
    static let INVALID_BIRTHDAY = "Ngày sinh không hợp lệ. Tuổi trên 18"
    static let INVALID_PHONE = "Số điện thoại không hợp lệ"
    static let INVALID_IDENTITY = "Số CMND/ CCCD không hợp lệ"
    static let INVALID_PASSWORD = "Mật khẩu mới phải là mật khẩu mạnh (mật khẩu có ít nhất 8 ký tự bao gồm ký tự chữ thường, chữ hoa, số và ký tự đặc biệt)"
    static let INVALID_ACCOUNT = "Tên đăng nhập hoặc mật khẩu không đúng"
    static let INVALID_CAPTCHA = "Mã captcha không đúng"
    static let INVALID_OTP = "Mã xác thực không đúng"
    static let INVALID_PASSWORD_2 = "Mật khẩu không được chứa ký tự tiếng Việt có dấu"
    static let PASSWORD_NOT_MATCH = "Mật khẩu nhập lại không khớp với mật khẩu mới"
    static let ACCOUNT_LOCKED = "Tài khoản của bạn đã bị khoá. Vui lòng liên hệ quản trị hệ thống để mở khoá"
    static let VALIDATE_FILE_FORMAT_1 = "Chỉ cho phép file có định dạng .xlsx"
    static let VALIDATE_FILE_FORMAT_2 = "File không đúng định dạng"
    static let VALIDATE_FILE_CAPACITY = "File vượt quá dung lượng cho phép"
    static let INVALID_ADDRESS = "Địa chỉ không tồn tại. Vui long kiểm tra lại"
    static let INVALID_LATITUDE = "Vĩ độ phải nằm trong khoảng [0,180]"
    static let INVALID_LONGTITUDE = "Kinh độ phải nằm trong khoảng [0,180]"
    static let INVALID_FINGER_PRINT = "Không nhận dạng được"
    static let INVALID_DOB = "Ngày sinh không hợp lệ vì chưa đủ 18 tuổi"
    static let CONNECTION_IS_NOT_AVAILABLE = "Không kết nối được với máy chủ"
    static let PRIVATE_CODE_WAS_SENT = "Gửi mã bí mật thành công"
    
    // Character checker
    static let VIETNAMESE_CHARACTERS = "áàạảãâấầậẩẫăắằặẳẵÁÀẠẢÃÂẤẦẬẨẪĂẮẰẶẲẴéèẹẻẽêếềệểễÉÈẸẺẼÊẾỀỆỂỄóòọỏõôốồộổỗơớờợởỡÓÒỌỎÕÔỐỒỘỔỖƠỚỜỢỞỠúùụủũưứừựửữÚÙỤỦŨƯỨỪỰỬỮíìịỉĩÍÌỊỈĨđĐýỳỵỷỹÝỲỴỶỸ"
    static let SPECIAL_CHARACTERS = ["[","!","@","#","$","%","^","&","*","(",")","_","+","\\","-","=","]","{","}",";","'",":","\"","|",",",".","<",">","/","?","]","+"]
    
    // SOS
    static let SOS_PHONE_NUMBER = "18008080"
    static let SOS_EMAIL = "cskh_vts@viettel.com.vn"
    
    // Date Format
    static let SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    static let SERVER_SECOND_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
    static let STANDARD_DATE_FORMAT = "dd/MM/yyyy HH:mm"
    static let STANDARD_SECOND_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"
    
    // Configs
    static let FEEDBACK_MANAGEMENT_TITLE = "Cảnh báo"
    static let FEEDBACK_MANAGEMENT_TAB_NAME = "Quản lý cảnh báo"
    static let FEEDBACK_MANAGEMENT_PLACEHOLDER = "Nhập nội dung cảnh báo"
    static let FEEDBACK_DETAIL_TITLE = "Chi tiết cảnh báo"
    static let FEEDBACK_MANAGEMENT_ADVANCE_SEARCH_PARAM = """
    {"label":"Tên phản ánh","field":"name","value":"%@","hidden_mobile":false,"compare":"and","condition":"like","type":"text","config":{"typeField":"local","value":[]}}
    """
    static let NEWS_CONFIRMATION_MANAGEMENT_TITLE = "Quản lý xác minh"
    static let NEWS_CONFIRMATION_MANAGEMENT_TAB_NAME = "Quản lý xác minh"
    static let NEWS_CONFIRMATION_MANAGEMENT_PLACEHOLDER = "Nhập nội dung yêu cầu xác minh"
    static let NEWS_CONFIRMATION_DETAIL_TITLE = "Chi tiết yêu cầu xác minh"
    static let NEWS_CONFIRMATION_ADVANCE_SEARCH_PARAM = """
    {"compare":"and","condition":"like","config":{"typeField":"local","value":[]},"field":"content","hidden_mobile":false,"label":"noi dung","type":"text","value":"%@"}
    """
    
    // Status message
    static let SUCCESS_MESSAGE = "success"
    
    static let TIME_OUT = 30.0
}
