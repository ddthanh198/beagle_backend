package com.viettel.beaglebff.components.compose_components

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
import com.viettel.beaglebff.common.Constants.COLOR_DIVIDER

class HorizontalDivider(
    val marginTop: Int = 0,
    val marginBottom: Int = 0,
    val marginLeft: Int = 0,
    val marginRight: Int = 0
) : ComposeComponent {
    override fun build(): ServerDrivenComponent {
        return Container(children = listOf())
            .applyStyle(
                Style(
                    backgroundColor = COLOR_DIVIDER,
                    size = Size(height = 0.75.unitReal()),
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