package com.viettel.beaglebff

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class BeagleBffApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(BeagleBffApplication::class.java, *args)
		}
	}

	@Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
		return builder.build()
	}
}