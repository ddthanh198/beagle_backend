package com.viettel.beaglebff.components.compose_components

import br.com.zup.beagle.core.CornerRadius
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

class CustomButton(
    val title: String,
    var textColor: String,
    var backgroundColor: String,
    var radius: Double,
    var styleId: String? = null,
    var onPress: List<Action>
): ComposeComponent {
    private val style = Style(
        backgroundColor = backgroundColor,
        cornerRadius = CornerRadius(radius),
        margin = EdgeValue(horizontal = 20.unitReal()),
        size = Size(height = 44.unitReal()),
        flex = Flex(
            alignItems = AlignItems.CENTER,
            justifyContent = JustifyContent.CENTER
        )
    )

    override fun build(): ServerDrivenComponent {
        return Touchable(
            child = Container(
                children = listOf(
                    Text(title, textColor = textColor, styleId = styleId)
                )
            ).applyStyle(style),
            onPress = onPress
        )
    }
}