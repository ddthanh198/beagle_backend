package com.viettel.beaglebff.controller

import com.viettel.beaglebff.service.MyService
import com.viettel.beaglebff.service.TabService
import com.viettel.beaglebff.service.ScreenService
import com.viettel.beaglebff.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/screenController")
class ScreenController(
        private val tabService: TabService,
        private val myService: MyService,
        private val screenService: ScreenService,
        private val taskService: TaskService
) {
    @GetMapping("/search")
    fun getSearchScreen() = myService.getMyScreen()

    @GetMapping("/statistics")
    fun getStatisticsScreen() = screenService.createStatisticsScreen()

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

    @GetMapping("/taskFeedback")
    fun getTaskFeedback() = taskService.getTaskScreen()

}