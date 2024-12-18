package com.mat.training.controller

import com.mat.training.TrainingAppUtils
import com.mat.training.domain.Training
import com.mat.training.service.TrainingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


@Controller
class TrainingController (private val trainingService : TrainingService){

    @GetMapping("/")
    fun slash(): String {
        return "redirect:/loginpage"  // Renvoie le nom de la vue loginpage.html
    }

   @GetMapping("/home")
    fun home(): String {
        return "home"  // Renvoie le nom de la vue loginpage.html
    }

    @GetMapping("/loginpage")
    fun loginPage (token : OAuth2AuthenticationToken, @RegisteredOAuth2AuthorizedClient client :  OAuth2AuthorizedClient): ModelAndView {
        var principal:OidcUser = token.getPrincipal() as OidcUser
        var model = generateDefaultModel(token);

        //Redirect to home view
        model.setViewName("home");
        // Add accesstoken, refreshtoken and idtoken
        model.addObject("accesstoken",
            TrainingAppUtils.prettyBody(client.getAccessToken().getTokenValue()));
        model.addObject("refreshtoken",
            TrainingAppUtils.prettyBody(client.getRefreshToken()?.getTokenValue() ?: "null"));
        model.addObject("idtoken",
            TrainingAppUtils.prettyBody(principal.getIdToken().getTokenValue()));
        return model;
    }

    @PostMapping()
    fun createTraining(@RequestBody training: Training, token: OAuth2AuthenticationToken): ResponseEntity<Training> {
        val savedTraining =  trainingService.createTraining(training)
        return ResponseEntity(savedTraining, HttpStatus.CREATED)
    }

    /*
     * Sets some basic user information. The call can add more properties
     * to it before passing to the view file.
     */
    private fun generateDefaultModel(token: OAuth2AuthenticationToken): ModelAndView {
        val principal = token.principal as OidcUser

        val model = ModelAndView()
        model.addObject("user", principal)
        return model
    }

   /*@GetMapping("/trainings")
    fun getAllTrainings(): List<Training> = trainingService.getAllTrainings()

    @PostMapping("/add")
    fun addTraining(@RequestParam name: String): Training {
        return trainingService.addTraining(Training(name))
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, secured user!"
    }

    @GetMapping(path = ["/"])
    fun index(): String {
        return "external"
    }

    @GetMapping(path=["/error"])
    fun error(): String {
        return "error"
    }*/
}