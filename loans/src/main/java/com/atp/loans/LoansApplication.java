package com.atp.loans;

import com.atp.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.ATPbytes.loans.controller") })
@EnableJpaRepositories("com.ATPbytes.loans.repository")
@EntityScan("com.ATPbytes.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "ATPBank Loans microservice REST API Documentation",
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
				description = "ATPBank Loans microservice REST API Documentation",
				url = "https://www.ATPbytes.com/swagger-ui.html"
		)
)
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@EnableDiscoveryClient
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
