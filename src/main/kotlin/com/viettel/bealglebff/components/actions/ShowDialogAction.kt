package com.viettel.bealglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class ShowDialogAction(val endpoint: String, val numberOfItems: Int?) : Action