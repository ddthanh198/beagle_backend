package com.viettel.beaglebff.service

import br.com.zup.beagle.widget.action.RequestActionMethod
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import com.viettel.beaglebff.builder.WidgetBuilder
import com.viettel.beaglebff.components.actions.DismissDialogAction
import com.viettel.beaglebff.components.actions.LoadingAction
import com.viettel.beaglebff.model.TaskResponse

import javafx.scene.control.Alert
import org.springframework.stereotype.Service

@Service
class WidgetService {
    fun createBottomNavigationView() = WidgetBuilder.createBottomNavigationView()

    fun createBannerView() = WidgetBuilder.createBannerView()

    fun createSelectLanguageDialog() = WidgetBuilder.createLanguageSelectionDialog()

    fun createBottomSheetDialog() = WidgetBuilder.createDemoBottomSheetDialog()

    fun createBasicDialog() = WidgetBuilder.createBasicDialog(title = "Thông báo", "Mời bạn chọn giới tính của bạn","Title.Text.BasicDialog", "Content.Text.BasicDialog"
        , "Đồng ý", "Huỷ bỏ", "Accept.Button.Style", "Cancel.Button.Style", acceptAction = arrayListOf(
        SetContext( contextId = "global",
            path = "taskModels",
            value = TaskResponse()
        ),

        DismissDialogAction(""),
        LoadingAction(true)
        ,
        SendRequest(
            url = "http://171.244.149.211:8090/api/1.0/tasks?include=process,processRequest,processRequest.user,user&statusfilter=ACTIVE,CLOSED,DRAFT,PAUSE,ASSIGNED&order_by=ID&count_type=none&order_direction=DESC&per_page=20&page=@{sum(taskModels.meta.current_page, 1)}&process_owner=14&advanced_search[]={\"label\":\"Trạng thái\",\"field\":\"status\",\"value\":[\"ASSIGNED\",\"ACTIVE\"],\"hidden_mobile\":false,\"compare\":\"and\",\"condition\":\"=\",\"type\":\"TASK:STATUS\",\"config\":{\"typeField\":\"local\",\"value\":[{\"name\":\"Chờ tiếp nhận\",\"value\":\"DRAFT\"},{\"name\":\"Đang thực hiện\",\"value\":\"ACTIVE\"},{\"name\":\"Đã hoàn thành\",\"value\":\"CLOSED\"}]}}&created_start_date=&created_end_date=",
            method = RequestActionMethod.GET,
            headers = mapOf(
                "Content-Type" to "application/json",
                "Authorization" to "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjY4YjRlZDczZTRjOWU2YzU3NzgzYzA2NzczMWJhNGE3NWQwMzY5NmEzZDZlOGYxOGI4NTlmMWFmYTE1NDM1MDljZjZhY2ExYjg4NDczYWUwIn0.eyJhdWQiOiIxIiwianRpIjoiNjhiNGVkNzNlNGM5ZTZjNTc3ODNjMDY3NzMxYmE0YTc1ZDAzNjk2YTNkNmU4ZjE4Yjg1OWYxYWZhMTU0MzUwOWNmNmFjYTFiODg0NzNhZTAiLCJpYXQiOjE2MTc5NjM1MjksIm5iZiI6MTYxNzk2MzUyOSwiZXhwIjoxNjE3OTc3OTI4LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.xpTfat8n5BoLou9Eh9RHukaI2Iq-2ia0nEl0biSD1oZNNs9kzTGqSP47LAoeL0nOHDRxEbWiL9dcaWp_cOf2MH0-PpdZjFBU0Acoi7ZBqySsn0zad3alFljFuL1mT2h3yBqTkE4FrFdGNUfg0WkDXBVRRnwPGhQR2_dGFaaxKQ2rrPN53JasutRevtptpN4AMdjw0bGCbXPhici-_Gf966jDUNJiNLohJ8X0olY-XS0lFNSfGyMSUil7TKql7WAUSyLv9xIQbr-ZHQ23TEWgoQHQtuM8eJagp0ied9S5lpNiPa3IS8o7uvNsqpwh8d8TlLzSXKJ6cVzKUDd7Je2Wmz34XJlA2KAMEwqa4l1VBk7AG4EF9ABupiw78w2Gkvi8vsV5nvS8oRkx1-jlo96CLQum22pcUomaE8iPKIYCMT5B5Ck1XXnq1HNnUIEDd9qYvxDsAfbc0O18YcvWHHswmnWygDhnGqL2p8NpRuWL9XenKBKYhozkDl5METqJdapDCHnHW4XSFNb1_7gywJvExhjW6qi8nzK05j7GYh7UEsG-itNganwVRiV4Y7F0ar63kJc-nyO0lOU6aIJSab9_bhV0TbU1iVogeGaJg_MetjHNwbjqWB7pNO__JoVbvbVYQYJ-12gORPWli2MTTyE2caTP3xLJYupvT1PiFLngPHk"
            ),
            onSuccess = listOf (
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
                // DismissAction("")
                //    CustomAction("hideLoading")
            ), onError = listOf(
            //   CustomAction("hideLoading"),

            LoadingAction(false)
            )
        )
    )
        , cancelAction = arrayListOf(
           DismissDialogAction("")
        )
    )

}