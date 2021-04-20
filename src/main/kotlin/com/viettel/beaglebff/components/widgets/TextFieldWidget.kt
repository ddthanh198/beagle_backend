
package com.viettel.beaglebff.components.widgets

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Action
import br.com.zup.beagle.widget.context.Bind

@RegisterWidget
class TextFieldWidget(val placeHolder: String, val height: Int, val isRequiredTextField: Boolean,
                      val isAlwaysVisibleClearButton: Boolean, var text: Bind.Expression<String>, onChange: List<Action>): Widget() {
}
