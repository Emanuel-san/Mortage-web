package app.mortgageweb;

import app.mortgageweb.controllers.MortgageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = MortgageController.class)
public class MortgageWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageWebApplication.class, args);
	}
}
