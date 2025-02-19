package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EshopApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		EshopApplication.main(new String[]{});
		assertNotNull(context);
	}

}
