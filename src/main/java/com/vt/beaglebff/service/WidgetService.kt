package com.vt.beaglebff.service

import com.vt.beaglebff.builder.WidgetBuilder
import org.springframework.stereotype.Service

@Service
class WidgetService {
    fun createBottomNavigationView() = WidgetBuilder.createBottomNavigationView()

    fun createBannerView() = WidgetBuilder.createBannerView()
}