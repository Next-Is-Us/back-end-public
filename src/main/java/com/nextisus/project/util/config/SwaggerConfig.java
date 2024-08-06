package com.nextisus.project.util.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 로컬 : http://localhost:8080/swagger-ui/index.html
 * 서버 : https://15.164.134.131/swagger-ui/index.html
 */

@Configuration
public class SwaggerConfig {

    // TODO : JWT 추가 후 JWT 관련 설정 추가 필요

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("오늘의 맘 API")
                .description("개발 진행중")
                .version("0.0.1");
    }
}
