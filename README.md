<details>
    <summary><h1>Tutorial 01</h1></summary>

## Reflection 1
After I reviewed my code, I'm confident that I've applied most of the clean code principles and secure coding practices. The variables of my code has been named accordingly. For example:
```Java
public Product edit(Product changes, UUID id) {
    Product product = findById(id);

    // Set name & quantity  
    if (product != null) {
        product.setProductName(changes.getProductName());
        product.setProductQuantity(changes.getProductQuantity());
    }

    return product;
}
```
"Changes" clearly states that it is an object of product which its attributes are the incoming changes inputted by the user, and "product" states that it is the object being modified.

The functions in the source code are broken down to its smallest form. It's written to perform a single task and be small in size. For example:
```java
public Product findById(UUID id) {
    for (Product product : productData) {
        if (product.getProductId().equals(id)) {
            return product;
        }
    }
    return null;
}
```
This improves readability of the code, as it reduce clutteredness of the code. It eliminates the need to replicate the same line of codes over different functions.

Most of the clean code principles & secure coding practices are handled by Spring Boot. Spring Boot is an MVC (Model-View-Controller) framework, which generally have their data structure's behaviour encapsulated. It's done to prevent the data being exposed directly. An example of this is the accessors (Setter & Getter) of the Product object. Furthermore, Spring Boot encodes data sent to users properly. I tried populating the input with common XSS payloads, and Spring Boot successfully prevents it from running. Spring Boot encodes the data into HTML Entity, therefore makes it clearer to parse HTML tags and data.

The web project is a small one, it only have 3 features, which are Create, Update, and Delete. I don't find the need to comment the source code, the functions are quite self-explanatory. It should be noted that Spring Boot does not enable input validation by default. In order to cover it, dependencies such as Jakarta Validation (Bean Validation API) and Spring Boot’s built-in validation support has to be added to project.

## Reflection 2
### Part 1
Writing unit tests ensures my code is valid and functions as intended. I've realized that unit and functional testing are essential in software development. They speed up testing, making the process more efficient. Without automation, testing would be slow and time-consuming.

Determining the number of unit tests required for a class depends on several factors, including the complexity of the class, the number of methods, and the potential edge cases that need to be covered. A rule-of-thumb is to write unit tests for each methods and additional tests to cover the potential edge cases and error handling.

To ensure that our unit tests is enough to verify the program, it's best to utilize code coverage tools. Code coverage, as its name suggests, measures how much of your code is executed while running tests. 100% code coverage doesn't imply that your code is bug-free and invulnerable, it simply suggests that each line within your code is executed. Therefore, code coverage cannot be used as proof that the code is flawless, it simply reflects the extent of test execution.

### Part 2
The new functional test suite would negatively impact the code cleanliness. Duplicating setup and instance variables from `CreateProductFunctionalTest.java` leads to redundant code, increasing the code size and introduces higher risk of error for future maintenance.
To improve this, a better approach is to create a parent class that contains the shared setup logic and use inheritance to avoid duplication. Additionally, minimizing instance variables helps reduce confusion and improves readability.

</details>

<details open>
    <summary><h1>Tutorial 2</h1></summary>

## Reflection 1
>List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

1."This class has only private constructors and may be final' & 'This utility class has a non-private constructor"

To solve this issue, I added `@SuppresionWarning` in `EshopApplication.java`

```java
package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

}
```

2. "Unused import 'org.springframework.web.bind.annotation.*"
This issue is found in 2 files, `HomepageController.java` & `ProductController.java`. My solution is since the asterisk represents 'all', we specifically import what's needed in the code.
Example:

from : 
```java
import org.springframework.web.bind.annotation.*;
```

to : 
```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
```

3. "Unnecessary modifier 'public' on method '<insert method>': the method is declared in an interface type"
    This code issue is present in `ProductService.java`. To fix it, simply remove 'public modifier on each method.
```java
public interface ProductService {
    Product create(Product product);
    Product edit(Product changes, String id);
    void delete(String id);
    Product findById(String id);
    List<Product> findAll();
}
```

## Reflection 2
> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

The current implementation meets the principles of Continuous Integration because every push and pull request triggers an automated test suite and code quality analysis using GitHub Actions. Not to mention, the whole test suite has a test coverage of 100%. It also implements Continuous Deployment by automatically deploying the latest changes to a PaaS, ensuring that the latest working version is always available online. 


</details>

