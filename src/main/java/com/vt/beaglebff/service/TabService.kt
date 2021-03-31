package com.vt.beaglebff.service

import com.vt.beaglebff.builder.TabBuilder
import org.springframework.stereotype.Service

@Service
class TabService {
    fun createTabHome() = TabBuilder.createTabHome()

    fun createTabRequest() = TabBuilder.createTabRequest()

    fun createTabTask() = TabBuilder.createTabTask()

    fun createTabNotification() = TabBuilder.createTabNotification()
}