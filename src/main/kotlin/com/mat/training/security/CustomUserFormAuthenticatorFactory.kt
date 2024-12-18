package com.mat.training.security

import org.keycloak.authentication.Authenticator
import org.keycloak.authentication.AuthenticatorFactory
import org.keycloak.models.AuthenticationExecutionModel
import org.keycloak.models.KeycloakSession
import org.keycloak.models.KeycloakSessionFactory
import org.keycloak.provider.ProviderConfigProperty

class CustomUserFormAuthenticatorFactory : AuthenticatorFactory {
    override fun create(session: KeycloakSession): Authenticator {
        return CustomUserFormAuthenticator()
    }


    override fun postInit(factory: KeycloakSessionFactory) {
        // Nothing to do
    }

    override fun getId(): String = "custom-authenticator"

    override fun init(config: org.keycloak.Config.Scope?) {}

    override fun close() {}

    override fun getDisplayType(): String = "Custom Authenticator"
    override fun getReferenceCategory(): String {
        return ""
    }

    override fun isConfigurable(): Boolean = false

    override fun getRequirementChoices(): Array<AuthenticationExecutionModel.Requirement> {
        return arrayOf(
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.ALTERNATIVE,
            AuthenticationExecutionModel.Requirement.DISABLED
        )
    }

    override fun isUserSetupAllowed(): Boolean = false

    override fun getHelpText(): String = "A custom authenticator"

    override fun getConfigProperties(): MutableList<ProviderConfigProperty> {
        return mutableListOf() // Pas de propriétés de configuration pour cet authenticator
    }


}