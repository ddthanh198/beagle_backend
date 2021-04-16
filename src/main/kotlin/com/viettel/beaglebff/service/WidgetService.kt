package com.viettel.beaglebff.service

import br.com.zup.beagle.widget.action.RequestActionMethod
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.layout.Container
import com.viettel.beaglebff.builder.WidgetBuilder
import com.viettel.beaglebff.common.Constants
import com.viettel.beaglebff.components.actions.DismissDialogAction
import com.viettel.beaglebff.components.actions.LoadingAction
import com.viettel.beaglebff.entity.CacheVersion
import com.viettel.beaglebff.model.TaskResponse
import com.viettel.beaglebff.repository.CacheVersionRepository
import org.springframework.stereotype.Service

@Service
class WidgetService(private val cacheVersionRepository: CacheVersionRepository) {

    fun createBottomNavigationView() : Container {
        val tabArray = ArrayList<String>()
        tabArray.addAll(
            listOf("TabHome", "TabTask", "TabRequest", "TabNotification", "TabChart")
        )
        val listCacheVersion = ArrayList<CacheVersion>()
        for (tab: String in tabArray) {
            val cacheVersion = cacheVersionRepository.getCacheVersionByName(tab)
            listCacheVersion.add(cacheVersion)
        }
        return WidgetBuilder.createBottomNavigationView(listCacheVersion)
    }

    fun createBannerView() = WidgetBuilder.createBannerView()

    fun createSelectLanguageDialog() = WidgetBuilder.createLanguageSelectionDialog()

    fun createBottomSheetDialog() = WidgetBuilder.createDemoBottomSheetDialog()

    fun createBasicDialog() = WidgetBuilder.createBasicDialog(title = "Thông báo", "Có những lúc giật mình trong giấc ngủ\n" +
        "Em vô tình bị kiến đốt vào mông\n" +
        "Chợt mơ về một buổi chiều hồng\n" +
        "Em cùng anh đi lên đồi hóng gió\n" +
        "Và ở đó giữa muôn trùng ngọn cỏ\n" +
        "Em lại vô tình bị kiến đốt vào mông.\n" +
        "Anh cũng mong đến một ngày nào đó\n" +
        "Giữa một chiều yêu thương lồng lộng gió\n" +
        "Ta vô tình gặp nhau trên đồng cỏ\n" +
        "Anh cũng ước mình bị kiến đốt vào mông.","Title.Text.BasicDialog", "Content.Text.BasicDialog"
        , "Đồng ý", "Huỷ bỏ", "Accept.Button.BasicDialog", "Cancel.Button.BasicDialog", acceptAction = arrayListOf(
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
                "Authorization" to "${Constants.TOKEN}"
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