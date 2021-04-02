package com.example.beagleProject.builder

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Text
import com.example.beagleProject.common.Constants
import com.example.beagleProject.common.CustomStyle
import com.example.beagleProject.components.actions.NavigateAction
import com.example.beagleProject.components.actions.ToastAction

class PersonalScreenBuilder : ScreenBuilder, BaseBuilder() {

    override fun build() = Screen(
            child = createContainer(
                    WidgetBuilder.createMainToolbar(),
                    createContainer(
                            createScrollView(
                                    createTouchableRow(
                                            text = "Thông tin tài khoản",
                                            iconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info",
                                            icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                            destination = "account_information"
                                    ),
                                    createDivider(4, 4, 16, 16),
                                    createTouchableRow(
                                            text = "Cài đặt",
                                            iconUrl = "${Constants.baseUrl}/resourcesController/ic_settings",
                                            icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                            destination = "settings"
                                    ),
                                    createDivider(4, 4, 16, 16),
                                    createTouchableRow(
                                            text = "Đổi mật khẩu",
                                            iconUrl = "${Constants.baseUrl}/resourcesController/ic_change_password",
                                            icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                            destination = "change_password"
                                    ),
                                    createDivider(4, 4, 16, 16),
                                    createTouchableRow(
                                            text = "Thông tin",
                                            iconUrl = "${Constants.baseUrl}/resourcesController/ic_info",
                                            icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                            destination = "information"
                                    ),
                                    createDivider(4, 4, 16, 16),
                                    createTouchableRow(
                                            text = "Đăng xuất",
                                            iconUrl = "${Constants.baseUrl}/resourcesController/ic_logout",
                                            icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                            destination = "log_out"
                                    )
                            )
                    ).applyStyle(Style(margin = EdgeValue(top = 8.unitReal())))
            )
    )

    private fun createTouchableRow(text: String, iconUrl: String, icon2Url: String, destination: String) : Touchable {
        return Touchable(
                onPress = listOf(NavigateAction(destination)),
                child = createContainer(
                        createImageViewFromRemote(iconUrl)
                        .applyStyle(
                                Style(
                                        size = Size(width = 40.unitReal(), height = 40.unitReal())
                                )
                        )
                        .applyFlex(Flex(alignSelf = AlignSelf.CENTER)),
                        createTextView(text)
                        .applyStyle(
                                Style(
                                        margin = EdgeValue(left = 16.unitReal(), right = 16.unitReal())
                                )
                        )
                        .applyFlex(
                                Flex(
                                        grow = 1.0,
                                        justifyContent = JustifyContent.FLEX_START,
                                        alignSelf = AlignSelf.CENTER
                                )
                        ),
                        createImageViewFromRemote(icon2Url)
                        .applyStyle(
                                Style(
                                        size = Size(width = 12.unitReal(), height = 12.unitReal())
                                )
                        )
                        .applyFlex(Flex(alignSelf = AlignSelf.CENTER))
                )
                .applyFlex(Flex(flexDirection = FlexDirection.ROW, flexWrap = FlexWrap.NO_WRAP))
                .applyStyle(style = CustomStyle.buttonStyle)
        )
    }

    private fun createText(text: String)
            = Text(text).applyStyle(style = CustomStyle.textStyle)

    private fun createButton(buttonText: String, remoteUrl: String, styleId: String? = "ButtonRightArrow") = createContainer(
            createImageViewFromRemote(remoteUrl).applyStyle(style = CustomStyle.iconStyle),
            Button(
                    text = buttonText,
                    styleId = styleId,
                    onPress = listOf(ToastAction(buttonText))
            ).applyStyle(style = CustomStyle.buttonStyle)
    ).applyFlex(
            Flex(
                    flexDirection = FlexDirection.ROW,
                    alignContent = AlignContent.STRETCH
            )
    )
}