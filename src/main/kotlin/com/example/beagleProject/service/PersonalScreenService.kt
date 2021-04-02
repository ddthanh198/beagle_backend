package com.example.beagleProject.service

import com.example.beagleProject.builder.PersonalScreenBuilder
import org.springframework.stereotype.Service

@Service
class PersonalScreenService {
    fun createPersonalScreen() = PersonalScreenBuilder()
}