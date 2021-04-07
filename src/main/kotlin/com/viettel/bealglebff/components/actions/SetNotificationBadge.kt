package com.viettel.bealglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class SetNotificationBadge(
    val number: Int,
    val badgeBackgroundColor: String,
    val badgeTextColor: String
) : Action