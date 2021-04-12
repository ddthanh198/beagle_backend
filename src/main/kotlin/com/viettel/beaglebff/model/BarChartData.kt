package com.viettel.bealglebff.model

import javafx.scene.chart.Axis

data class BarChartData(val barchartDataSet: ArrayList<BarChart>, val isEnableLeftAxis: Boolean, val isEnableRightAxis: Boolean,
                        val isEnableXAxis: Boolean ,val prefix: String, val title: String
)

