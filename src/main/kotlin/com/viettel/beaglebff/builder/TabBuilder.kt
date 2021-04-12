package com.viettel.bealglebff.builder

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
import com.viettel.beaglebff.builder.BaseBuilder
import com.viettel.beaglebff.builder.WidgetBuilder
import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.components.compose_components.CustomFloatButton
import com.viettel.beaglebff.components.compose_components.FloatingButton
import com.viettel.beaglebff.model.populateData
import com.viettel.bealglebff.components.widgets.BarChartWidget
import com.viettel.bealglebff.model.BarChart
import com.viettel.bealglebff.model.BarChartData

object TabBuilder: BaseBuilder(){

    // tab home
    fun createTabHome() = Screen(
        child = createContainer(
            WidgetBuilder.createToolbar(),
            WidgetBuilder.createBannerView(),
            CustomFloatButton(
                image = "ic_stats",
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
                      size = Size(width = 150.unitReal(), height = 40.unitReal()),
                      flex = Flex(
                          alignSelf = AlignSelf.CENTER,
                          justifyContent = JustifyContent.CENTER
                      )
                  )
              ),
              BarChartWidget(getDataChart(),true,false, true,"ton",width = 100, height = 200).applyStyle(
                  Style(
                      size = Size(width = 100.unitReal(), height = 200.unitReal()),
                      flex = Flex(
                          alignSelf = AlignSelf.CENTER,

                      )
                  )
              ),
              createLegendBarChart(color = "#a50000", "Thành phố Hà Nội"),
              createLegendBarChart("#a5dd00", "Thành phố Hồ Chí Minh"),
              createLegendBarChart("#a5ddc7", "Thành phố Cần Thơ"),
              createLegendBarChart("#ebdd70", "Thành Phố Đà Nẵng"),

          ).applyFlex(
              Flex(
                  grow = 1.0,
                  flexDirection = FlexDirection.COLUMN
              )
          )
     }

     fun getDataChart(): ArrayList<BarChart> {
          var barChartData = ArrayList<BarChart>()
          barChartData.addAll(
              listOf(
                  BarChart(1.0, 11.0,null, "Thành phố Hà Nội", "#a50000"),
                  BarChart(2.0, 12.0, null, "Thành phố Hồ Chí Minh",  "#a5dd00"),
                  BarChart(3.0, 13.0,null, "Thành Phố Cần Thơ", "#a5ddc7"),
                  BarChart(4.0, 14.0, null, "Thành Phố Đà Nẵng", "#ebdd70")
              )
          )
          return barChartData
     }
}