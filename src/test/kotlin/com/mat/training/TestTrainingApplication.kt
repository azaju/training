package com.mat.training

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<TrainingApplication>().with(TestcontainersConfiguration::class).run(*args)
}
