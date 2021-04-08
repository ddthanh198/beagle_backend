package com.viettel.bealglebff.components.compose_components

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container

class CircularDot(private val dotSize: Int, private val dotColor: String): ComposeComponent {
    override fun build(): ServerDrivenComponent {
        return Container(listOf()).applyStyle(
            Style(
                size = Size(height = dotSize.unitReal(), width = dotSize.unitReal()),
                backgroundColor = dotColor,
                cornerRadius = CornerRadius((dotSize/2).toDouble())
            )
        )
    }
}