package com.viettel.bealglebff.service

import com.viettel.bealglebff.builder.TabBuilder
import org.springframework.stereotype.Service

@Service
class TabService {
    fun createTabHome() = TabBuilder.createTabHome()

    fun createTabRequest() = TabBuilder.createTabRequest()

    fun createTabTask() = TabBuilder.createTabTask()

    fun createTabNotification() = TabBuilder.createTabNotification()

    fun createTabChart() = TabBuilder.createTabChart()
}