package com.vt.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.context.Bind

@RegisterWidget
class ItemRowPersonal(val text: Bind<String>, val iconRemoteUrl: Bind<String>): Widget()