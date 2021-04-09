package com.viettel.beaglebff.builder

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import com.viettel.beaglebff.common.Constants.COLOR_CHART_BLUE
import com.viettel.beaglebff.common.Constants.COLOR_CHART_GRAY
import com.viettel.beaglebff.common.Constants.COLOR_CHART_GREEN
import com.viettel.beaglebff.common.Constants.COLOR_CHART_ORANGE
import com.viettel.beaglebff.components.compose_components.CircularDot
import com.viettel.beaglebff.components.widgets.PieChart
import com.viettel.beaglebff.model.PieChartSlice
import com.viettel.bealglebff.components.compose_components.HorizontalDivider

class StatisticsScreenBuilder: ScreenBuilder, BaseBuilder() {

    companion object {
        private const val NORMAL_MARGIN = 16
        private const val DOT_SIZE = 16
        private const val LEGEND_TEXT_MARGIN = 4
        private const val LEGEND_ITEM_MARGIN = 32
        private const val CHART_HEIGHT = 300
        private const val CHART_WIDTH = 300
    }

    override fun build() = Screen(
        child = createContainer(
            WidgetBuilder.createMainToolbar("Thống kê"),
            createContainer(
                createScrollView(
                    createTextView(
                        text = "Tình hình xử lý phản ánh",
                        styleId = "NormalBoldText"
                    ).applyFlex(
                        Flex(alignSelf = AlignSelf.CENTER)
                    ),
                    PieChart(
                        dataset = getChartData(),
                        sliceSpace = 3F,
                        isHoleNeeded = true,
                        holeRadius = 40f,
                        holeText = "15.243\nphản ánh"
                    ).applyStyle(
                        Style(
                            size = Size(height = CHART_HEIGHT.unitReal(), width = CHART_WIDTH.unitReal()),
                            flex = Flex(alignSelf = AlignSelf.CENTER)
                        )
                    ),
                    createContainer(
                        createContainer(
                            createLegendItem(COLOR_CHART_GREEN, "Chưa xử lý"),
                            createLegendItem(COLOR_CHART_GRAY, "Đã xử lý")
                        ).applyFlex(
                            Flex(
                                flexDirection = FlexDirection.COLUMN
                            )
                        ),
                        createContainer(
                            createLegendItem(COLOR_CHART_BLUE, "Đang xử lý"),
                            createLegendItem(COLOR_CHART_ORANGE, "Quá hạn")
                        ).applyStyle(
                            Style(
                                flex = Flex(
                                    flexDirection = FlexDirection.COLUMN
                                ),
                                margin = EdgeValue(left = LEGEND_ITEM_MARGIN.unitReal())
                            )
                        )
                    ).applyFlex(
                        Flex(
                            flexDirection = FlexDirection.ROW,
                            alignSelf = AlignSelf.CENTER
                        )
                    ),
                    HorizontalDivider(marginTop = NORMAL_MARGIN, marginBottom = NORMAL_MARGIN, marginLeft = NORMAL_MARGIN, marginRight = NORMAL_MARGIN)
                )
            ).applyStyle(Style(margin = EdgeValue(top = NORMAL_MARGIN.unitReal())))
        )
    )

//    private fun getChartData(): ArrayList<Array<String>> {
//        val dataset = ArrayList<Array<String>>()
//
//        dataset.addAll(
//            listOf(
//                arrayOf("48", COLOR_CHART_GRAY),
//                arrayOf("17", COLOR_CHART_BLUE),
//                arrayOf("25", COLOR_CHART_GREEN),
//                arrayOf("10", COLOR_CHART_ORANGE)
//            )
//        )
//
//        return dataset
//    }

    private fun getChartData(): List<PieChartSlice> {
        val dataset = ArrayList<PieChartSlice>()

        dataset.addAll(
            listOf(
                PieChartSlice(48F, COLOR_CHART_GRAY),
                PieChartSlice(17F, COLOR_CHART_BLUE),
                PieChartSlice(25F, COLOR_CHART_GREEN),
                PieChartSlice(10F, COLOR_CHART_ORANGE)
            )
        )

        return dataset
    }

    private fun createLegendItem(dotColor: String, legendText: String) = createContainer(
        CircularDot(DOT_SIZE, dotColor),
        createTextView(legendText).applyStyle(
            Style(
                margin = EdgeValue(
                    horizontal = LEGEND_TEXT_MARGIN.unitReal(),
                    vertical = LEGEND_TEXT_MARGIN.unitReal()
                )
            )
        )
    ).applyFlex(
        Flex(
            flexDirection = FlexDirection.ROW,
            alignItems = AlignItems.CENTER
        )
    )
}