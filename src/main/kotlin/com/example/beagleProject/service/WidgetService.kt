package com.example.beagleProject.service

import com.example.beagleProject.builder.WidgetBuilder
import org.springframework.stereotype.Service

@Service
class WidgetService {
    fun createBottomNavigationView() = WidgetBuilder.createBottomNavigationView()

    fun createBannerView() = WidgetBuilder.createBannerView()

    fun createSelectionDialog() = WidgetBuilder.createLanguageSelectionDialog()
}