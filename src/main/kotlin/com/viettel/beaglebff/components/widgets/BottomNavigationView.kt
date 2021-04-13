package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import com.viettel.beaglebff.model.TabInfo

@RegisterWidget
class BottomNavigationView(
        val tabItems: List<TabInfo>,
        val selectedColor: String? = "#3596EC",
        val unselectedColor: String? = "#788793"
): Widget()