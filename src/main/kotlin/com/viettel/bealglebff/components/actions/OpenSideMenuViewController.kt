package com.viettel.bealglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action
import com.viettel.bealglebff.common.Gravity

@RegisterAction
data class OpenSideMenuViewController(
    val url: String = "",
    val gravity: Gravity = Gravity.START
) : Action