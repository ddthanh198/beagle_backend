package com.viettel.beaglebff.controller

import com.viettel.beaglebff.service.WidgetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/widgetController")
class WidgetController(
        private val widgetService: WidgetService
) {

    @GetMapping("/bottomNavigationView")
    fun getHomeScreenBottomNavigationView() = widgetService.createBottomNavigationView()

    @GetMapping("/banner")
    fun getHomeScreenBannerView() = widgetService.createBannerView()

    @GetMapping("/selectLanguageDialog")
    fun getSelectionDialog() = widgetService.createSelectLanguageDialog()

    @GetMapping("/bottomSheetDialog")
    fun getBottomSheetDialog() = widgetService.createBottomSheetDialog()
}