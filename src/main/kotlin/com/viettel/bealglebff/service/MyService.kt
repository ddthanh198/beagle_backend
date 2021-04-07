package com.viettel.bealglebff.service

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Display
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.*
import com.viettel.bealglebff.components.actions.OpenDateRangePicker
import com.viettel.bealglebff.components.actions.OpenSideMenuViewController
import com.viettel.bealglebff.components.compose_components.CustomButton
import com.viettel.bealglebff.components.compose_components.CustomFloatButton
import com.viettel.bealglebff.model.DatePickerContext
import com.viettel.bealglebff.model.GithubUser
import com.viettel.bealglebff.model.SearchContext
import com.viettel.bealglebff.model.UserCell
import org.springframework.stereotype.Service

@Service
class MyService {
    fun getMyScreen(): ScreenBuilder = MyScreen()
    fun getHomeSideMenuScreen(): ScreenBuilder = HomeSideMenuScreen()
}

class HomeSideMenuScreen: ScreenBuilder {
    override fun build(): Screen {
        return Screen(
            style = Style(
                backgroundColor = "#000000"
            ),
            child = Container(
                children = listOf(
                    Text("This is the side menu", textColor = "#ffffff")
                )
            ).applyStyle(
                Style(
                    flex = Flex(
                        alignItems = AlignItems.CENTER,
                        grow = 1.0,
                        justifyContent = JustifyContent.CENTER
                    )
                )
            )
        )
    }

}

class MyScreen : ScreenBuilder {
    val searchContext = ContextData(
        id = "searchContext",
        value = SearchContext()
    )

    override fun build() = Screen(
        style = Style(
            backgroundColor = "#D72E34"
        ),
        child = Container(
            context = searchContext,
            children = listOf(
                Container(
                    children = listOf(
                        Text("Hihi, chào cậu !", styleId = "Title.Text.Orange"),
                        Image(
                            ImagePath.Remote("https://freepikpsd.com/wp-content/uploads/2019/10/push-notification-icon-png-9-Transparent-Images-Free.png")
                        ).applyStyle(
                            Style(
                                size = Size(width = 40.unitReal(), height = 40.unitReal())
                            )
                        )
                    )
                ).applyStyle(
                    Style(
                        margin = EdgeValue(horizontal = 20.unitReal(), vertical = 10.unitReal()),
                        size = Size(height = 44.unitReal()),
                        flex = Flex(
                            flexDirection = FlexDirection.ROW,
                            justifyContent = JustifyContent.SPACE_BETWEEN,
                            alignItems = AlignItems.CENTER
                        )
                    )
                ),
                CustomButton(
                    title = "Hehe" + "@{global.dateRange}",
                    textColor = "#ffffff",
                    backgroundColor = "#0000ff",
                    radius = 22.0,
                    onPress = listOf(
                        OpenSideMenuViewController("/home_side_menu")
                    )
                ),
                Container(
                    children = listOf(
                        Image(
                            ImagePath.Local.both(
                                "https://img.icons8.com/pastel-glyph/2x/search--v2.png",
                                "ic_search"
                            )
                        ).applyStyle(
                            Style(
                                size = Size(width = 24.unitReal(), height = 24.unitReal()),
                                margin = EdgeValue(right = 11.unitReal())
                            )
                        ),
                        TextInput(
                            placeholder = "Nhập số năm cần tìm kiếm",
                            onChange = listOf(
                                SetContext("global", "@{onChange.value}", path = "key")
                            ),
                            onBlur = listOf(
                                Condition(
                                    condition = expressionOf("@{not(isEmpty(global.key))}"),
                                    onTrue = listOf(
                                        SetContext("searchContext", value = listOf<GithubUser>(), path = "users"),
                                        SetContext("searchContext", Display.FLEX, path = "isShowLoading"),
                                        SendRequest(
                                            url = "https://api.github.com/users?since=@{global.key}",
                                            method = RequestActionMethod.GET,
                                            onSuccess = listOf(
                                                SetContext("searchContext", Display.NONE, path = "isShowLoading"),
                                                Condition(
                                                    condition = expressionOf("@{isEmpty(searchContext.users)}"),
                                                    onTrue = listOf(
                                                        SetContext("searchContext", Display.FLEX, "isEmptyResult")
                                                    ),
                                                    onFalse = listOf(
                                                        SetContext("searchContext", Display.NONE, "isEmptyResult")
                                                    )
                                                ),
                                                SetContext("searchContext", value = "@{onSuccess.data}", path = "users")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                ).applyStyle(
                    Style(
                        margin = EdgeValue(horizontal = 20.unitReal(), vertical = 8.unitReal()),
                        backgroundColor = "#ffffff",
                        padding = EdgeValue(horizontal = 11.unitReal()),
                        size = Size(height = 44.unitReal()),
                        cornerRadius = CornerRadius(22.0),
                        flex = Flex(
                            FlexDirection.ROW,
                            alignItems = AlignItems.CENTER
                        )
                    )
                ),
                Container(
                    children = listOf(
                        ListView(
                            dataSource = expressionOf("@{searchContext.users}"),
                            template = UserCell("@{item.avatar_url}", "@{item.login}", "@{item.node_id}")
                        ).applyStyle(
                            Style(
                                margin = EdgeValue(bottom = 100.unitReal())
                            )
                        )
                    )
                ).applyStyle(
                    Style(
                        backgroundColor = "#ffffff",
                        flex = Flex(flex = 1.0),
                        cornerRadius = CornerRadius(16.0),
                        margin = EdgeValue(bottom = (-100).unitReal(), top = 10.unitReal())
                    )
                ),
                CustomFloatButton(
                    title = "@{global}",
                    textColor = "#ffffff",
                    backgroundColor = "#0000ff",
                    alignItems = AlignItems.FLEX_START,
                    onPress = listOf(
                        OpenDateRangePicker(
                            context = searchContext
                        )
                    )
                )
            )
        ).applyStyle(
            Style(
                flex = Flex(grow = 1.0)
            )
        )
    )
}
