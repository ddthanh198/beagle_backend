import com.viettel.beaglebff.builder.BaseBuilder
import com.viettel.beaglebff.builder.WidgetBuilder
import com.viettel.beaglebff.builder.WidgetBuilder.createContainer
import com.viettel.beaglebff.builder.WidgetBuilder.createTextView
import com.viettel.beaglebff.components.widgets.TextFieldWidget

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.layout.ScrollView
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.*
import com.viettel.beaglebff.builder.WidgetBuilder.createRadioImage

import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.components.actions.ShowDialogAction
import com.viettel.beaglebff.components.widgets.ImagePicker


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
         context = ContextData(
             id = "ctx",
             value = "aaaaa",
         ),
         child = createContainer(
             WidgetBuilder.createMainToolbar("Cập nhật tài khoản"),
             createContainer(
                 ScrollView(
                     children = listOf(
                         createContainer(
                             createTitle(),
                             createNameTextInput(),
                             createDateOfBirthInput(),
                             //   createGenderSelector(),
                             createIdentityNumberInput(),
                             createPhoneNumberInput(),
                             createEmailInput(),
                             createHorizontalLine(),
                             createAdressTitle(),
                             createContainer(
                                 TextFieldWidget("Tỉnh/ Thành phố", 75, true, false
                                     , text = Bind.Expression<String>("@{ctx.text}"), onChange = listOf(
                                     SetContext(
                                         contextId = "ctx",
                                         path = "text",
                                         value = "@{onChange.value}"
                                     )
                                 )),
                                 Text("The date is @{ctx.text}").applyStyle(
                                     Style(
                                         size = Size(height = 40.unitReal())
                                     )
                                 )
                             ).applyStyle(
                                 Style(
                                     margin = EdgeValue(horizontal = 16.unitReal(), top = 8.unitReal()),

                                     size = Size(height = 200.unitReal())
                                 )
                             )
                             ,
                             createTextInput(placeHolder = "Quận/Huyện*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createTextInput(placeHolder = "Phường/Xã*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createTextInput(placeHolder = "Địa chỉ chi tiết*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createTextInput(placeHolder = "Tỉnh/Thành phố*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createTextInput(placeHolder = "Quận/Huyện*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createTextInput(placeHolder = "Phường/Xã*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createTextInput(placeHolder = "Địa chỉ chi tiết*", type = TextInputType.TEXT, styleId = "HintText", onChange = listOf()),
                             createUpdateButton()

                         ).applyStyle(
                             Style(
                                 backgroundColor = Constants.COLOR_WHITE,
                                 margin = EdgeValue(top = 64.unitReal(),bottom = (-10).unitReal()),
                                 cornerRadius = CornerRadius(16.0),
                                 flex = Flex(flex = 1.0)
                             )
                         ), createAvatarView()
                     )
                 )
             ).applyStyle(
                 Style(
                     backgroundColor = Constants.COLOR_PRIMARY,
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
             margin = EdgeValue(top = 84.unitReal()),
             flex = Flex(alignSelf = AlignSelf.CENTER)
         )
     )

     private fun createAvatarView() = createContainer(
         createContainer(
             ImagePicker(url = "https://billboardvn.vn/wp-content/uploads/2019/12/20170209212251486615022large-1543892844059784963424.jpg", width = 114, height = 114, cornerRadius = 57.0)
                 .applyStyle(
                     Style(
                         size = Size(width = 114.unitReal(),height = 114.unitReal()),
                         cornerRadius = CornerRadius(57.0),
                         margin = EdgeValue(left = 7.unitReal(), top = 7.unitReal())
                     )
                 ),

             ).applyStyle(
             Style(
                 backgroundColor = "#EBEEF0",
                 size = Size(width = 128.unitReal(), height = 128.unitReal()),
                 cornerRadius = CornerRadius(64.0),
                 positionType = PositionType.ABSOLUTE,
                 flex = Flex(alignSelf = AlignSelf.CENTER)
             )
         ),
         Touchable(
             child = Image(ImagePath.Remote("${Constants.BASE_URL}/resourcesController/ic_edit_avatar")).applyStyle(
                 Style(
                     size = Size(32.unitReal(), 32.unitReal()),
                     positionType = PositionType.ABSOLUTE,
                     cornerRadius = CornerRadius(16.0),
                     flex = Flex( alignSelf = AlignSelf.FLEX_END),
                     margin = EdgeValue(top = 96.unitReal())
                 )
             ),
             onPress = listOf(
                 ShowDialogAction("/widgetController/imageAccessMethodDialog", 0)
             )
         )
     ).applyStyle(
         Style(
             size = Size(width = 128.unitReal(), height = 128.unitReal()),

             positionType = PositionType.ABSOLUTE,
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
         createTextView("Giới tính*", styleId = "NormalBoldText") ,
         createRadioImage("Nam",Style(margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal(), top = 12.unitReal()),flex = Flex(flexDirection = FlexDirection.ROW)), indexID = "male"),
         createRadioImage("Nữ",Style(margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal(), top = 12.unitReal()),flex = Flex(flexDirection = FlexDirection.ROW)),indexID = "female"),
         createRadioImage("Chưa xác định",Style(margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal(), top = 12.unitReal()),flex = Flex(flexDirection = FlexDirection.ROW)),indexID = "undefine"),
     ).applyStyle(Style(margin = EdgeValue(horizontal = 20.unitReal(), top = 20.unitReal())))

     private fun createIdentityNumberInput() = createContainer(
         TextInput(
             placeholder = "Số CMND/CCCD*",
             type = TextInputType.NUMBER,
             styleId = "HintText",
             onChange = listOf(

             )
         ).applyStyle(Style(size = Size(height = 48.unitReal())))
     ).applyStyle(style = inputTextStyle)

     private fun createPhoneNumberInput() = createContainer(
         TextInput(
             placeholder = "Số điện thoại*",
             type = TextInputType.NUMBER,
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

     private fun createHorizontalLine() = createContainer().applyStyle(
         Style(
             backgroundColor =  "#e0e0e0",
             size = Size(height = 1.unitReal()),
             margin = EdgeValue(left = 8.unitReal(), right = 8.unitReal(), top = 36.unitReal())
         )
     )

     private fun createAdressTitle() = createContainer(
         createTextView("Địa chỉ thường trú",styleId = "NormalBoldText").applyStyle(
             Style(
                 margin = EdgeValue(top = 54.unitReal()),
                 flex = Flex(alignSelf = AlignSelf.CENTER)
             )
         )
     )

     private fun createTextInput(placeHolder: String, type: TextInputType, styleId: String, onChange: List<Action>) = createContainer(
         TextInput(
             placeholder = placeHolder,
             type = type,
             styleId = styleId,
             onChange = onChange
         ).applyStyle(Style(size = Size(height = 48.unitReal())))
     ).applyStyle(style = inputTextStyle)

     private fun createUpdateButton() = Button(
         text = "Cập nhật",
         styleId = "Accept.Button.BasicDialog"
     ).applyStyle(
         Style(
             backgroundColor = Constants.COLOR_PRIMARY,
             cornerRadius = CornerRadius(22.0),
             size = Size(height = 44.unitReal()),
             margin = EdgeValue(bottom = 120.unitReal(), top = 24.unitReal(), left = 16.unitReal(), right = 16.unitReal())
         )
     )
}
