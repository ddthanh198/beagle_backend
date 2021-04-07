package com.viettel.bealglebff.builder

import br.com.zup.beagle.core.*
import br.com.zup.beagle.ext.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.pager.PageIndicator
import br.com.zup.beagle.widget.ui.*
import br.com.zup.beagle.widget.core.JustifyContent
import com.viettel.bealglebff.common.Constants
import com.viettel.bealglebff.components.actions.ToastAction
import com.viettel.bealglebff.components.compose_components.HorizontalDivider
import com.viettel.bealglebff.components.widgets.BottomNavigationView

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
                                    "${Constants.BASE_URL}/resourcesController/img_tnxh",
                                    "${Constants.BASE_URL}/resourcesController/img_tnxh"
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
            ),
            Image(
                    ImagePath.Remote("${Constants.BASE_URL}/resourcesController/img_tnxh")
            ).applyStyle(
                    Style(
                            size = Size(width = 100.unitReal(), height = 100.unitReal()),
                            cornerRadius = CornerRadius(50.0)
                    )
            )
    ).applyStyle(
            Style(
                    size = Size(width = 100.unitPercent(), height = 300.unitReal())
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
            Style(backgroundColor = Constants.COLOR_PRIMARY,
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
                "${Constants.BASE_URL}/resourcesController/ic_account_info",
                "Home",
                "/screenController/home")
        )

        menuItems.add(arrayOf(
                "${Constants.BASE_URL}/resourcesController/ic_account_info",
                "Requests",
                "/screenController/home")
        )

        menuItems.add(arrayOf(
                "${Constants.BASE_URL}/resourcesController/ic_account_info",
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
            HorizontalDivider(),
            createContainer(
                    createBottomButton(
                            text = "Home",
                            remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Tasks",
                            remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Requests",
                            remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Chat",
                            remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_account_info"
                    ),
                    createBottomButton(
                            text = "Notifications",
                            remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_account_info"
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