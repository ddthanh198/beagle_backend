package com.viettel.beaglebff.components.compose_components


import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.ComposeComponent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text

class TaskComponent(val id: String, var imageUrl: String, val title: String, val location: String, val time: String, val statusImage: String, val status: String, val statusTextColor: String): ComposeComponent {
     override fun build(): ServerDrivenComponent = Container(
         children = listOf(

             Touchable(onPress = listOf(
                 //   CustomAction(taskID = id)
             ),
                 child = Container (

                     children = listOf(
                         Image( ImagePath.Remote(imageUrl)
                             , ImageContentMode.FIT_XY,
                         ).applyStyle(
                             Style(
                                 size  = Size(width = 96.unitReal(), height = 96.unitReal()),
                                 margin = EdgeValue(left = 16.unitReal(), top = 16.unitReal(), right =  17.unitReal()),
                                 cornerRadius = CornerRadius(16.0),
                             )
                         )
                         , Container (
                         children = listOf(
                             Text(title)
                                 .applyStyle(
                                     Style(
                                         margin = EdgeValue( right = 14.unitReal()),
                                         size = Size(height = 19.5.unitReal()),
                                         positionType = PositionType.RELATIVE,
                                     )
                                 ),
                             Container(
                                 children = listOf(
                                     Image(ImagePath.Remote(remoteUrl = "http://localhost:8080/ic_location"))
                                         .applyStyle(
                                             Style(
                                                 size = Size(width = 12.6.unitReal(),height = 16.3.unitReal()),
                                                 positionType = PositionType.RELATIVE,
                                             )
                                         ),
                                     Text(location, textColor = "#617882")
                                         .applyStyle(
                                             Style(
                                                 margin = EdgeValue( left = 10.unitReal()),
                                                 size = Size(height = 19.5.unitReal()),
                                                 positionType = PositionType.RELATIVE,
                                             )
                                         ).applyFlex(
                                             Flex(
                                                 flexWrap = FlexWrap.WRAP
                                             )
                                         ),
                                 )
                             ).applyFlex(
                                 Flex(
                                     flexDirection = FlexDirection.ROW,
                                 )
                             ).applyStyle(
                                 Style(
                                     margin = EdgeValue(  top = 12.unitReal()),
                                 )
                             )
                             , Container (
                             children = listOf(
                                 Image(ImagePath.Remote(remoteUrl = "http://localhost:8080/ic_clock"))
                                     .applyStyle(
                                         Style(
                                             size = Size(width = 16.unitReal(),height = 16.unitReal()),
                                             positionType = PositionType.RELATIVE,
                                         )
                                     ),
                                 Text(time, textColor = "#617882")
                                     .applyStyle(
                                         Style(
                                             margin = EdgeValue( left = 10.unitReal()),
                                             size = Size(height = 19.5.unitReal()),
                                             positionType = PositionType.RELATIVE,
                                         )
                                     ),
                             )
                         ).applyFlex(
                             Flex(
                                 flexDirection = FlexDirection.ROW,
                             )
                         ).applyStyle(
                             Style(
                                 margin = EdgeValue(  top = 12.unitReal()),
                             )
                         ),
                             Container (
                                 children =  listOf(
                                     Image( ImagePath.Remote(statusImage)).applyStyle(
                                         Style(
                                             size = Size(width = 16.unitReal(),height = 16.unitReal()),
                                             positionType = PositionType.RELATIVE,
                                         )
                                     ),
                                     Text(status
                                         ,textColor = statusTextColor)
                                         .applyStyle(
                                             Style(
                                                 margin = EdgeValue( left = 10.unitReal()),
                                                 size = Size(height = 19.5.unitReal()),
                                                 positionType = PositionType.RELATIVE,
                                             )
                                         ),
                                 ),
                             ).applyFlex(
                                 Flex(
                                     flexDirection = FlexDirection.ROW,
                                 )
                             ).applyStyle(
                                 Style(
                                     margin = EdgeValue(  top = 12.unitReal()),
                                 )
                             ),
                         )

                     ).applyStyle(
                         Style(
                             margin = EdgeValue( right = 8.unitReal(),  top = 16.unitReal()),
                             flex = Flex(flex = 1.0)
                         )
                     ), Container (
                         children = listOf()
                     ).applyStyle(
                         Style(
                             backgroundColor = "#E5E5E5",
                             size = Size(height = (0.5).unitReal()),
                             margin = EdgeValue(top = 16.unitReal(), left = 16.unitReal(),right = 8.unitReal()),
                             positionType = PositionType.ABSOLUTE,
                             position = EdgeValue(horizontal = (0.0).unitReal(), bottom = (-6).unitReal())
                         )
                     )
                     ),

                     ).applyStyle(
                     Style(
                         margin = EdgeValue(bottom = 10.unitReal()),
                         positionType = PositionType.RELATIVE
                     )
                 ).applyFlex(
                     Flex(
                         flexDirection = FlexDirection.ROW,
                     )
                 )
             )
         )

     )
}