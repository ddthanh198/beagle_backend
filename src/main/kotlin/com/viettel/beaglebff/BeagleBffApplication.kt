package com.viettel.beaglebff

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.runApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication
class BeagleBffApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(BeagleBffApplication::class.java, *args)
		}
	}
}