package com.viettel.bealglebff.service

import com.viettel.bealglebff.builder.AccountInformationScreenBuilder
import com.viettel.bealglebff.builder.HomeScreenBuilder
import com.viettel.bealglebff.builder.PersonalScreenBuilder
import com.viettel.bealglebff.builder.StatisticsScreenBuilder
import org.springframework.stereotype.Service

@Service
class ScreenService {
    fun createHomeScreen() = HomeScreenBuilder()

    fun createPersonalScreen() = PersonalScreenBuilder()

    fun createAccountInformationScreen() = AccountInformationScreenBuilder()

    fun createStatisticsScreen() = StatisticsScreenBuilder()
}