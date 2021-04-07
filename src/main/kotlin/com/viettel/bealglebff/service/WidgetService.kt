package com.viettel.bealglebff.service

import com.viettel.bealglebff.builder.WidgetBuilder
import org.springframework.stereotype.Service

@Service
class WidgetService {
    fun createBottomNavigationView() = WidgetBuilder.createBottomNavigationView()

    fun createBannerView() = WidgetBuilder.createBannerView()

    fun createSelectLanguageDialog() = WidgetBuilder.createLanguageSelectionDialog()

    fun createBottomSheetDialog() = WidgetBuilder.createDemoBottomSheetDialog()
}