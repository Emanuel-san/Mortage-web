package app.mortageweb;

import app.mortageweb.controllers.MortageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackageClasses = MortageController.class)
public class MortageWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortageWebApplication.class, args);
	}
}
