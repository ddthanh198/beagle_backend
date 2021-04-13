package com.viettel.beaglebff.builder

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.*
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.Route
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.ui.*
import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.components.compose_components.CustomFloatButton
import com.viettel.beaglebff.components.compose_components.FloatingButton
import com.viettel.beaglebff.model.populateData
import com.viettel.beaglebff.components.widgets.BarChart
import com.viettel.beaglebff.model.bar_chart.BarModel
import com.viettel.beaglebff.model.bar_chart.LeftAxisMetadata
import com.viettel.beaglebff.model.bar_chart.RightAxisMetaData

object TabBuilder: BaseBuilder(){

    // tab home
    fun createTabHome() = Screen(
        child = createContainer(
            WidgetBuilder.createToolbar(),
            WidgetBuilder.createBannerView(),
            WidgetBuilder.createGridView(),
            FloatingButton(
                remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_stats",
                backgroundColor = Constants.COLOR_PRIMARY,
                onPress = listOf(
                    Navigate.PushView(route = Route.Remote("/screenController/statistics"))
                )
            )
        ).applyFlex(
            flex = Flex(
                grow = 1.0
            )
        )
    )

    // tab request
    fun createTabRequest() : Container {
        val characters = populateData()
        return createContainer(
                ListView(
                        context = ContextData(id = "characters", value = characters),
                        dataSource = expressionOf("@{characters}"),
                        template = createContainer(
                                createTextView("Name: @{item.name}"),
                                createTextView("Race: @{item.race}"),
                                createTextView("Mistborn: @{item.isMistborn}"),
                                createTextView("Planet: @{item.planet}"),
                                createTextView("sex: @{item.sex}"),
                                createTextView("age: @{item.age}")
                        ).applyStyle(
                                Style(
                                        margin = EdgeValue(bottom = 20.unitReal())
                                )
                        )
                )
        )
    }


    // tab task
    fun createTabTask() = createContainer(
            createScrollView(
                    createTextView("Vertical ScrollView"),
                    createTextView("Vertical ScrollView"),
                    createTextView("Vertical ScrollView")
            )
    )

    // tab notification
    fun createTabNotification() = createContainer(
            createScrollView(
                    createTextView("Vertical ScrollView"),
                    createTextView("Vertical ScrollView"),
                    createTextView("Vertical ScrollView")
            )
    )

     // tab Chart
     fun createTabChart(): Container {
          return createContainer(
              createTextView("Biểu đồ lượng mưa").applyStyle(
                  Style(
                      margin = EdgeValue(top = 10.unitReal()),
                      flex = Flex(
                          alignSelf = AlignSelf.CENTER
                      )
                  )
              ),
              BarChart(
                  dataset = getDataChart(),
                  leftAxisMetadata = LeftAxisMetadata(textSize = 13F),
                  rightAxisMetadata = RightAxisMetaData(isRightAxisEnabled = false),
                  unit = "tons",
                  barWidth = 0.45F,
                  width = 100,
                  height = 200
              ).applyStyle(
                  Style(
                      size = Size(width = 400.unitReal(), height = 200.unitReal()),
                      margin = EdgeValue(vertical = 20.unitReal()),
                      flex = Flex(
                          alignSelf = AlignSelf.CENTER
                      )
                  )
              ),
              createLegendBarChart("#a50000", "Thành phố Hà Nội"),
              createLegendBarChart("#a5dd00", "Thành phố Hồ Chí Minh"),
              createLegendBarChart("#a5ddc7", "Thành phố Cần Thơ"),
              createLegendBarChart("#ebdd70", "Thành Phố Đà Nẵng")
          ).applyFlex(
              Flex(
                  grow = 1.0,
                  flexDirection = FlexDirection.COLUMN
              )
          )
     }

     private fun getDataChart(): List<BarModel> {
          val colorList = listOf("#a50000", "#a5dd00", "#a5ddc7", "#ebdd70")
          val barChartData = ArrayList<BarModel>()
          barChartData.addAll(
              listOf(
                  BarModel(1F, floatArrayOf(11F, 0F, 0F, 0F),null, "Thành phố Hà Nội", colorList),
                  BarModel(2F, floatArrayOf(0F, 12F, 0F, 0F), null, "Thành phố Hồ Chí Minh",  colorList),
                  BarModel(3F, floatArrayOf(0F, 0F, 13F, 0F),null, "Thành Phố Cần Thơ", colorList),
                  BarModel(4F, floatArrayOf(0F, 0F, 0F, 14F), null, "Thành Phố Đà Nẵng", colorList)
              )
          )
          return barChartData
     }
}