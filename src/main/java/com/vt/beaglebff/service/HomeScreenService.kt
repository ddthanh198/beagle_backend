package com.vt.beaglebff.service

import com.vt.beaglebff.builder.HomeScreenBuilder
import org.springframework.stereotype.Service

@Service
class HomeScreenService {
    fun createHomeScreen() = HomeScreenBuilder()
}