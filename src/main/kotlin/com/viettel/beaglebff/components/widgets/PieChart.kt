package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import com.viettel.beaglebff.model.PieChartSlice

@RegisterWidget
class PieChart(
    val dataset: List<PieChartSlice>,
    val valueTextColor: String = "#ffffff",
    val valueTextSize: Float = 12F,
    val sliceSpace: Float = 3F,
    val isHoleNeeded: Boolean = false,
    val holeRadius: Float = 0f,
    val holeColor: String = "#ffffff",
    val holeText: String = "",
    val holeTextColor: String = "#000000",
    val holeTextSize: Float = 12F,
    val width: Int = 100,
    val height: Int = 100
): Widget()

/*
    dataset -> arrayOf(percent, color)
    valueTextColor -> color of text in each slice
    sliceSpace -> space between slices
    isHoleNeeded -> check if there is a hole centered in the pie chart
*/
