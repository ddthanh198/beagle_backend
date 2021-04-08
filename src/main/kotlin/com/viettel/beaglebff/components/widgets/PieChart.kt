package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget

@RegisterWidget
class PieChart(
    val dataset: ArrayList<Array<String>>,
    val valueTextColor: String = "#ffffff",
    val valueTextSize: Float = 12F,
    val sliceSpace: Float = 3F,
    val isHoleNeeded: Boolean,
    val holeRadius: Float = 0f,
    val holeColor: String = "#ffffff",
    val holeText: String = "",
    val holeTextColor: String = "#000000",
    val holeTextSize: Float = 12F
): Widget()

/*
    dataset -> arrayOf(percent, color)
    valueTextColor -> color of text in each slice
    sliceSpace -> space between slices
    isHoleNeeded -> check if there is a hole centered in the pie chart
*/
