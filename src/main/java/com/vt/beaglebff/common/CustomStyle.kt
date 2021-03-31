package com.vt.beaglebff.common

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*

object CustomStyle {
    val buttonStyle = Style(margin = EdgeValue(
            left = 16.unitReal(),
            right = 16.unitReal(),
            top = 8.unitReal(),
            bottom = 8.unitReal()
    )
    )

    val iconStyle = Style(
            size = Size(width = 40.unitReal(), height = 40.unitReal()),
            flex = Flex(alignSelf = AlignSelf.FLEX_END),
            margin = EdgeValue(
                    left = 16.unitReal()
            )
    )

    val textStyle = Style(
            size = Size(
                    width = UnitValue(
                            33.0, type = UnitType.PERCENT
                    )
            )
    )
}
