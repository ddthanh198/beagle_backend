package com.viettel.bealglebff.components.compose_components

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
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import com.viettel.bealglebff.common.Constants
import com.viettel.bealglebff.common.Constants.FLOATING_BUTTON_SIZE
import com.viettel.bealglebff.common.Constants.SMALL_ICON_SIZE

class FloatingButton(
    val remoteIconUrl: String,
    val backgroundColor: String = Constants.COLOR_PRIMARY,
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
                            Image(
                                path = ImagePath.Remote(remoteIconUrl),
                                mode = ImageContentMode.CENTER_CROP
                            ).applyStyle(
                                Style(size = Size(height = SMALL_ICON_SIZE.unitReal(), width = SMALL_ICON_SIZE.unitReal()))
                            )
                        )
                    ).applyStyle(
                        Style(
                            backgroundColor = backgroundColor,
                            cornerRadius = CornerRadius((FLOATING_BUTTON_SIZE/2).toDouble()),
                            size = Size(width = FLOATING_BUTTON_SIZE.unitReal(), height = FLOATING_BUTTON_SIZE.unitReal()),
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