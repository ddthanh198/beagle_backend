package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import com.viettel.beaglebff.model.bar_chart.BarModel
import com.viettel.beaglebff.model.bar_chart.HorizontalAxisMetaData
import com.viettel.beaglebff.model.bar_chart.LeftAxisMetadata
import com.viettel.beaglebff.model.bar_chart.RightAxisMetaData

@RegisterWidget
class BarChart(
    val isHorizontalBarChart: Boolean = false,
    val dataset: List<BarModel>,
    val horizontalAxisMetadata: HorizontalAxisMetaData = HorizontalAxisMetaData(),
    val leftAxisMetadata: LeftAxisMetadata = LeftAxisMetadata(),
    val rightAxisMetadata: RightAxisMetaData = RightAxisMetaData(),
    val unit: String,
    val barWidth: Float = 1F,
    val width: Int = 100,
    val height: Int = 100
): Widget()

/*
    isHorizontalBarChart -> check if the bar chart is oriented vertically (default) or horizontally
    dataset -> list of BarModel
    horizontalAxisMetadata -> metadata of the X axis
    leftAxisMetadata -> metadata of the left axis
    rightAxisMetadata -> metadata of the right axis
    unit -> unit of value (for ex: %)
    barWidth -> width of bar, to have space between bars -> decrease bar width to 0.9F, 0.8F,...
    width, height -> size of BarChart, exclusive for iOS
*/

