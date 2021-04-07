package com.viettel.bealglebff.components.compose_components

import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.FlexDirection
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import com.viettel.bealglebff.common.Constants

class HorizontalDivider(
    val marginTop: Int,
    val marginBottom: Int,
    val marginLeft: Int,
    val marginRight: Int
) : ComposeComponent {
    override fun build(): ServerDrivenComponent {
        return Container(children = listOf())
            .applyStyle(
                Style(
                    backgroundColor = Constants.COLOR_DIVIDER,
                    size = Size(height = 0.75.unitReal()),
                    flex = Flex(
                        flexDirection = FlexDirection.ROW,
                        grow = 1.0
                    ),
                    margin = EdgeValue(
                        top = marginTop.unitReal(),
                        bottom = marginBottom.unitReal(),
                        left = marginLeft.unitReal(),
                        right = marginRight.unitReal()
                    )
                )
            )
    }
}