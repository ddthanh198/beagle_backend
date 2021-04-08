package com.viettel.beaglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.context.ContextData

@RegisterAction
data class OpenDateRangePicker(
    val callback: ContextData? = null
) : Action
