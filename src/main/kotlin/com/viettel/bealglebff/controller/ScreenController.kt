package com.viettel.bealglebff.controller

import com.viettel.bealglebff.service.HomeScreenService
import com.viettel.bealglebff.service.MyService
import com.viettel.bealglebff.service.PersonalScreenService
import com.viettel.bealglebff.service.TabService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/screenController")
class ScreenController(
        private val homeScreenService: HomeScreenService,
        private val personalScreenService: PersonalScreenService,
        private val tabService: TabService,
        private val myService: MyService
) {
    @GetMapping("/home")
    fun getHomeScreen() = homeScreenService.createHomeScreen()

    @GetMapping("/personal")
    fun getPersonalScreen() = personalScreenService.createPersonalScreen()

    @GetMapping("/tabHome")
    fun getTabHome() = myService.getMyScreen()

    @GetMapping("/tabRequest")
    fun getTabRequest() = tabService.createTabRequest()

    @GetMapping("/tabTask")
    fun getTabTask() = tabService.createTabTask()

    @GetMapping("/tabNotification")
    fun getTabNotification() = tabService.createTabNotification()
}