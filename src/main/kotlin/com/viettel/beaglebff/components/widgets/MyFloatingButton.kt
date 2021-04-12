package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action

@RegisterWidget
data class MyFloatingButton(val image: String, val backgroundColor: String): Widget()