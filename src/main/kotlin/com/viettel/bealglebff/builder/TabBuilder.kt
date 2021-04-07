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
import com.viettel.bealglebff.common.Constants
import com.viettel.bealglebff.components.compose_components.CustomFloatButton
import com.viettel.bealglebff.components.compose_components.FloatingButton
import com.viettel.bealglebff.components.widgets.BarChart
import com.viettel.bealglebff.model.populateData

object TabBuilder: BaseBuilder(){

    // tab home
    fun createTabHome() = Screen(
        child = createContainer(
            WidgetBuilder.createToolbar(),
            WidgetBuilder.createBannerView(),
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
    fun createTabNotification(): Container {
        return createContainer(
            createScrollView(
                createTextView("Vertical ScrollView"),
                createTextView("Vertical ScrollView"),
                createTextView("Vertical ScrollView"),
            )
        )
    }

     // tab Chart
     fun createTabChart() = createContainer(
         BarChart()
     )
}