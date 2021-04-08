package com.viettel.beaglebff.service

import com.viettel.beaglebff.builder.WidgetBuilder
import org.springframework.stereotype.Service

@Service
class WidgetService {
    fun createBottomNavigationView() = WidgetBuilder.createBottomNavigationView()

    fun createBannerView() = WidgetBuilder.createBannerView()

    fun createSelectLanguageDialog() = WidgetBuilder.createLanguageSelectionDialog()

    fun createBottomSheetDialog() = WidgetBuilder.createDemoBottomSheetDialog()
}