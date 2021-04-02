package com.example.beagleProject.components.actions

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class ShowDialogAction(val dialogEndpoint: String) : Action