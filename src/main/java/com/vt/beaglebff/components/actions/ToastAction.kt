package com.vt.beaglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class ToastAction(val msg: String? = null) : Action