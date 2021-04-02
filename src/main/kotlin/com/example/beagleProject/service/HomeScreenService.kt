package com.example.beagleProject.service

import com.example.beagleProject.builder.HomeScreenBuilder
import org.springframework.stereotype.Service

@Service
class HomeScreenService {
    fun createHomeScreen() = HomeScreenBuilder()
}