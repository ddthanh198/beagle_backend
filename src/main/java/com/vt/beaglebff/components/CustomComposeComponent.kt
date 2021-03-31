package com.vt.beaglebff.components

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.core.UnitType
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.Text

class CustomComposeComponent(
        private val field: String,
        private val value: String,
        private val status: String
): ComposeComponent {

    private val style = Style(
            size = Size(
                    width = UnitValue(
                            33.0, type = UnitType.PERCENT
                    )
            )
    )

    // customize view here
    override fun build() = Container(
            children = listOf(
                    createText(field),
                    createText(value),
                    createText(status)
            )
    )

    private fun createText(text: String)
            = Text(text).applyStyle(style = style)

}