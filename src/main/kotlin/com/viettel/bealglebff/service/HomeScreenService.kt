package com.viettel.bealglebff.service

import com.viettel.bealglebff.builder.HomeScreenBuilder
import org.springframework.stereotype.Service

@Service
class HomeScreenService {
    fun createHomeScreen() = HomeScreenBuilder()
}