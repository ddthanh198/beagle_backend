package com.viettel.bealglebff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import com.viettel.bealglebff.common.Constants

class AccountInformationScreenBuilder: ScreenBuilder, BaseBuilder() {

    override fun build() = Screen(
            child = createContainer(
                    WidgetBuilder.createMainToolbar(),
                    createContainer().applyStyle(
                            Style(
                                    backgroundColor = Constants.colorWhite,
                                    flex = Flex(flex = 1.0),
                                    margin = EdgeValue(top = 24.unitReal(), horizontal = 0.unitReal(), bottom = (-100).unitReal()),
                                    cornerRadius = CornerRadius(24.0)
                            )
                    )
            ).applyStyle(
                    Style(
                            flex = Flex(grow = 1.0)
                    )
            ),
            style = Style(
                    backgroundColor = Constants.colorPrimary
            )
    )
}