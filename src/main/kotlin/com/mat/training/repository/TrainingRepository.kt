package com.mat.training.repository

import com.mat.training.domain.Training
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainingRepository : MongoRepository<Training, String> {


}