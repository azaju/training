package com.mat.training.security

import jakarta.ws.rs.core.Response
import org.keycloak.Config
import org.keycloak.authentication.AuthenticationFlowContext
import org.keycloak.authentication.Authenticator
import org.keycloak.authentication.AuthenticatorFactory
import org.keycloak.models.*
import org.keycloak.provider.ProviderConfigProperty


class CustomUserFormAuthenticator : Authenticator {
    override fun authenticate(context: AuthenticationFlowContext) {
        //context.challenge(context.form().setAttribute("loginPage", "loginpage.html"))
        // URL complète vers ta page personnalisée
        val redirectUrl = "http://localhost:8081/loginpage.html" // Remplace par l'URL complète de ta page

        // Crée une réponse de redirection HTTP
        val response = Response.status(Response.Status.FOUND) // 302
            .location(java.net.URI(redirectUrl))
            .build()

        // Déclenche la redirection
        context.challenge(response)
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun action(context: AuthenticationFlowContext?) {
        TODO("Not yet implemented")
    }

    override fun requiresUser(): Boolean = false

    override fun configuredFor(session: KeycloakSession?, realm: RealmModel?, user: UserModel?): Boolean = true

    override fun setRequiredActions(session: KeycloakSession?, realm: RealmModel?, user: UserModel?) {
        TODO("Not yet implemented")
    }
}
