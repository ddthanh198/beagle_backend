package com.vt.beaglebff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.Route
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.PageView
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.pager.PageIndicator
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text
import com.vt.beaglebff.common.Constants
import com.vt.beaglebff.components.actions.BackAction
import com.vt.beaglebff.components.actions.ToastAction
import com.vt.beaglebff.components.widgets.BottomNavigationView

object WidgetBuilder : BaseBuilder(){

    // bottom navigation
    fun createBottomNavigationView() : Container {
        val menuItems = ArrayList<Array<String>>()

        menuItems.addAll(
                listOf(
                        arrayOf(
                                "${Constants.baseUrl}/resourcesController/ic_home",
                                "Home",
                                "/screenController/tabHome"
                        ),
                        arrayOf(
                                "${Constants.baseUrl}/resourcesController/ic_task",
                                "Tasks",
                                "/screenController/tabTask"
                        ),
                        arrayOf(
                                "${Constants.baseUrl}/resourcesController/ic_request",
                                "Requests",
                                "/screenController/tabRequest"
                        ),
                        arrayOf(
                                "${Constants.baseUrl}/resourcesController/ic_notification",
                                "Notifications",
                                "/screenController/tabNotification"
                        )
                )
        )

        return createContainer(BottomNavigationView(menuItems = menuItems, selectedColor = "#3596EC", unselectedColor = "#788793"))
    }

    // banner
    fun createBannerView() = createContainer(
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
            style = Style(
                    size = Size(width = 100.unitPercent(), height = 25.unitPercent())
            )
    )

    private fun createBannerImage(remoteUrl: String) = createImageViewFromRemote(
            remoteUrl = remoteUrl
    ).applyStyle(
            Style(
                    margin = EdgeValue(
                            top = 16.unitReal(),
                            left = 16.unitReal(),
                            right = 16.unitReal(),
                            bottom = 8.unitReal()
                    ),
                    cornerRadius = CornerRadius(48.0)
            )
    )

    // toolbar
    fun createToolbar(backgroundColor: String? = Constants.colorPrimary) = createContainer(
            createHeader(),
            createSearchBar()
    ).applyStyle(
            style = Style(
                    backgroundColor = backgroundColor
            )
    )

    // header
    fun createHeader() = createContainer(
            createTextView("Xin chào", "WhiteNormalText"),
            createContainer(
                    createTouchableIcon(
                            remoteUrl = "${Constants.baseUrl}/resourcesController/ic_notification_white",
                            width = 30,
                            height = 30,
                            listAction = listOf(
                                    ToastAction("Không có thông báo mới nào")
                            )
                    ),
                    createCircularTextView(
                            text = "HN",
                            width = 44,
                            height = 44,
                            listAction = listOf(
                                    Navigate.PushView(route = Route.Remote("/screenController/personal"))
                            )
                    )
            ).applyFlex(
                    flex = Flex(
                            flexDirection = FlexDirection.ROW,
                            alignItems = AlignItems.CENTER
                    )
            )
    ).applyStyle(
            style = Style(
                    margin = EdgeValue(
                            top = 20.unitReal(),
                            left = 20.unitReal(),
                            right = 20.unitReal()
                    )
            )
    ).applyFlex(
            flex = Flex(
                    flexDirection = FlexDirection.ROW,
                    alignItems = AlignItems.CENTER,
                    justifyContent = JustifyContent.SPACE_BETWEEN,
                    grow = 1.0
            )
    )

    // search bar
    fun createSearchBar() = createContainer(
            createImageViewFromLocal("ic_search")
                    .applyStyle(
                            Style(
                                    size = Size(width = 24.unitReal(), height = 24.unitReal()),
                                    margin = EdgeValue(right = 11.unitReal())
                            )
                    ),
            createTextInput("Công việc, yêu cầu, ứng dụng")
    ).applyStyle(
            Style(
                    margin = EdgeValue(horizontal = 20.unitReal(), vertical = 20.unitReal()),
                    backgroundColor = Constants.colorWhite,
                    padding = EdgeValue(horizontal = 11.unitReal()),
                    size = Size(height = 44.unitReal()),
                    cornerRadius = CornerRadius(22.0),
                    flex = Flex(
                            FlexDirection.ROW,
                            alignItems = AlignItems.CENTER
                    )
            )
    )

    // toolbar
    fun createMainToolbar() = Container(
            children = listOf(
                    Touchable(
                            child = Image(
                                    path = ImagePath.Remote("${Constants.baseUrl}/resourcesController/ic_back")
                            ).applyStyle(
                                    style = Style(
                                            size = Size(
                                                    width = Constants.sizeIcon.unitReal(),
                                                    height = Constants.sizeIcon.unitReal()
                                            ),
                                            margin = EdgeValue(
                                                    all = 8.unitReal(),
                                                    left = 16.unitReal()
                                            ),
                                            flex = Flex(
                                                    alignSelf = AlignSelf.CENTER
                                            )
                                    )
                            ),
                            onPress = listOf(
                                    BackAction("back")
                            )
                    ),
                    Container(
                            children = listOf()
                    ).applyStyle(
                            style = Style(
                                    size = Size(
                                            width = 20.unitReal(),
                                            height = 20.unitReal()
                                    )
                            )
                    ),
                    Text(
                            text = "Title",
                            textColor = Constants.colorWhite,
                            styleId = "TextTitleProfile"
                    ).applyStyle(
                            style = Style(
                                    flex = Flex(
                                            alignSelf = AlignSelf.CENTER
                                    )
                            )
                    ),
                    Container(
                            children = listOf(
                                    Touchable(
                                            child = Image(
                                                    path = ImagePath.Remote("${Constants.baseUrl}/resourcesController/ic_tick")
                                            ).applyStyle(
                                                    style = Style(
                                                            size = Size(
                                                                    width = Constants.sizeIcon.unitReal(),
                                                                    height = Constants.sizeIcon.unitReal()
                                                            ),
                                                            margin = EdgeValue(
                                                                    all = 8.unitReal()
                                                            ),
                                                            flex = Flex(
                                                                    alignSelf = AlignSelf.CENTER
                                                            ),
                                                            padding = EdgeValue(
                                                                    all = 4.unitReal()
                                                            )
                                                    )
                                            ),
                                            onPress = listOf(
                                                    ToastAction("Click read all!")
                                            )
                                    ),
                                    Touchable(
                                            child = Image(
                                                    path = ImagePath.Remote("${Constants.baseUrl}/resourcesController/ic_setting")
                                            ).applyStyle(
                                                    style = Style(
                                                            size = Size(
                                                                    width = Constants.sizeIcon.unitReal(),
                                                                    height = Constants.sizeIcon.unitReal()
                                                            ),
                                                            margin = EdgeValue(
                                                                    all = 8.unitReal(),
                                                                    right = 16.unitReal()
                                                            ),
                                                            flex = Flex(
                                                                    alignSelf = AlignSelf.CENTER
                                                            )
                                                    )
                                            ),
                                            onPress = listOf(
                                                    ToastAction("Click setting!")
                                            )
                                    )
                            )
                    ).applyStyle(
                            style = Style(
                                    flex = Flex(
                                            flexDirection = FlexDirection.ROW
                                    )
                            )
                    )
            )
    ).applyStyle(
            style = Style(
                    flex = Flex(
                            flexDirection = FlexDirection.ROW,
                            justifyContent = JustifyContent.SPACE_BETWEEN
                    ),
                    backgroundColor = Constants.colorPrimary,
                    size = Size(
                            height = 50.unitReal()
                    )
            )
    )
}