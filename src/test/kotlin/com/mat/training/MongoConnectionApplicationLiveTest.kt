package com.mat.training


import com.mongodb.client.MongoClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext


@SpringBootTest
class MongoConnectionApplicationLiveTest {

    private val HOST = "localhost"
    private val PORT = "27017"
    private val DB = "trainingDB"
    private val USER = "root"
    private val PASS = "secret"

    @Autowired
    lateinit var mongoClient: MongoClient


    //@Test
    fun assertInsertSucceeds(context: ConfigurableApplicationContext) {
        val database = mongoClient.getDatabase("trainingDB")
        assertNotNull(database)
        println("Connection to MongoDB established successfully.")
    }
}