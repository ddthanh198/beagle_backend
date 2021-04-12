package com.viettel.beaglebff.components.compose_components

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Text
import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.components.actions.OpenDateRangePicker
import com.viettel.beaglebff.components.widgets.MyFloatingButton

class CustomFloatButton(
    val image: String,
    val backgroundColor: String,
    val onPress: List<Action>
) : ComposeComponent {
    private val style = Style(
        backgroundColor = null,
        positionType = PositionType.ABSOLUTE,
        position = EdgeValue(bottom = 0.unitReal(), right = 0.unitReal())
    )

    override fun build(): ServerDrivenComponent {
        return Container(
            children = listOf(
                Container(
                    children = listOf(
                        Touchable(
                            child = MyFloatingButton(image, backgroundColor),
                            onPress = onPress
                        )
                    )
                ).applyStyle(
                    Style(
                        margin = EdgeValue(right = 20.unitReal(), bottom = 20.unitReal()),
                        size = Size(width = 44.unitReal(), height = 44.unitReal())
                    )
                )
            )
        ).applyStyle(style)
    }
}