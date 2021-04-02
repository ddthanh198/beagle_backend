package com.example.beagleProject.model

import com.example.beagleProject.common.Constants

data class LanguageOption(
        val languageIconUrl: String,
        val language: String
)

fun populateLanguageOptions() = listOf(
    LanguageOption(
            languageIconUrl = "${Constants.baseUrl}/resourcesController/flag_uk",
            language = "English"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.baseUrl}/resourcesController/flag_vn",
            language = "Vietnamese"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.baseUrl}/resourcesController/flag_de",
            language = "German"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.baseUrl}/resourcesController/flag_es",
            language = "Spanish"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.baseUrl}/resourcesController/flag_pt",
            language = "Portuguese"
    )
)