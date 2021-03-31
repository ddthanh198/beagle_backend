package com.vt.beaglebff.service

import com.vt.beaglebff.builder.PersonalScreenBuilder
import org.springframework.stereotype.Service

@Service
class PersonalScreenService {
    fun createPersonalScreen() = PersonalScreenBuilder()
}