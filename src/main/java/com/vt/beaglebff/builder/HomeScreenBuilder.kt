package com.vt.beaglebff.builder

import br.com.zup.beagle.core.*
import br.com.zup.beagle.ext.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.pager.PageIndicator
import br.com.zup.beagle.widget.ui.*
import com.vt.beaglebff.components.actions.ToastAction
import br.com.zup.beagle.widget.core.JustifyContent
import com.vt.beaglebff.common.Constants
import com.vt.beaglebff.components.widgets.BottomNavigationView
import com.vt.beaglebff.components.widgets.ItemRowDivider

class HomeScreenBuilder : ScreenBuilder, BaseBuilder() {

    override fun build(): Screen {
        return Screen(
                child = createContainer(
                        createBottomNavigationView()
                ).applyStyle(
                        Style(
                                flex = Flex(
                                        grow = 1.0
                                )
                        )
                )
        )
    }

    private fun createBannerView() = createContainer(
            PageView(
                    context = ContextData(
                            id = "bannerUrl",
                            value = listOf(
                                    "${Constants.baseUrl}/resourcesController/img_tnxh",
                                    "${Constants.baseUrl}/resourcesController/img_tnxh"
                            )
                    ),
                    pageIndicator = PageIndicator(
                            selectedColor = "#000000",
                            unselectedColor = "#888888"
                    ),
                    children = listOf(
                            createBannerImage("@{bannerUrl[0]}"),
                            createBannerImage("@{bannerUrl[1]}")
                    )
            )
    ).applyStyle(
            Style(
                    size = Size(width = 100.unitPercent(), height = 25.unitPercent())
            )
    ).applyFlex(
            Flex(
                    alignSelf = AlignSelf.CENTER,
                    justifyContent = JustifyContent.FLEX_START
            )
    )

    private fun createToolbar() = createContainer(
            createTextView(
                    text = "Xin chào",
                    styleId = "NormalText"
            ).applyStyle(
                    style = Style(
                            margin = EdgeValue(
                                    all = 8.unitReal()
                            )
                    )
            )
    ).applyStyle(
            Style(backgroundColor = Constants.colorPrimary,
                    size = Size(width = 100.unitPercent(), height = 56.unitReal()),
                    position = EdgeValue(0.unitReal()),
                    positionType = PositionType.RELATIVE
            )

    ).applyFlex(
            Flex(
                    alignSelf = AlignSelf.CENTER,
                    justifyContent = JustifyContent.FLEX_START
            )
    ).setId("widget")

    private fun createBannerImage(remoteUrl: String): Image {
        return createImageViewFromRemote(remoteUrl).applyStyle(
                        Style(
                                margin = EdgeValue(all = 16.unitReal()),
                                cornerRadius = CornerRadius(48.0)
                        )
                )
    }

    private fun createBottomNavigationView() : Container {
        val menuItems = ArrayList<Array<String>>()

        menuItems.add(arrayOf(
                "${Constants.baseUrl}/resourcesController/ic_account_info",
                "Home",
                "/screenController/home")
        )

        menuItems.add(arrayOf(
                "${Constants.baseUrl}/resourcesController/ic_account_info",
                "Requests",
                "/screenController/home")
        )

        menuItems.add(arrayOf(
                "${Constants.baseUrl}/resourcesController/ic_account_info",
                "Tasks",
                "/screenController/home")
        )

        return createContainer(BottomNavigationView(menuItems)).applyStyle(
                Style(
                        flex = Flex(
                                justifyContent = JustifyContent.FLEX_END,
                                grow = 1.0
                        )
                )
        )
    }

    private fun createBottomNavigationBar() = createContainer(
            ItemRowDivider(
                    expressionOf("#B9B9B9"),
                    expressionOf("1"),
                    leftMargin = 0,
                    rightMargin = 0,
                    topMargin = 0,
                    bottomMargin = 0
            ),
            createContainer(
                    createBottomButton(
                            text = "Home",
                            remoteIconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Tasks",
                            remoteIconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Requests",
                            remoteIconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Chat",
                            remoteIconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Notifications",
                            remoteIconUrl = "${Constants.baseUrl}/resourcesController/ic_account_info"
                    )
            ).applyFlex(
                    Flex(
                            flexDirection = FlexDirection.ROW,
                            flexWrap = FlexWrap.NO_WRAP
                    )
            )
    ).applyFlex(
            Flex(
                    flexDirection = FlexDirection.COLUMN
            )
    ).applyStyle(
            Style(
                    flex = Flex(
                            justifyContent = JustifyContent.FLEX_END,
                            grow = 1.0
                    ),
                    margin = EdgeValue(horizontal = 8.unitReal(), bottom = 16.unitReal())
            )
    )

    private fun createBottomButton(text: String, remoteIconUrl: String) = Touchable(
            child = createContainer(
                    createImageViewFromRemote(remoteIconUrl)
                    .applyStyle(
                            Style(
                                    size = Size(width = 40.unitReal(), height = 40.unitReal())
                            )
                    )
                    .applyFlex(
                            Flex(
                                    alignSelf = AlignSelf.CENTER
                            )
                    ),
                    createTextView(
                            text = text,
                            styleId = "BottomNavigationText"
                    ).applyFlex(
                            Flex(
                                    alignSelf = AlignSelf.CENTER
                            )
                    )
            ).applyFlex(
                    Flex(
                            flexDirection = FlexDirection.COLUMN,
                            flex = 1.0
                    )
            ).applyStyle(
                    Style(
                            margin = EdgeValue(all = 8.unitReal())
                    )
            ),
            onPress = listOf(
                    ToastAction(text)
            )
    )

    private fun buildScreen(): Screen = Screen(
            child = createContainer(
                    createTextView("hello")
            )
    )
}