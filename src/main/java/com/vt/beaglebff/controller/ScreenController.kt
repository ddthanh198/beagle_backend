package com.vt.beaglebff.controller

import com.vt.beaglebff.service.HomeScreenService
import com.vt.beaglebff.service.PersonalScreenService
import com.vt.beaglebff.service.TabService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/screenController")
class ScreenController(
        private val homeScreenService: HomeScreenService,
        private val personalScreenService: PersonalScreenService,
        private val tabService: TabService
) {
    @GetMapping("/home")
    fun getHomeScreen() = homeScreenService.createHomeScreen()

    @GetMapping("/personal")
    fun getPersonalScreen() = personalScreenService.createPersonalScreen()

    @GetMapping("/tabHome")
    fun getTabHome() = tabService.createTabHome()

    @GetMapping("/tabRequest")
    fun getTabRequest() = tabService.createTabRequest()

    @GetMapping("/tabTask")
    fun getTabTask() = tabService.createTabTask()

    @GetMapping("/tabNotification")
    fun getTabNotification() = tabService.createTabNotification()
}