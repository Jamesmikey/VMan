package tz.ac.iact.va;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@OpenAPIDefinition(info = @Info(title = "VMAN", version = "1.0", description = "Verbal Autopsy Manager API"),
		servers = {
				@Server(url = "/"),
		})
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		openIdConnectUrl = "ft"
)
@SpringBootApplication()
@EnableMongoRepositories()
//@EnableScheduling
//@EnableAsync
//@EnableConfigurationProperties()
public class VaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaApplication.class, args);
	}

}
