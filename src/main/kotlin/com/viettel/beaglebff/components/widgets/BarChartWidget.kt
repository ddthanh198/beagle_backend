package com.viettel.bealglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import com.viettel.bealglebff.model.BarChart
import com.viettel.bealglebff.model.BarChartData

@RegisterWidget
class BarChartWidget(val barchartDataSet: ArrayList<BarChart>, val isEnableLeftAxis: Boolean, val isEnableRightAxis: Boolean,
                     val isEnableXAxis: Boolean ,val prefix: String, val width: Int = 100, val height: Int = 100): Widget() {
}