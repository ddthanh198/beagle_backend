package com.vt.beaglebff.service

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Display
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.ui.*
import com.vt.beaglebff.common.Constants
import com.vt.beaglebff.components.actions.ToastAction
import com.vt.beaglebff.model.GithubUser
import com.vt.beaglebff.model.SearchContext
import com.vt.beaglebff.model.UserCell
import org.springframework.stereotype.Service

@Service
class MyService {
    fun getMyScreen(): ScreenBuilder = MyScreen()
}

class MyScreen : ScreenBuilder {
    private val users = listOf<GithubUser>()
    override fun build() = Screen(
        style = Style(
            backgroundColor = "#D72E34"
        ),
        child = Container(
            context = ContextData(
                id = "searchContext",
                value = SearchContext()
            ),
            children = listOf(
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
                            styleId = "NormalText",
                            placeholder = "Nhập số năm cần tìm kiếm",
                            onChange = listOf(
                                ToastAction("hihi"),
                                SetContext("global", "@{onChange.value}", path = "key")
                            ),
                            onBlur = listOf(
                                Alert("hihi", "hehe"),
                                Condition(
                                    condition = expressionOf("@{not(isEmpty(global.key))}"),
                                    onTrue = listOf(
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
                        ).applyStyle(
                            Style(
                                flex = Flex(flex = 1.0)
                            )
                        )
                    )
                ).applyStyle(
                    Style(
                        margin = EdgeValue(horizontal = 20.unitReal(), vertical = 8.unitReal()),
                        backgroundColor = Constants.colorWhite,
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
                        )
                    )
                ).applyStyle(
                    Style(
                        backgroundColor = Constants.colorWhite,
                        flex = Flex(flex = 1.0),
                        cornerRadius = CornerRadius(16.0),
                        margin = EdgeValue(bottom = (-50).unitReal(), top = 10.unitReal())
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
