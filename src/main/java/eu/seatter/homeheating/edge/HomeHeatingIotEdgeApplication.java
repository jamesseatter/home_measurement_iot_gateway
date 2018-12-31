package eu.seatter.homeheating.edge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class HomeHeatingIotEdgeApplication {

	public static void main(String[] args) {
		log.info("Application startup");
		SpringApplication.run(HomeHeatingIotEdgeApplication.class, args);
		log.info("Application shutdown");
	}

}
