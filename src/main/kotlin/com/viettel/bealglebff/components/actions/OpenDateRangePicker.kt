package com.viettel.bealglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.ContextData

@RegisterAction
data class OpenDateRangePicker(val context: ContextData) : Action