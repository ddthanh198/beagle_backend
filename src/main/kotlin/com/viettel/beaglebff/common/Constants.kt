package com.viettel.beaglebff.common

object Constants {
    const val BASE_URL = "http://localhost:8080"
//    const val BASE_URL = "http://10.0.2.2:8080"
    //const val BASE_URL = "http://d5fdf5c622eb.ngrok.io"

    // item size
    const val SMALL_ICON_SIZE = 16
    const val FLOATING_BUTTON_SIZE = 44
    const val DIALOG_RADIUS: Double = 20.0

    // color
    const val COLOR_BLACK = "#000000"
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

    // server-driven components
    enum class CachedFile {
        BottomNavigationView,
        TabHome,
        TabTask,
        TabRequest,
        TabNotification,
        TabChart
    }

    // JSON version
    const val VERSION_BOTTOM_NAV_VIEW = "1.0.0"
    const val VERSION_TAB_HOME = "1.0.0"
    const val VERSION_TAB_TASK = "1.0.0"
    const val VERSION_TAB_REQUEST = "1.0.0"
    const val VERSION_TAB_NOTIFICATION = "1.0.0"
    const val VERSION_TAB_CHART = "1.0.0"

    const val TOKEN = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImYzNzBmZWQ1YzhhYzc2ODVjZGYzNTQzY2ZiYTdlYzYzN2ZkM2U1NzViMjU5Njk0NGY1MTZiNGM5MTFiZDFmZDhhZDAyMWNhMWExMmY3MjM2In0.eyJhdWQiOiIxIiwianRpIjoiZjM3MGZlZDVjOGFjNzY4NWNkZjM1NDNjZmJhN2VjNjM3ZmQzZTU3NWIyNTk2OTQ0ZjUxNmI0YzkxMWJkMWZkOGFkMDIxY2ExYTEyZjcyMzYiLCJpYXQiOjE2MTgxOTc3NjYsIm5iZiI6MTYxODE5Nzc2NiwiZXhwIjoxNjE4MjEyMTY2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.dh2WumOsmGTZ3j74H0FhaeK8MkIrZDj-IzPhe25E-d3XVS90eO8aS9flZIM1uX287JVeFvbyB1pQJQON6SMcy5nAohrOc1FnvZ9EsWTEsc8R3EJ-INe21QgmNKzJO57Se_ViwEH5zAsc5dgeqE4hf__QUmaWSxjMbBw-42siDIk-qDwf8BfJ1wKbDmMgtyKtULu5XNt142FXo9kopiFUiftD9IWp6LdSBe3QMUX-lnBQ-Qarhgt7ysMh4LyjnPQ3bhRetRfwopGKOCFXU6YWc3lr74nXS6qg6CK3Yd0wmgH3QqNSgBQ8xXhGDF7xEg0O4hkGaPadQRwI9_mbpJ40iGcOXgbEd1fCgEy5iHEzow9IEiF5nsD-4NS95JLEk_YVXk8TIyQx20pu8HGBQ5EU0SIKymZ3-Bx35M47jr7Yh8MM_yESsFOyAWiVX60QOxL2MMONA8ww0qXBjTLAWIB4OGVEr2KRRyMUS5YHq99RLw4cYvu3JDPzdcwFJ6uslOR15waqw5M6BFTyQmhGjGmSlS1gkXjZLCnI5uuzYYD0NyzvGJprFlQmYfqIrf3sgjdBNNktFOWeaK6iHUO9LnueFblRl9HCGnjA5N0AIAvESlSxU-9M4tpI8bC-VgvGoZVRLRGyRBrMKRY11IjY9EWq3lVWnHILGF0El-Km9cCt9EU"
}