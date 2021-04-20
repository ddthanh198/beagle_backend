package com.viettel.beaglebff.model.bar_chart

import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.common.XAxisPosition

data class HorizontalAxisMetaData(
    val isXAxisEnabled: Boolean = true,
    val axisPosition: XAxisPosition = XAxisPosition.BOTTOM,
    val textSize: Float = 12F,
    val textColor: String = Constants.COLOR_BLACK,
    val displayTitleAsLabel: Boolean = false,
    val drawGridLinesEnabled: Boolean = false,
    val drawAxisLineEnabled: Boolean = false
)

/*
    isXAxisEnabled -> check if the X axis needs to be displayed
    textSize -> size of text displayed under the X axis
    textColor -> color of text
    displayTitleAsLabel -> display title for each bar instead of the x value as default
    drawGridLinesEnabled -> draw gridline in chart
    drawAxisLineEnabled -> draw axis line in chart
*/