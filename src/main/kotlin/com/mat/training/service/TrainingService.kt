package com.mat.training.service

import com.mat.training.domain.Training
import com.mat.training.repository.TrainingRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Service

@Service
class TrainingService (private val trainingRepository: TrainingRepository) {

    fun getAllTrainings(): List<Training> = trainingRepository.findAll()

    //fun addTraining(training : Training): Training = trainingRepository.save(training)

    fun createTraining(training: Training): Training {
        //get user in order to get the creator of the training
        //val token =  SecurityContextHolder.getContext().authentication;
        //val principal =  token.principal;
        return trainingRepository.save(training)
    }
}