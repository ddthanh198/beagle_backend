package com.viettel.beaglebff.service

import com.viettel.beaglebff.builder.TabBuilder
import org.springframework.stereotype.Service

@Service
class TabService {
    fun createTabHome() = TabBuilder.createTabHome()

    fun createTabRequest() = TabBuilder.createTabRequest()

    fun createTabTask() = TabBuilder.createTabTask()

    fun createTabNotification() = TabBuilder.createTabNotification()

    fun createTabChart() = TabBuilder.createTabChart()
}