package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT
)
@ExtendWith({SeleniumJupiter.class})
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void productCreated_isCorrect(ChromeDriver driver) throws Exception {
        String productName = "Test";
        String productQuantity = "1";

        driver.get(this.baseUrl + "/product/list");
        WebElement createButton = driver.findElement(By.id("btn-create"));
        createButton.click();

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys(productName);

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys(productQuantity);

        WebElement submitButton = driver.findElement(By.id("btn-submit"));
        submitButton.click();

        String resultName = driver.findElement(By.cssSelector("table tbody tr:first-child td:first-child")).getText();
        String resultQuantity = driver.findElement(By.cssSelector("table tbody tr:first-child td:nth-child(2)")).getText();

        assertEquals(productName, resultName);
        assertEquals(productQuantity, resultQuantity);
    }
}