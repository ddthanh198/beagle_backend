package com.viettel.beaglebff.service

import com.viettel.beaglebff.common.Constants.BASE_URL
import com.viettel.beaglebff.common.Constants.CachedFile
import com.viettel.beaglebff.common.Constants.VERSION_BOTTOM_NAV_VIEW
import com.viettel.beaglebff.common.Constants.VERSION_TAB_CHART
import com.viettel.beaglebff.common.Constants.VERSION_TAB_HOME
import com.viettel.beaglebff.common.Constants.VERSION_TAB_NOTIFICATION
import com.viettel.beaglebff.common.Constants.VERSION_TAB_REQUEST
import com.viettel.beaglebff.common.Constants.VERSION_TAB_TASK
import com.viettel.beaglebff.model.VersionModel
import org.springframework.stereotype.Service

@Service
class VersionService {
    fun getLatestBeagleScreenVersion(): List<VersionModel> {
        val versionList = ArrayList<VersionModel>()
        versionList.addAll(
            listOf(
                VersionModel(
                    CachedFile.BottomNavigationView.name,
                    VERSION_BOTTOM_NAV_VIEW,
                    "${BASE_URL}/widgetController/bottomNavigationView"
                ),
                VersionModel(
                    CachedFile.TabHome.name,
                    VERSION_TAB_HOME,
                    "${BASE_URL}/screenController/tabHome"
                ),
                VersionModel(
                    CachedFile.TabRequest.name,
                    VERSION_TAB_REQUEST,
                    "${BASE_URL}/screenController/tabRequest"
                ),
                VersionModel(
                    CachedFile.TabTask.name,
                    VERSION_TAB_TASK,
                    "${BASE_URL}/screenController/tabTask"
                ),
                VersionModel(
                    CachedFile.TabNotification.name,
                    VERSION_TAB_NOTIFICATION,
                    "${BASE_URL}/screenController/tabNotification"
                ),
                VersionModel(
                    CachedFile.TabChart.name,
                    VERSION_TAB_CHART,
                    "${BASE_URL}/screenController/tabChart"
                )
            )
        )
        return versionList
    }
}