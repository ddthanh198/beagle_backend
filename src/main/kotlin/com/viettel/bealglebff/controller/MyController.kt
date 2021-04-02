package com.viettel.bealglebff.controller
import br.com.zup.beagle.widget.layout.ScreenBuilder
import com.viettel.bealglebff.service.MyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

class MessageBody {
    val msg: String = ""
}

@RestController
class MyController(private val myService: MyService) {

    @GetMapping("/screen")
    fun getScreen() : ScreenBuilder = myService.getMyScreen()

    @PostMapping("/screen_test")
    fun getScreenTest(@RequestBody body: MessageBody) : ScreenBuilder {
        println("MINHMON ${body.msg}")
        return myService.getMyScreen()
    }
}
