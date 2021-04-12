package com.viettel.beaglebff.builder

import br.com.zup.beagle.core.CornerRadius
import com.viettel.beaglebff.components.actions.LoadingAction
import com.viettel.beaglebff.components.actions.ShowDialogAction
import com.viettel.beaglebff.components.compose_components.TaskComponent

import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.SafeArea
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.ListView
import br.com.zup.beagle.widget.ui.Text
import com.viettel.beaglebff.common.Constants


class TaskBuilder: ScreenBuilder, BaseBuilder() {
     override fun build(): Screen = Screen (

         child = Container (
             children =  listOf(
                 Container(
                     context = ContextData(id = "global.oldKey","@{onChange.value}"),
                     children =  listOf(
                         Touchable (
                             child = Image(
                                 ImagePath.Local.both("a","ic_back_button")
                             ).applyStyle(
                                 Style(
                                     size = Size(width = 20.unitReal(), height = 20.unitReal()),
                                     margin = EdgeValue(top = 12.unitReal()),
                                 )
                             ),
                             onPress = listOf(
                                 Navigate.PopView()
                                 //   CustomAction("dismiss")
                             )
                         ),
                         Text("Phản ánh hiện trường",styleId = "Title.Text.Orange").applyStyle(Style(
                             margin = EdgeValue(top = 12.unitReal()),
                             size = Size(height = 20.unitReal())
                         )),
                         Touchable (
                             child = Image(
                                 ImagePath.Local.both("https://img.icons8.com/pastel-glyph/2x/search--v2.png","ic_search_feedback")
                             ).applyStyle(
                                 Style(
                                     size = Size(width = 20.unitReal(), height = 20.unitReal()),
                                     margin = EdgeValue(top = 12.unitReal()),
                                 )
                             ),
                             onPress = listOf(
                                 SetContext(
                                     contextId = "global",
                                     path = "male",
                                     value =  0
                                 ),
                                 SetContext(
                                     contextId = "global",
                                     path = "female",
                                     value =  1
                                 ),
                                 SetContext(
                                     contextId = "global",
                                     path = "undefine",
                                     value =  2
                                 ),
                                 SetContext(
                                     contextId = "global",
                                     path = "gender",
                                     value =  0
                                 ),
                                 ShowDialogAction("/widgetController/basicDialog", 0,width = 300, height = 480)
                             )
                         ),
                     )
                 ).applyStyle(
                     Style(
                         margin = EdgeValue(top = 40.unitReal(), left = 16.unitReal(), right = 16.unitReal(), bottom = 10.unitReal()),
                         size = Size(height = 44.unitReal()),
                         flex = Flex(flexDirection = FlexDirection.ROW,justifyContent = JustifyContent.SPACE_BETWEEN)

                     )
                 ), Container(
                 children = listOf(
                     ListView(

                         dataSource = expressionOf("@{global.taskModels.data}"),
                         scrollEndThreshold = 95,

                         template = TaskComponent("@{item.id}","@{condition(isEmpty(item.process_request.data.media_file) , 'http://localhost:8080/default', concat(item.process_request.data.media_file[0].host, item.process_request.data.media_file[0].thumbnail))}","@{item.process_request.data.name}","@{item.process_request.data.address.location}","@{item.process_request.data.created_at}",
                             "@{condition(eq(item.status, 'ACTIVE'), 'http://localhost:8080/ic_feedback_processing', condition(eq(item.status, 'CLOSED'), 'http://localhost:8080/ic_feedback_processed', 'http://localhost:8080/ic_feedback_waiting'))}"
                             ,"@{condition(eq(item.status, 'ACTIVE'), 'Đang xử lý', condition(eq(item.status, 'DRAFT'), 'Dự thảo', condition(eq(item.status, 'CLOSED'), 'Đã xử lý', 'Chờ xử lý')))}"
                             , "@{condition(eq(item.status, 'ACTIVE'), '#1790C9', condition(eq(item.status, 'CLOSED'), '#0F8E70', '#E17126'))}")
                         , onScrollEnd = listOf(
                         Condition (
                             condition = expressionOf("@{gt(global.taskModels.meta.total_pages,global.taskModels.meta.current_page)}"),
                             onTrue = listOf(
                                 LoadingAction(true),
                                 SendRequest(
                                     url = "http://171.244.149.211:8090/api/1.0/tasks?include=process,processRequest,processRequest.user,user&statusfilter=ACTIVE,CLOSED,DRAFT,PAUSE,ASSIGNED&order_by=ID&count_type=none&order_direction=DESC&per_page=20&page=@{sum(taskModels.meta.current_page, 1)}&process_owner=14&advanced_search[]={\"label\":\"Trạng thái\",\"field\":\"status\",\"value\":[\"ASSIGNED\",\"ACTIVE\"],\"hidden_mobile\":false,\"compare\":\"and\",\"condition\":\"=\",\"type\":\"TASK:STATUS\",\"config\":{\"typeField\":\"local\",\"value\":[{\"name\":\"Chờ tiếp nhận\",\"value\":\"DRAFT\"},{\"name\":\"Đang thực hiện\",\"value\":\"ACTIVE\"},{\"name\":\"Đã hoàn thành\",\"value\":\"CLOSED\"}]}}&created_start_date=&created_end_date=",
                                     method = RequestActionMethod.GET,
                                     headers = mapOf(
                                         "Content-Type" to "applicaition/json",
                                         "Authorization" to "${Constants.TOKEN}"
                                     ), onSuccess = listOf (
                                     SetContext(
                                         contextId = "global",
                                         path = "taskModels.data",
                                         value = "@{union(global.taskModels.data,onSuccess.data.data)}"
                                     ),
                                     SetContext(
                                         contextId = "global",
                                         path = "taskModels.meta",
                                         value = "@{onSuccess.data.meta}"
                                     ),
                                     SetContext(
                                         contextId = "global",
                                         path = "taskModels.countReport",
                                         value = "@{onSuccess.data.countReport}"
                                     ),
                                     LoadingAction(false)

                                     //   CustomAction("hideLoading")
                                 ), onError = listOf(
                                     //     CustomAction("hideLoading"),
                                     LoadingAction(false),

                                   )
                                 )
                             ))
                     )
                     ).applyStyle(
                         Style(
                             backgroundColor = "#FFFFFF",

                             )
                     )
                 )
             ).applyStyle(
                 Style(
                     backgroundColor = "#FFFFFF",
                     cornerRadius = CornerRadius(16.0),
                     margin = EdgeValue(bottom = (-10).unitReal(),top = 12.unitReal(), left = 0.unitReal(),right = 0.unitReal()),
                     flex = Flex(grow = 1.0)
                 )
               )
             )
         ).applyStyle(
             Style(
                 backgroundColor = "#0F8E70",
                 flex = Flex(flexDirection = FlexDirection.COLUMN,flex = 1.0)
             )
         ),
         safeArea = SafeArea(
             top = false,
             leading = true,
             trailing = true,
             bottom = true
         )
     )
}
