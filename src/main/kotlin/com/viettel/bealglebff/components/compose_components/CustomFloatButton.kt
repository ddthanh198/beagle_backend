package com.viettel.bealglebff.components.compose_components

import br.com.zup.beagle.annotation.RegisterWidget
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

class CustomFloatButton(
    val title: String,
    val textColor: String? = null,
    val backgroundColor: String? = null,
    val alignItems: AlignItems,
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
                Touchable(
                    child = Container(
                        children = listOf(
                            Text(
                                text = title,
                                textColor = textColor
                            )
                        )
                    ).applyStyle(
                        Style(
                            backgroundColor = backgroundColor,
                            cornerRadius = CornerRadius(22.0),
                            size = Size(width = 44.unitReal(), height = 44.unitReal()),
                            margin = EdgeValue(right = 20.unitReal(), bottom = 20.unitReal()),
                            flex = Flex(
                                alignItems = AlignItems.CENTER,
                                justifyContent = JustifyContent.CENTER
                            )
                        )
                    ),
                    onPress = onPress
                )
            )
        ).applyStyle(style)
    }
}