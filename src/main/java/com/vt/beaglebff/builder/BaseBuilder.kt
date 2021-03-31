package com.vt.beaglebff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.ScrollView
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text
import br.com.zup.beagle.widget.ui.TextInput
import com.vt.beaglebff.common.Constants

abstract class BaseBuilder {
    protected fun createTextView(text: String, styleId: String? = "NormalText", textAlignment: TextAlignment? = TextAlignment.LEFT) = Text(
            text = text,
            styleId = styleId,
            alignment = textAlignment
    )

    protected fun createTextInput(hint: String, styleId: String? = "HintText") = TextInput(
            styleId = styleId,
            placeholder = hint
    )

    protected fun createContainer(vararg children: ServerDrivenComponent) = Container(
            children = children.asList()
    )

    protected fun createScrollView(vararg children: ServerDrivenComponent, scrollDirection: ScrollAxis? = ScrollAxis.VERTICAL) = ScrollView(
            scrollDirection = scrollDirection,
            children = children.asList()
    )

    protected fun createImageViewFromRemote(remoteUrl: String, mode: ImageContentMode? = ImageContentMode.CENTER_CROP) = Image(
            path = ImagePath.Remote(remoteUrl),
            mode = mode
    )

    protected fun createImageViewFromLocal(mobileId: String, mode: ImageContentMode? = ImageContentMode.CENTER_CROP) = Image(
            path = ImagePath.Local.justMobile(mobileId),
            mode = mode
    )

    protected fun createTouchableIcon(
            remoteUrl: String,
            width: Int,
            height: Int,
            listAction: List<Action>
    ) : Touchable {
        return Touchable(
                child = Image(
                        path = ImagePath.Remote(remoteUrl)
                ).applyStyle(
                        style = Style(
                                size = Size(width = width.unitReal(), height = height.unitReal())
                        )
                ),
                onPress = listAction
        )
    }

    // circular text view
    protected fun createCircularTextView(
            text: String,
            styleId: String? = "NormalText",
            backgroundColor: String? = Constants.colorWhite,
            width: Int = 44,
            height: Int = 44,
            listAction: List<Action>
    ) = Touchable(
            child = Text(
                    text = text,
                    styleId = styleId,
                    alignment = TextAlignment.CENTER
            ).applyStyle(
                    style = Style(
                            size = Size(
                                    width = width.unitReal(),
                                    height = height.unitReal()
                            ),
                            margin = EdgeValue(vertical = 8.unitReal(), horizontal = 8.unitReal()),
                            cornerRadius = CornerRadius(radius = (width/2).toDouble()),
                            backgroundColor = backgroundColor
                    )
            ),
            onPress = listAction
    )

    protected fun createContextData(id: String, vararg value: Any): ContextData {
        return ContextData(
                id = id,
                value = value.asList()
        )
    }
}