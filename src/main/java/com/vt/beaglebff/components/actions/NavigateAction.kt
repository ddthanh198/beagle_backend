package com.vt.beaglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class NavigateAction(val destination : String) : Action