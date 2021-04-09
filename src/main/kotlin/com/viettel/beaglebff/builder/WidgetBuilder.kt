package com.viettel.beaglebff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.Route
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.PageView
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.pager.PageIndicator
import br.com.zup.beagle.widget.ui.*
import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.components.actions.*
import com.viettel.beaglebff.components.widgets.BottomNavigationView
import com.viettel.beaglebff.model.generateUserInfoList
import com.viettel.beaglebff.model.populateLanguageOptions

object WidgetBuilder : BaseBuilder(){

    // bottom navigation
    fun createBottomNavigationView() : Container {
        val menuItems = ArrayList<Array<String>>()

        menuItems.addAll(
                listOf(
                        arrayOf(
                                "${Constants.BASE_URL}/resourcesController/ic_home",
                                "Home",
                                "/screenController/tabHome"
                        ),
                        arrayOf(
                                "${Constants.BASE_URL}/resourcesController/ic_task",
                                "Tasks",
                                "/screenController/tabTask"
                        ),
                        arrayOf(
                                "${Constants.BASE_URL}/resourcesController/ic_request",
                                "Requests",
                                "/screenController/tabRequest"
                        ),
                        arrayOf(
                                "${Constants.BASE_URL}/resourcesController/ic_notification",
                                "Notifications",
                                "/screenController/tabNotification"
                        ),
                         arrayOf(
                             "${Constants.BASE_URL}/resourcesController/ic_management",
                             "Management",
                             "/screenController/tabChart"
                         )
                )
        )

        return createContainer(BottomNavigationView(menuItems = menuItems, selectedColor = "#3596EC", unselectedColor = "#788793"))
    }

    // banner
    fun createBannerView() = Container(
            children = listOf(
                    Container(
                            children = listOf(
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
                                    )
                            )
                    ).applyStyle(
                            Style(
                                    size = Size(height = 200.unitReal()),
                                    margin = EdgeValue(horizontal = 12.unitReal(), top = 12.unitReal())
                            )
                    )
            )
    )

    private fun createBannerImage(remoteUrl: String) = createImageViewFromRemote(
            remoteUrl = remoteUrl
    ).applyStyle(
            Style(
                    size = Size(height = 500.unitReal()),
                    cornerRadius = CornerRadius(48.0)
            )
    )

    // toolbar
    fun createToolbar(backgroundColor: String? = Constants.COLOR_PRIMARY) = createContainer(
            createHeader(),
            createSearchBar()
    ).applyStyle(
            style = Style(
                    backgroundColor = backgroundColor
            )
    )

    // header
    fun createHeader() = Container(
            children = listOf(
                    Touchable(
                            child = Text(
                                        text = "@{condition(isEmpty(global.welcomeContext), 'Xin chào', global.welcomeContext)}",
                                        styleId = "NormalBoldText",
                                        textColor = Constants.COLOR_WHITE
                            ).applyStyle(
                                    Style(size = Size(width = 50.unitPercent()))
                            ),
                            onPress = listOf(
                                    com.viettel.beaglebff.components.actions.ShowBottomSheetAction(
                                            "/widgetController/bottomSheetDialog",
                                            generateUserInfoList().size
                                    )
                            )
                    ),
                    createContainer(
                            createTouchableIcon(
                                    remoteUrl = "@{condition(isEmpty(global.language), '${Constants.BASE_URL}/resourcesController/flag_vn', global.language)}",
                                    width = 30,
                                    height = 30,
                                    listAction = listOf(
                                            com.viettel.beaglebff.components.actions.ShowDialogAction(
                                                    "/widgetController/selectLanguageDialog",
                                                    numberOfItems = populateLanguageOptions().size
                                            )
                                    )
                            ),
                            createCircularTextView(
                                    text = "HN",
                                    width = 44,
                                    height = 44,
                                    listAction = listOf(
                                            //ShowBottomSheetAction("/widgetController/bottomSheetDialog")
                                            //ShowDialogAction("/widgetController/selectionDialog")
                                            Navigate.PushView(route = Route.Remote("/screenController/accountInformation"))
                                    )
                            )
                    ).applyFlex(
                            flex = Flex(
                                    flexDirection = FlexDirection.ROW,
                                    alignItems = AlignItems.CENTER
                            )
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
            Touchable(
                    child = TextInput(placeholder = "Công việc, yêu cầu, ứng dụng", styleId = "HintText", readOnly = true),
                    onPress = listOf(
                            Navigate.PushView(route = Route.Remote("/screenController/search"))
                    )
            )
    ).applyStyle(
            Style(
                    margin = EdgeValue(horizontal = 20.unitReal(), vertical = 20.unitReal()),
                    backgroundColor = Constants.COLOR_WHITE,
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
    fun createMainToolbar(title: String) = Container(
            children = listOf(
                    Touchable(
                            child = Image(
                                    path = ImagePath.Remote("${Constants.BASE_URL}/resourcesController/ic_back")
                            ).applyStyle(
                                    style = Style(
                                            size = Size(
                                                    width = Constants.SMALL_ICON_SIZE.unitReal(),
                                                    height = Constants.SMALL_ICON_SIZE.unitReal()
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
                                    Navigate.PopView()
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
                            text = title,
                            textColor = Constants.COLOR_WHITE,
                            styleId = "TextTitleProfile",
                            alignment = TextAlignment.CENTER
                    ).applyStyle(
                            style = Style(
                                    positionType = PositionType.ABSOLUTE,
                                    position = EdgeValue(top = 0.unitReal(), horizontal = 50.unitReal()),
                                    margin = EdgeValue(top = 16.unitReal())
                            )
                    ),
                    Container(
                            children = listOf(
                                    Touchable(
                                            child = Image(
                                                    path = ImagePath.Remote("${Constants.BASE_URL}/resourcesController/ic_tick")
                                            ).applyStyle(
                                                    style = Style(
                                                            size = Size(
                                                                    width = Constants.SMALL_ICON_SIZE.unitReal(),
                                                                    height = Constants.SMALL_ICON_SIZE.unitReal()
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
                                                    com.viettel.beaglebff.components.actions.ToastAction(
                                                            "Click read all!"
                                                    )
                                            )
                                    ),
                                    Touchable(
                                            child = Image(
                                                    path = ImagePath.Remote("${Constants.BASE_URL}/resourcesController/ic_setting")
                                            ).applyStyle(
                                                    style = Style(
                                                            size = Size(
                                                                    width = Constants.SMALL_ICON_SIZE.unitReal(),
                                                                    height = Constants.SMALL_ICON_SIZE.unitReal()
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
                                                    com.viettel.beaglebff.components.actions.ToastAction(
                                                            "Click setting!"
                                                    )
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
                            justifyContent = JustifyContent.SPACE_BETWEEN,
                            alignContent = AlignContent.CENTER
                    ),
                    backgroundColor = Constants.COLOR_PRIMARY,
                    size = Size(
                            height = 50.unitReal()
                    )
            )
    )

    fun createLanguageSelectionDialog() = createContainer(
        createContainer(
            createTextView(
                text = "Choose your language",
                styleId = "NormalBoldText",
                textAlignment = TextAlignment.CENTER
            ).applyStyle(
                Style(
                    margin = EdgeValue(left = 18.unitReal(), top = 4.unitReal()),
                    flex = Flex(alignSelf = AlignSelf.CENTER, grow = 1.0)
                )
            ),
            createTouchableIcon(remoteUrl = "${Constants.BASE_URL}/resourcesController/ic_close",width = 18,height = 18, listOf(
                    com.viettel.beaglebff.components.actions.DismissDialogAction("/widgetController/selectLanguageDialog")
                 ))
             ).applyStyle(
                    Style(
                        flex = Flex(flexDirection = FlexDirection.ROW, justifyContent = JustifyContent.FLEX_END),
                        margin = EdgeValue(right = 12.unitReal(), top = 16.unitReal(), bottom = 20.unitReal(), left = 12.unitReal())
                    )
             ),
            ListView(
                    context = ContextData(id = "languages", value = populateLanguageOptions()),
                    dataSource = expressionOf("@{languages}"),
                    template = createContainer(
                            Touchable(
                                    child = createContainer(
                                            createImageViewFromRemote("@{item.languageIconUrl}").applyStyle(
                                                    Style(
                                                            size = Size(
                                                                    width = 24.unitReal(),
                                                                    height = 24.unitReal()
                                                            )
                                                    )
                                            ),
                                            createTextView(text = "@{item.language}").applyStyle(
                                                    Style(margin = EdgeValue(horizontal = 8.unitReal()))
                                            )
                                    ).applyFlex(
                                            Flex(
                                                    flex = 1.0,
                                                    flexDirection = FlexDirection.ROW
                                            )
                                    ),
                                    onPress = listOf(
                                            SetContext(
                                                    contextId = "global",
                                                    path = "language",
                                                    value = "@{item.languageIconUrl}"
                                            ),
                                            com.viettel.beaglebff.components.actions.DismissDialogAction(
                                                    "/widgetController/selectLanguageDialog"
                                            )
                                    )
                            ),
                            createDivider(8, 8, 0, 0)
                    ).applyStyle(
                            Style(
                                    margin = EdgeValue(
                                            left = 20.unitReal(),
                                            right = 20.unitReal(),
                                            bottom = 16.unitReal()
                                    )
                            )
                    )
            )
    ).applyStyle(
            style = Style(
                    backgroundColor = Constants.COLOR_WHITE,
                    cornerRadius = CornerRadius(radius = Constants.DIALOG_RADIUS)
            )
    )

    fun createDemoBottomSheetDialog() = createContainer(
            createContainer(
                createTextView(
                   text = "Lựa chọn tên của bạn",
                   styleId = "NormalBoldText",
                   textAlignment = TextAlignment.CENTER
               ).applyStyle(
                    Style(
                        margin = EdgeValue(left = 18.unitReal(), top = 12.unitReal()),
                        flex = Flex(alignSelf = AlignSelf.CENTER, grow = 1.0)
                    )
                ),
                createTouchableIcon(remoteUrl = "${Constants.BASE_URL}/resourcesController/ic_close",width = 18,height = 18, listOf(
                        com.viettel.beaglebff.components.actions.DismissDialogAction("/widgetController/bottomSheetDialog")
                ))
            ).applyStyle(
                Style(
                    flex = Flex(flexDirection = FlexDirection.ROW, justifyContent = JustifyContent.FLEX_END),
                    margin = EdgeValue(right = 12.unitReal(), top = 8.unitReal(), bottom = 20.unitReal(), left = 12.unitReal())
                )
            ),
            ListView(
                    context = ContextData(id = "users", value = generateUserInfoList()),
                    dataSource = expressionOf("@{users}"),
                    template = createContainer(
                            Touchable(
                                    child = createContainer(
                                            createTextView(text = "@{item.username}").applyStyle(
                                                    Style(margin = EdgeValue(horizontal = 8.unitReal()))
                                            )
                                    ).applyFlex(
                                            Flex(
                                                    flex = 1.0,
                                                    flexDirection = FlexDirection.ROW
                                            )
                                    ),
                                    onPress = listOf(
                                            SetContext(
                                                    contextId = "global",
                                                    path = "welcomeContext",
                                                    value = "Xin chào @{item.username}"
                                            ),
                                            com.viettel.beaglebff.components.actions.DismissDialogAction(
                                                    "/widgetController/bottomSheetDialog"
                                            )
                                    )
                            ),
                            createDivider(8, 8, 0, 0)
                    ).applyStyle(
                            Style(
                                    margin = EdgeValue(
                                            left = 20.unitReal(),
                                            right = 20.unitReal(),
                                            bottom = 16.unitReal()
                                    )
                            )
                    )
            ),
            createContainer().applyStyle(Style(size = Size(height = Constants.DIALOG_RADIUS.unitReal())))
    ).applyStyle(
            style = Style(
                    backgroundColor = Constants.COLOR_WHITE,
                    cornerRadius = CornerRadius(radius = Constants.DIALOG_RADIUS),
                    margin = EdgeValue(bottom = (-Constants.DIALOG_RADIUS).unitReal())
            )
    )
}