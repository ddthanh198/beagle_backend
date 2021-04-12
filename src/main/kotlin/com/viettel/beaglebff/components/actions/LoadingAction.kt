package com.viettel.beaglebff.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class LoadingAction(val isLoading : Boolean) : Action