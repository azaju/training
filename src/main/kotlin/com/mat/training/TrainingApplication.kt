package com.mat.training

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class TrainingApplication

fun main(args: Array<String>) {
	runApplication<TrainingApplication>(*args)
}
