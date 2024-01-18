package com.blockchain.voteguardian.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("투표가디언 API")
                        .description("블록체인을 활용한 투명한 투표 시스템을 제공합니다")
                        .version("1.0.0"));
    }
}
