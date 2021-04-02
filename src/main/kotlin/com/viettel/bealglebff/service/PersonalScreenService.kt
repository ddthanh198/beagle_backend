package com.viettel.bealglebff.service

import com.viettel.bealglebff.builder.PersonalScreenBuilder
import org.springframework.stereotype.Service

@Service
class PersonalScreenService {
    fun createPersonalScreen() = PersonalScreenBuilder()
}