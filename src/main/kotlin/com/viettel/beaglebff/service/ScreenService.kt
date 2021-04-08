package com.viettel.beaglebff.service

import com.viettel.beaglebff.builder.AccountInformationScreenBuilder
import com.viettel.beaglebff.builder.HomeScreenBuilder
import com.viettel.beaglebff.builder.PersonalScreenBuilder
import com.viettel.beaglebff.builder.StatisticsScreenBuilder
import org.springframework.stereotype.Service

@Service
class ScreenService {
    fun createHomeScreen() = HomeScreenBuilder()

    fun createPersonalScreen() = PersonalScreenBuilder()

    fun createAccountInformationScreen() = AccountInformationScreenBuilder()

    fun createStatisticsScreen() = StatisticsScreenBuilder()
}