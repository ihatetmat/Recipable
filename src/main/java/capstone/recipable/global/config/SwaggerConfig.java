package capstone.recipable.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Recipable's API Specifications"
                ,description = "캡스톤 종합 디자인 프로젝트 2"
                , version = "v1")
)
public class SwaggerConfig {
    private static final String BEARER_TOKEN_PREFIX = "Bearer";
    private static final String JWT = "JWT";
    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(JWT);
        Components components = new Components().addSecuritySchemes(JWT, new SecurityScheme()
                .name(JWT)
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER_TOKEN_PREFIX)
                .bearerFormat(JWT)
        );
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components())
                .info(new Info())
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}


