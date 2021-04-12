package com.viettel.beaglebff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.layout.ScrollView
import br.com.zup.beagle.widget.ui.Text
import br.com.zup.beagle.widget.ui.TextInput
import com.viettel.beaglebff.common.Constants

class AccountInformationScreenBuilder: ScreenBuilder, BaseBuilder() {

        private val inputTextStyle = Style(
                borderWidth = 1.0,
                borderColor = Constants.COLOR_DIVIDER,
                backgroundColor = Constants.COLOR_WHITE,
                cornerRadius = CornerRadius(12.0),
                margin = EdgeValue(horizontal = 20.unitReal(), top = 20.unitReal()),
                padding = EdgeValue(horizontal = 12.unitReal())
        )

        override fun build() = Screen(
                child = createContainer(
                        WidgetBuilder.createMainToolbar("Cập nhật tài khoản"),
                        createContainer(
                                ScrollView(
                                        children = listOf(
                                                createTitle(),
                                                createNameTextInput(),
                                                createDateOfBirthInput(),
                                                createGenderSelector(),
                                                createIdentityNumberInput(),
                                                createPhoneNumberInput(),
                                                createEmailInput()
                                        )
                                )
                        ).applyStyle(
                                Style(
                                        backgroundColor = Constants.COLOR_WHITE,
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
                        backgroundColor = Constants.COLOR_PRIMARY
                )
        )

        private fun createTitle() = Text(
                text = "Thông tin cá nhân",
                styleId = "NormalBoldText"
        ).applyStyle(
                Style(
                        margin = EdgeValue(top = 20.unitReal()),
                        flex = Flex(alignSelf = AlignSelf.CENTER)
                )
        )

        private fun createNameTextInput() = createContainer(
                TextInput(
                        placeholder = "Họ và tên*",
                        type = TextInputType.TEXT,
                        styleId = "HintText",
                        onChange = listOf(

                        )
                ).applyStyle(Style(size = Size(height = 48.unitReal())))
        ).applyStyle(style = inputTextStyle)

        private fun createDateOfBirthInput() = createContainer(
                TextInput(
                        placeholder = "Ngày sinh*",
                        type = TextInputType.TEXT,
                        styleId = "HintText",
                        readOnly = true
                ).applyStyle(Style(size = Size(height = 48.unitReal())))
        ).applyStyle(style = inputTextStyle)

        private fun createGenderSelector() = createContainer(
                createTextView("Giới tính*", styleId = "NormalBoldText")
        ).applyStyle(Style(margin = EdgeValue(horizontal = 20.unitReal(), top = 20.unitReal())))

        private fun createIdentityNumberInput() = createContainer(
                TextInput(
                        placeholder = "Số CMND/CCCD*",
                        type = TextInputType.TEXT,
                        styleId = "HintText",
                        onChange = listOf(

                        )
                ).applyStyle(Style(size = Size(height = 48.unitReal())))
        ).applyStyle(style = inputTextStyle)

        private fun createPhoneNumberInput() = createContainer(
                TextInput(
                        placeholder = "Số điện thoại*",
                        type = TextInputType.TEXT,
                        styleId = "HintText",
                        onChange = listOf(

                        )
                ).applyStyle(Style(size = Size(height = 48.unitReal())))
        ).applyStyle(style = inputTextStyle)

        private fun createEmailInput() = createContainer(
                TextInput(
                        placeholder = "Email*",
                        type = TextInputType.TEXT,
                        styleId = "HintText",
                        onChange = listOf(

                        )
                ).applyStyle(Style(size = Size(height = 48.unitReal())))
        ).applyStyle(style = inputTextStyle)
}