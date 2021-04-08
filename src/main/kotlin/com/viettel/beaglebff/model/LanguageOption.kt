package com.viettel.beaglebff.model

import com.viettel.beaglebff.common.Constants

data class LanguageOption(
        val languageIconUrl: String,
        val language: String
)

fun populateLanguageOptions() = listOf(
    LanguageOption(
            languageIconUrl = "${Constants.BASE_URL}/resourcesController/flag_uk",
            language = "English"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.BASE_URL}/resourcesController/flag_vn",
            language = "Vietnamese"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.BASE_URL}/resourcesController/flag_de",
            language = "German"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.BASE_URL}/resourcesController/flag_es",
            language = "Spanish"
    ),
    LanguageOption(
            languageIconUrl = "${Constants.BASE_URL}/resourcesController/flag_pt",
            language = "Portuguese"
    )
)