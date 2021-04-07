package com.viettel.bealglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class DismissDialogAction(val endpoint: String) : Action