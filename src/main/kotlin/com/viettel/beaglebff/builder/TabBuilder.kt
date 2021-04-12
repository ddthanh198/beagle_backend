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
import com.viettel.beaglebff.components.widgets.BarChart
import com.viettel.beaglebff.model.populateData

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