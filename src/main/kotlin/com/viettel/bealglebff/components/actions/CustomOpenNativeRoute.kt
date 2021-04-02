package com.viettel.bealglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class CustomOpenNativeRoute(val path: String) : Action