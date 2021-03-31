package com.vt.beaglebff.builder

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.*
import com.vt.beaglebff.model.populateData

object TabBuilder: BaseBuilder(){

    // tab home
    fun createTabHome() = createContainer(
            WidgetBuilder.createToolbar(),
            WidgetBuilder.createBannerView()
    ).applyFlex(
            flex = Flex(
                    grow = 1.0
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
}