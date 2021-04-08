package com.viettel.beaglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action
import com.viettel.beaglebff.common.Gravity

@RegisterAction
data class OpenSideMenuViewController(
    val url: String = "",
    val gravity: Gravity = Gravity.START
) : Action