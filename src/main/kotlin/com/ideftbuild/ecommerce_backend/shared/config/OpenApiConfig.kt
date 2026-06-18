package com.ideftbuild.ecommerce_backend.shared.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(Info()
                .title("E-commerce API")
                .version("1.0")
                .description("E-commerce REST API")
            )
//            .addSecurityItem(SecurityRequirement().addList("basicAuth"))
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
            .components(Components()
//                .addSecuritySchemes("basicAuth", SecurityScheme()
//                    .type(SecurityScheme.Type.HTTP)
//                    .scheme("basic")
//                )
                .addSecuritySchemes(
                    "bearerAuth",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
    }
}
