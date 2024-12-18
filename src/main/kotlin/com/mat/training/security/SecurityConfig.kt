package com.mat.training.security


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler


//@Configuration
@EnableWebSecurity
class SecurityConfig {

    //@Autowired
    private lateinit var clientRegistrationRepository: ClientRegistrationRepository

    @Bean
    @Throws(Exception::class)
    fun resourceServerFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/home").authenticated()
                    //.requestMatchers("home/admin/**").hasAnyAuthority("SCOPE_training.trainer")
                    .requestMatchers("/home").hasAnyAuthority("SCOPE_training.trainer", "SCOPE_training.trainee")
                    .requestMatchers("/home/**").hasAnyAuthority("SCOPE_training.trainer", "SCOPE_training.trainee")
                    .anyRequest().authenticated()
                    }
            .oauth2Login() { oauth2 ->
                oauth2.authorizationEndpoint{
                    cfg -> cfg.authorizationRequestResolver(
                        pkceResolver(clientRegistrationRepository)
                    )
                }
            }
            .logout { logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()); };
        return http.build()
    }

    private fun oidcLogoutSuccessHandler(): LogoutSuccessHandler {
        val oidcLogoutSuccessHandler = OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository)

        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("https://localhost:8081/home")
        return oidcLogoutSuccessHandler
    }

    fun pkceResolver(repo: ClientRegistrationRepository?): OAuth2AuthorizationRequestResolver {
        val resolver = DefaultOAuth2AuthorizationRequestResolver(repo, "/oauth2/authorization")
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce())
        return resolver
    }

}