package com.viettel.beaglebff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Action
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
import com.viettel.beaglebff.common.Constants.CachedFile
import com.viettel.beaglebff.components.actions.*
import com.viettel.beaglebff.components.compose_components.HorizontalDivider
import com.viettel.beaglebff.components.widgets.BottomNavigationView
import com.viettel.beaglebff.components.widgets.GridView
import com.viettel.beaglebff.entity.CacheVersion
import com.viettel.beaglebff.model.TabInfo
import com.viettel.beaglebff.model.generateUserInfoList
import com.viettel.beaglebff.model.populateLanguageOptions
import com.viettel.beaglebff.repository.CacheVersionRepository
import org.springframework.beans.factory.annotation.Autowired

object WidgetBuilder : BaseBuilder(){

    // bottom navigation
    fun createBottomNavigationView(listCacheVersion: List<CacheVersion>) : Container {
        val tabItems = ArrayList<TabInfo>()

        if (listCacheVersion.size == 5) {
            tabItems.addAll(
                listOf(
                    TabInfo(
                        remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_home",
                        title = "Home",
                        api = "/screenController/tabHome",
                        cacheFile = listCacheVersion[0].name + "-" + listCacheVersion[0].version
                    ),
                    TabInfo(
                        remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_task",
                        title = "Tasks",
                        api = "/screenController/tabTask",
                        cacheFile = listCacheVersion[1].name + "-" + listCacheVersion[1].version
                    ),
                    TabInfo(
                        remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_request",
                        title = "Requests",
                        api = "/screenController/tabRequest",
                        cacheFile = listCacheVersion[2].name + "-" + listCacheVersion[2].version
                    ),
                    TabInfo(
                        remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_notification",
                        title = "Notifications",
                        api = "/screenController/tabNotification",
                        cacheFile = listCacheVersion[3].name + "-" + listCacheVersion[3].version
                    ),
                    TabInfo(
                        remoteIconUrl = "${Constants.BASE_URL}/resourcesController/ic_management",
                        title = "Management",
                        api = "/screenController/tabChart",
                        cacheFile = listCacheVersion[4].name + "-" + listCacheVersion[4].version
                    )
                )
            )
        }

        return createContainer(
            BottomNavigationView(
                tabItems = tabItems,
                selectedColor = Constants.COLOR_PRIMARY,
                unselectedColor = "#788793")
        )
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
                                    size = Size(height = 360.unitReal()),
                                    margin = EdgeValue(horizontal = 12.unitReal(), top = 12.unitReal())
                            )
                    )
            )
    )

    fun createGridView() = Container(
            children = listOf(
                    GridView(
                            spanCount = 3,
                            context = ContextData(
                                    id = "images",
                                    value = mutableListOf<String>().apply {
                                            repeat(100) {
//                                                                    add(
//                                                                            listOf(
//                                                                                    "https://www.wallpapertip.com/wmimgs/9-91552_hnh-nn-in-thoi-mo-cute.jpg",
//                                                                                    "https://i.ytimg.com/vi/OmxdLl6jSFk/maxresdefault.jpg",
//                                                                                    "https://langnhincuocsong.tv/cdn/2018/08/hinh-anh-meo-con.jpg",
//                                                                                    "https://i.pinimg.com/originals/3f/55/ee/3f55ee77f25327543aaa1e2a7a5effef.jpg",
//                                                                                    "https://www.xahara.vn/wp-content/uploads/ch%C3%B3-husky-d%E1%BB%85-th%C6%B0%C6%A1ng.jpeg",
//                                                                                    "https://www.xahara.vn/wp-content/uploads/h%C3%ACnh-%E1%BA%A3nh-ch%C3%B3-husky-m%E1%BA%B7t-d%E1%BB%85-th%C6%B0%C6%A1ng.jpg",
//                                                                                    "https://salt.tikicdn.com/ts/product/f3/f1/ee/036a1529fbbb1dba6fff235d91378ef6.jpg"
//                                                                            ).random()
//                                                                    )
                                                    add("$it hihi")
                                            }

                                    }),
                            dataSource = expressionOf("@{images}"),
                            template = Container(
                                    children = listOf(
                                            Text(
                                                    text = "@{item}",
                                                    alignment = TextAlignment.CENTER
                                            ).applyFlex(
                                                    Flex(
                                                            justifyContent = JustifyContent.CENTER
                                                    )
                                            ).applyStyle(
                                                    Style(
                                                            size = Size(width = 100.unitReal(), height = 50.unitReal()),
                                                            margin = EdgeValue(all = 10.unitReal())
                                                    )
                                            )
                                    )
                            ).applyStyle(
                                    Style(
                                            margin = EdgeValue(bottom = 20.unitReal())
                                    )
                            )
                    )
            )
    ).applyStyle(
            Style(
                    margin = EdgeValue(
                            top = 12.unitReal()
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

     // create Header
     fun createHeader() = Container(
         children = listOf(
             Touchable(
                 child = createContainer(
                     Text(
                         text = "Xin chào",
                         styleId = "NormalBoldText",
                         textColor = Constants.COLOR_WHITE
                     ).applyStyle(
                         Style(size = Size(width = 28.unitPercent()))
                     ),
                     Text(
                         text = "@{condition(isNull(global.welcomeContext), '', global.welcomeContext)}",
                         styleId = "NormalBoldText",
                         textColor = Constants.COLOR_WHITE
                     ).applyStyle(
                         Style(size = Size(width = 50.unitPercent()))
                     )
                 ).applyStyle(
                     Style(
                         flex = Flex(flexDirection = FlexDirection.ROW)
                     )
                 ),
                 onPress = listOf(
                     ShowBottomSheetAction("/widgetController/bottomSheetDialog", generateUserInfoList().size)
                 )
             ),
             createContainer(
                 Touchable(
                     child = createContainer(
                         Image(
                             ImagePath.Remote("${Constants.BASE_URL}/resourcesController/flag_vn")
                         ).applyStyle(
                             Style(size = Size(width = 30.unitReal(), height = 30.unitReal()))
                         ),
                         Image(
                             ImagePath.Remote("@{condition(isEmpty(global.language), '', global.language)}")
                         ).applyStyle(
                             Style(size = Size(width = 30.unitReal(), height = 30.unitReal()),
                                 positionType = PositionType.ABSOLUTE)
                         ),
                     ).applyStyle(
                         Style(
                             flex = Flex(flexDirection = FlexDirection.ROW),
                         )
                     )
                     , onPress = listOf(
                     ShowDialogAction("/widgetController/selectLanguageDialog", numberOfItems = populateLanguageOptions().size)
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
                    size = Size(height = 100.unitReal()),
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
                                                    ToastAction("Click read all!")
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
                    DismissDialogAction("/widgetController/selectLanguageDialog")
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
                                            DismissDialogAction("/widgetController/selectLanguageDialog")
                                    )
                            ),
                            HorizontalDivider(8, 8, 0, 0)
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
                        DismissDialogAction("/widgetController/bottomSheetDialog")
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
                                                    value = "@{item.username}"
                                            ),
                                            DismissDialogAction("/widgetController/bottomSheetDialog")
                                    )
                            ),
                            HorizontalDivider(8, 8, 0, 0)
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

     fun createBasicDialog( title: String = "Tittle",  content: String = "Content",  titleStyleID: String = "Title.Dialog.Style"
                           ,  contentStyleID: String = "Content.Dialog.Style",  acceptButton: String = "Accept"
                           ,  cancelButton: String = "Cancel",  acceptButtonStyle: String = ""
                           ,  cancelButtonStyle: String = "",  acceptAction: ArrayList<Action>,  cancelAction: ArrayList<Action>) = createContainer(
         createContainer(
             createTextView(text = title,styleId = titleStyleID, textAlignment = TextAlignment.CENTER).applyStyle(
                 Style(
                     flex = Flex(alignSelf = AlignSelf.CENTER,grow = 1.0),
                     margin = EdgeValue(left = 30.unitReal())
                 )
             )
             ,createTouchableIcon("${Constants.BASE_URL}/resourcesController/ic_delete",18,18, listOf(
                    DismissDialogAction(""),
               )
          )
         ).applyFlex(
             flex = Flex(
                 flexDirection = FlexDirection.ROW,
                 justifyContent = JustifyContent.FLEX_END,
             )
         ).applyStyle(
             Style(
                margin = EdgeValue(top = 12.unitReal(), right = 12.unitReal()),
                )
         )
         ,
         createTextView(text = content,styleId = contentStyleID, textAlignment = TextAlignment.CENTER).applyStyle(
             Style(
                 margin = EdgeValue(top = 12.unitReal(), left = 18.unitReal(), right = 18.unitReal(), bottom = 12.unitReal())
             )
         ),
//         createRadioImage("Nam",Style(margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal(), top = 12.unitReal()),flex = Flex(flexDirection = FlexDirection.ROW)), indexID = "male"),
//          createRadioImage("Nữ",Style(margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal(), top = 12.unitReal()),flex = Flex(flexDirection = FlexDirection.ROW)),indexID = "female"),
//          createRadioImage("Chưa xác định",Style(margin = EdgeValue(left = 18.unitReal(), right = 18.unitReal(), top = 12.unitReal()),flex = Flex(flexDirection = FlexDirection.ROW)),indexID = "undefine"),
         createContainer(
             createButton(buttonText = cancelButton, styleID = cancelButtonStyle, backgroundColor = "#e0e0e0", actions = cancelAction)
             ,
             createButton(buttonText = acceptButton, styleID = acceptButtonStyle, backgroundColor = "#039be5", actions = acceptAction)
         ).applyFlex(
             Flex(
                 grow = 1.0,
                 flexDirection =  FlexDirection.ROW,
                 alignContent = AlignContent.FLEX_END,
             )
         )
     ).applyFlex(
         Flex(
             justifyContent = JustifyContent.CENTER
         )
     ).applyStyle(
         Style(
             backgroundColor = "#FFFFFF",
             flex = Flex(grow = 1.0),
         )
     )

     private fun createButton(buttonText: String, styleID: String, backgroundColor: String, actions: List<Action> ) = Button (
         text = buttonText,
         styleId = styleID,
         onPress = actions.toList()
     ).applyStyle(
         Style(
             backgroundColor = backgroundColor,
             size = Size(height = 40.unitReal()),
             cornerRadius = CornerRadius(4.0),
             margin = EdgeValue(left = 12.unitReal(), right = 12.unitReal(), bottom = 24.unitReal() )
         )
     ).applyFlex(Flex(flex =  1.0, alignSelf = AlignSelf.FLEX_END))

     fun createRadioImage(text: String,style: Style, indexID: String) = Touchable(
         onPress = listOf(
             SetContext(
                 contextId = "global",
                 value = "@{global.$indexID}",
                 path = "gender"
             )
         ),
         child = createContainer(
             Image(
                 ImagePath.Remote("@{condition(eq(global.gender,global.$indexID) , 'http://localhost:8080/resourcesController/radio_button_clicked', 'http://localhost:8080/resourcesController/radio_button')}")
                 //  ImagePath.Remote("http://localhost:8080/resourcesController/radio_button_clicked")
             ).applyStyle(
                 Style(
                     size = Size(width = 24.unitReal(), height = 24.unitReal()),margin = EdgeValue(right = 12.unitReal())
                 )
             ), createTextView(text = text, styleId = "",textAlignment = TextAlignment.LEFT)
         ).applyStyle(style)
     )
}