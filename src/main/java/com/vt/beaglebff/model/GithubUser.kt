package com.vt.beaglebff.model

import br.com.zup.beagle.core.*
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text

data class SearchContext(
    var key: String? = null,
    var users: List<GithubUser>? = null,
    var isShowLoading: Display = Display.NONE,
    var isEmptyResult: Display = Display.NONE
)

data class GithubUser(
    var login: String? = null,
    var avatar_url: String? = null,
    var id: Int? = null,
    var node_id: String? = null)

class UserCell(
    private val avatar_url: String,
    private val login: String,
    private val node_id: String
) : ComposeComponent {

    override fun build(): ServerDrivenComponent {
        return Container(
            children = listOf(
                Image(
                    ImagePath.Remote(avatar_url)
                ).applyStyle(
                    Style(
                        size = Size(width = 80.unitReal(), height = 80.unitReal()),
                        cornerRadius = CornerRadius(40.0)
                    )
                ),
                Container(
                    children = listOf(
                        Text(login).applyStyle(Style(margin = EdgeValue(bottom = 10.unitReal()))),
                        Text(node_id)
                    )
                ).applyStyle(
                    Style(
                        padding = EdgeValue(left = 16.unitReal())
                    )
                ),
                Container(
                    children = listOf()
                ).applyStyle(
                    Style(
                        positionType = PositionType.ABSOLUTE,
                        position = EdgeValue(horizontal = 0.unitReal(), bottom = 0.unitReal()),
                        size = Size(height = (0.3).unitReal()),
                        backgroundColor = "#000000"
                    )
                )
            )
        ).applyStyle(
            Style(
                size = Size(height = 100.unitReal()),
                margin = EdgeValue(horizontal = 20.unitReal()),
                flex = Flex(
                    flexDirection = FlexDirection.ROW,
                    alignItems = AlignItems.CENTER
                )
            )
        )
    }
}