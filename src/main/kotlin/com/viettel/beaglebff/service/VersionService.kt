package com.viettel.beaglebff.service

import com.viettel.beaglebff.entity.CacheVersion
import com.viettel.beaglebff.model.version.ComponentData
import com.viettel.beaglebff.model.version.VersionModel
import com.viettel.beaglebff.repository.CacheVersionRepository
import org.apache.maven.artifact.versioning.ComparableVersion
import org.json.simple.JSONObject
import org.json.simple.JSONValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class VersionService(
    private val cacheVersionRepository: CacheVersionRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(VersionService::class.java)

    @Autowired
    private val restTemplate: RestTemplate? = null

    fun getLatestBeagleScreenVersion(versionCollection: List<VersionModel>?): List<ComponentData> {
        logger.info("# of components from client: " + versionCollection?.size)
        val response = ArrayList<ComponentData>()

        if (versionCollection.isNullOrEmpty()) {
            val componentList = cacheVersionRepository.findAll() as ArrayList<CacheVersion>
            logger.info("# of records in DB: " + componentList.size)

            for (component: CacheVersion in componentList) {
                val componentData = getBeagleComponentJson(component)
                response.add(componentData)
            }
        } else {
            for (versionModel: VersionModel in versionCollection) {
                val cacheVersion = cacheVersionRepository.getCacheVersionByName(versionModel.component)
                val clientVersion = ComparableVersion(versionModel.version)
                val serverVersion = ComparableVersion(cacheVersion.version)

                if (clientVersion < serverVersion) {
                    val componentData = getBeagleComponentJson(cacheVersion)
                    response.add(componentData)
                }
            }
        }

        return response
    }

    private fun getBeagleComponentJson(component: CacheVersion): ComponentData {
        val host = "http://localhost:8080"
        val jsonUrl = host + component.api
        val jsonString = restTemplate?.getForObject(jsonUrl, String::class.java)

        return ComponentData(
            name = component.name,
            version = component.version,
            json = JSONValue.parse(jsonString) as JSONObject?
        )
    }
}

//        logger.info("List size = " + componentList.size)
//
//        val versionList = ArrayList<VersionModel>()
//        versionList.addAll(
//            listOf(
//                VersionModel(
//                    CachedFile.BottomNavigationView.name,
//                    VERSION_BOTTOM_NAV_VIEW,
//                    "${BASE_URL}/widgetController/bottomNavigationView"
//                ),
//                VersionModel(
//                    CachedFile.TabHome.name,
//                    VERSION_TAB_HOME,
//                    "${BASE_URL}/screenController/tabHome"
//                ),
//                VersionModel(
//                    CachedFile.TabRequest.name,
//                    VERSION_TAB_REQUEST,
//                    "${BASE_URL}/screenController/tabRequest"
//                ),
//                VersionModel(
//                    CachedFile.TabTask.name,
//                    VERSION_TAB_TASK,
//                    "${BASE_URL}/screenController/tabTask"
//                ),
//                VersionModel(
//                    CachedFile.TabNotification.name,
//                    VERSION_TAB_NOTIFICATION,
//                    "${BASE_URL}/screenController/tabNotification"
//                ),
//                VersionModel(
//                    CachedFile.TabChart.name,
//                    VERSION_TAB_CHART,
//                    "${BASE_URL}/screenController/tabChart"
//                )
//            )
//        )
//        return versionList