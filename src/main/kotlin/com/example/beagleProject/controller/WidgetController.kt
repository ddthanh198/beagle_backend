package com.example.beagleProject.controller

import com.example.beagleProject.service.WidgetService
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

    @GetMapping("/selectionDialog")
    fun getSelectionDialog() = widgetService.createSelectionDialog()
}