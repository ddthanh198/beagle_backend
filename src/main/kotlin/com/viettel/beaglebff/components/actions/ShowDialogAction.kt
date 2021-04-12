package com.viettel.beaglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class ShowDialogAction(val endpoint: String, val numberOfItems: Int?, val height: Int? = 0, val width: Int? = 0) : Action