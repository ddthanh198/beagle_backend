package com.viettel.beaglebff.model.bar_chart

import com.viettel.beaglebff.common.Constants

data class LeftAxisMetadata(
    val isLeftAxisEnabled: Boolean = true,
    val textSize: Float = 12F,
    val textColor: String = Constants.COLOR_BLACK,
    val displayUnit: Boolean = false
)

/*
    isLeftAxisEnabled -> check if the left axis needs to be displayed
    textSize -> size of text displayed on the left axis
    textColor -> color of text
    displayUnit -> display y value with unit (for ex: y=30, unit="%" -> 30%)
*/