package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.Bind
import javax.xml.crypto.Data

@RegisterWidget
class ImagePicker(val url: String, val width: Int? = 0, val height: Int? = 0, val cornerRadius: Double? = 0.0): Widget() {
}
