package com.vt.beaglebff.controller

import com.vt.beaglebff.service.ResourcesService
import org.springframework.core.io.InputStreamResource
import org.springframework.http.ResponseEntity
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import java.io.IOException
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/resourcesController")
class ResourcesController(private val resourcesService: ResourcesService) {

    @RequestMapping("/ic_account_info", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getAccountInfoIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_account_info.png", "PNG")
    }

    @RequestMapping("/ic_settings", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getSettingIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_settings.png", "PNG")
    }

    @RequestMapping("/ic_change_password", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getChangePasswordIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_change_password.png", "PNG")
    }

    @RequestMapping("/ic_info", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getInfoIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_info.png", "PNG")
    }

    @RequestMapping("/ic_logout", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getLogoutIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_logout.png", "PNG")
    }

    @RequestMapping("/ic_next", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getNextIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_next.png", "PNG")
    }

    @RequestMapping("/img_default_avatar", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getDefaultAvatar(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/img_default_avatar.png", "PNG")
    }

    @RequestMapping("/img_tnxh", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_JPEG_VALUE))
    @Throws(IOException::class)
    fun getBanner1(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/img_te_nan_xa_hoi.jpeg", "JPEG")
    }

    @RequestMapping("/ic_home", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getHomeIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_home_outlined.png", "PNG")
    }

    @RequestMapping("/ic_home_filled", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getHomeIconFilled(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_home_filled.png", "PNG")
    }

    @RequestMapping("/ic_request", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getRequestIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_requests_outlined.png", "PNG")
    }

    @RequestMapping("/ic_task", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getTaskIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_tasks_outlined.png", "PNG")
    }

    @RequestMapping("/ic_notification", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getNotificationIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_notification_outlined.png", "PNG")
    }

    @RequestMapping("/ic_notification_white", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getNotificationWhiteIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_notification.png", "PNG")
    }

    @RequestMapping("/ic_avatar", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.IMAGE_PNG_VALUE))
    @Throws(IOException::class)
    fun getAvatarPlaceholder(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("home_icons/ic_avatar_2.png", "PNG")
    }

    @RequestMapping("/ic_back", method = [RequestMethod.GET], produces = [MediaType.IMAGE_PNG_VALUE])
    @Throws(IOException::class)
    fun getBackIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_back.png", "PNG")
    }

    @RequestMapping("/ic_tick", method = [RequestMethod.GET], produces = [MediaType.IMAGE_PNG_VALUE])
    @Throws(IOException::class)
    fun getTickIcon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_tick.png", "PNG")
    }

    @RequestMapping("/ic_setting", method = [RequestMethod.GET], produces = [MediaType.IMAGE_PNG_VALUE])
    @Throws(IOException::class)
    fun getSetting2Icon(): ResponseEntity<InputStreamResource> {
        return resourcesService.getImage("images/ic_setting.png", "PNG")
    }
}