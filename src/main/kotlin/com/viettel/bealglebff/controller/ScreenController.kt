package com.viettel.bealglebff.controller

import com.viettel.bealglebff.service.MyService
import com.viettel.bealglebff.service.TabService
import com.viettel.bealglebff.service.ScreenService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/screenController")
class ScreenController(
        private val tabService: TabService,
        private val myService: MyService,
        private val screenService: ScreenService
) {
    @GetMapping("/home")
    fun getHomeScreen() = screenService.createHomeScreen()

    @GetMapping("/personal")
    fun getPersonalScreen() = screenService.createPersonalScreen()

    @GetMapping("/accountInformation")
    fun getAccountInformationScreen() = screenService.createAccountInformationScreen()

    @GetMapping("/tabHome")
    fun getTabHome() = tabService.createTabHome()

    @GetMapping("/tabRequest")
    fun getTabRequest() = tabService.createTabRequest()

    @GetMapping("/tabTask")
    fun getTabTask() = myService.getMyScreen()

    @GetMapping("/tabNotification")
    fun getTabNotification() = tabService.createTabNotification()

    @GetMapping("/tabChart")
    fun getTabChart() = tabService.createTabChart()
}