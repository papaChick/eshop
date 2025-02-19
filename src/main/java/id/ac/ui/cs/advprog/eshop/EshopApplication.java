package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EshopApplication {

	private EshopApplication() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}
}
