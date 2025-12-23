package com.restful.nubank.technical.test.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openApi(): OpenAPI =
        OpenAPI().info(
            Info()
                .title("Desafio Back-End Nubank - Clientes e Contatos")
                .version("1.0.0")
                .description("API REST para cadastro/listagem de clientes e seus contatos.")
        )
}