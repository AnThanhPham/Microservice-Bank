package com.atp.cards;

import com.atp.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.ATPbytes.cards.controller") })
@EnableJpaRepositories("com.ATPbytes.cards.repository")
@EntityScan("com.ATPbytes.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "ATPBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Madan Reddy",
						email = "tutor@ATPbytes.com",
						url = "https://www.ATPbytes.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.ATPbytes.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "ATPBank Cards microservice REST API Documentation",
				url = "https://www.ATPbytes.com/swagger-ui.html"
		)
)
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
@EnableDiscoveryClient
@EnableFeignClients
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
