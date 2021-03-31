package com.vt.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.context.Bind

@RegisterWidget
class ItemRowDivider(
        val dividerColor: Bind<String>,
        val dividerHeight: Bind<Int>,
        private val leftMargin: Int? = null,
        private val topMargin: Int? = null,
        private val rightMargin: Int? = null,
        private val bottomMargin: Int? = null
): Widget()