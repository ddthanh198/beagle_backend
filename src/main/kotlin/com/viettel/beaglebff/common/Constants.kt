package com.viettel.beaglebff.common

object Constants {
    const val BASE_URL = "http://localhost:8080"
//    const val BASE_URL = "http://10.0.2.2:8080"
    //const val BASE_URL = "http://38e983b18b0e.ngrok.io"

    // item size
    const val SMALL_ICON_SIZE = 16
    const val FLOATING_BUTTON_SIZE = 44
    const val DIALOG_RADIUS: Double = 20.0

    // color
    const val COLOR_WHITE = "#FFFFFF"
    const val COLOR_PRIMARY = "#3596EC"
    const val COLOR_GRAY_DARK = "#788793"
    const val COLOR_DIVIDER = "#B9B9B9"
    const val COLOR_RED = "#DD3030"
    const val COLOR_CHART_GRAY = "#617882"
    const val COLOR_CHART_BLUE = "#1790C9"
    const val COLOR_CHART_GREEN = "#0F8E70"
    const val COLOR_CHART_ORANGE = "#E17126"

    // endpoints
    const val SCREEN_CONTROLLER_ENDPOINT = "/screenController"
    const val RESOURCE_CONTROLLER_ENDPOINT = "/resourcesController"
    const val WIDGET_CONTROLLER_ENDPOINT = "/widgetController"

    const val ACCOUNT_INFO_SCREEN_ENDPOINT = "/accountInformation"
    const val PERSONAL_SCREEN_ENDPOINT = "/accountInformation"

    const val TAB_HOME_ENDPOINT = "/tabHome"
    const val TAB_REQUEST_ENDPOINT = "/tabRequest"
    const val TAB_TASK_ENDPOINT = "/tabTask"
    const val TAB_NOTIFICATION_ENDPOINT = "/tabNotification"

    const val BOTTOM_NAV_ENDPOINT = "/bottomNavigationView"
    const val BANNER = "/banner"
    const val SELECT_LANGUAGE_DIALOG = "/selectLanguageDialog"
    const val BOTTOM_SHEET_DIALOG = "/bottomSheetDialog"
}