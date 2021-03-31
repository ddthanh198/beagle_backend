package com.vt.beaglebff.builder

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
import com.vt.beaglebff.common.Constants
import com.vt.beaglebff.common.CustomStyle
import com.vt.beaglebff.components.actions.NavigateAction
import com.vt.beaglebff.components.actions.ToastAction
import com.vt.beaglebff.components.widgets.ItemRowDivider

class PersonalScreenBuilder : ScreenBuilder, BaseBuilder() {

    override fun build() = Screen(
            child = createContainer(
                    WidgetBuilder.createMainToolbar(),
                    createScrollView(
                            createTouchableRow(
                                    text = "Thông tin tài khoản",
                                    iconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info",
                                    icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                    destination = "account_information"
                            ),
                            ItemRowDivider(
                                    expressionOf("#B9B9B9"),
                                    expressionOf("1")
                            ),
                            createTouchableRow(
                                    text = "Cài đặt",
                                    iconUrl = "${Constants.baseUrl}/resourcesController/ic_settings",
                                    icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                    destination = "settings"
                            ),
                            ItemRowDivider(
                                    expressionOf("#B9B9B9"),
                                    expressionOf("1")
                            ),
                            createTouchableRow(
                                    text = "Đổi mật khẩu",
                                    iconUrl = "${Constants.baseUrl}/resourcesController/ic_change_password",
                                    icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                    destination = "change_password"
                            ),
                            ItemRowDivider(
                                    expressionOf("#B9B9B9"),
                                    expressionOf("1")
                            ),
                            createTouchableRow(
                                    text = "Thông tin",
                                    iconUrl = "${Constants.baseUrl}/resourcesController/ic_info",
                                    icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                    destination = "information"
                            ),
                            ItemRowDivider(
                                    expressionOf("#B9B9B9"),
                                    expressionOf("1")
                            ),
                            createTouchableRow(
                                    text = "Đăng xuất",
                                    iconUrl = "${Constants.baseUrl}/resourcesController/ic_logout",
                                    icon2Url = "${Constants.baseUrl}/resourcesController/ic_next",
                                    destination = "log_out"
                            )
                    )
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