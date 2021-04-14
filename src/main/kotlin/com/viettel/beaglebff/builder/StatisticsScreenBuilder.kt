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
import com.viettel.beaglebff.model.pie_chart.PieChartSlice
import com.viettel.beaglebff.components.compose_components.HorizontalDivider
import com.viettel.beaglebff.components.widgets.BarChart
import com.viettel.beaglebff.model.bar_chart.BarModel
import com.viettel.beaglebff.model.bar_chart.HorizontalAxisMetaData
import com.viettel.beaglebff.model.bar_chart.LeftAxisMetadata

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
                    showPieChart(),
                    HorizontalDivider(marginTop = NORMAL_MARGIN, marginBottom = NORMAL_MARGIN, marginLeft = NORMAL_MARGIN, marginRight = NORMAL_MARGIN),
                    BarChart(
                        isHorizontalBarChart = true,
                        dataset = getBarChartData(),
                        horizontalAxisMetadata = HorizontalAxisMetaData(textSize = 13F, displayTitleAsLabel = true),
                        leftAxisMetadata = LeftAxisMetadata(isLeftAxisEnabled = false),
                        unit = "tons",
                        barWidth = 0.85F,
                        width = 100,
                        height = 200
                    ).applyStyle(
                        Style(
                            size = Size(width = 400.unitReal(), height = 400.unitReal()),
                            margin = EdgeValue(vertical = 20.unitReal()),
                            flex = Flex(
                                alignSelf = AlignSelf.CENTER
                            )
                        )
                    )
                )
            ).applyStyle(Style(margin = EdgeValue(top = NORMAL_MARGIN.unitReal())))
        )
    )

    private fun showPieChart() = createContainer(
        createTextView(
            text = "Tình hình xử lý phản ánh",
            styleId = "NormalBoldText"
        ).applyFlex(
            Flex(alignSelf = AlignSelf.CENTER)
        ),
        PieChart(
            dataset = getPieChartData(),
            sliceSpace = 3F,
            isHoleNeeded = true,
            holeRadius = 40f,
            holeText = "15.243\nphản ánh",
            width = CHART_WIDTH,
            height = CHART_HEIGHT
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
        )
    )

    private fun getPieChartData(): List<PieChartSlice> {
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

    private fun getBarChartData(): List<BarModel> {
        val colorList = listOf(COLOR_CHART_ORANGE, COLOR_CHART_GREEN, COLOR_CHART_BLUE)
        val barChartData = ArrayList<BarModel>()
        barChartData.addAll(
            listOf(
                BarModel(1F, floatArrayOf(0F, 48F, 0F),null, "01.2020", colorList),
                BarModel(2F, floatArrayOf(0F, 68F, 0F), null, "02.2020",  colorList),
                BarModel(3F, floatArrayOf(0F, 30F, 0F),null, "03.2020", colorList),
                BarModel(4F, floatArrayOf(0F, 46F, 0F), null, "04.2020", colorList),
                BarModel(5F, floatArrayOf(0F, 23F, 0F),null, "05.2020", colorList),
                BarModel(6F, floatArrayOf(0F, 9F, 0F), null, "06.2020",  colorList),
                BarModel(7F, floatArrayOf(0F, 42F, 0F),null, "07.2020", colorList),
                BarModel(8F, floatArrayOf(0F, 21F, 0F), null, "08.2020", colorList),
                BarModel(9F, floatArrayOf(0F, 28F, 0F),null, "09.2020", colorList),
                BarModel(10F, floatArrayOf(13F, 37F, 0F), null, "10.2020",  colorList),
                BarModel(11F, floatArrayOf(8F, 35F, 4F),null, "11.2020", colorList)
            )
        )
        return barChartData
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