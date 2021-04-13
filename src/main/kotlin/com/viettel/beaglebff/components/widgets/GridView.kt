package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextComponent
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.ListDirection

@RegisterWidget()
data class GridView
constructor (
    val spanCount : Int = 2,
    val direction: ListDirection = ListDirection.VERTICAL,
    override val context: ContextData? = null,
    val onInit: List<Action>? = null,
    val dataSource: Bind<List<Any>>? = null,
    val template: ServerDrivenComponent? = null,
    val onScrollEnd: List<Action>? = null,
    val scrollEndThreshold: Int? = null,
    val iteratorName: String = "item",
    val key: String? = null
) : Widget(), ContextComponent {}