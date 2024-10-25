package com.mat.training.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JvmRecord
@Document(collection = "users")
data class Training(@Id val id: Int?,  val name: String, val duration: Int, val trainingId: String) {

}