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

<details>
    <summary><h1>Tutorial 02</h1></summary>

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
<details open>
<summary><h1>Tutorial 03</h1></summary>

## Implemented SOLID Principles
### 1. SRP
SRP states that a class should have only one reason to change. In my code, the controllers, services, and repositories follows SRP. Each class has a single responsibility:

- Controllers handle HTTP requests.
- Services contain business logic.
- Repositories handle data access.

### 2. OCP
OCP states that software entities should be open for extension but closed for modification. My implementation use interfaces to allow easy extension without modifying existing code.

### 3. LSP
LSP states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. My current code implements LSP as the service implementations can replace their interfaces without issues.

### 4. ISP
ISP states that clients should not be forced to depend on interfaces they do not use. The interfaces are already specific and do not force clients to depend on unused methods.

### 5. DIP
DIP states that high-level modules should not depend on low-level modules but on abstractions. My code uses Spring's `@Autowired` annotation to ensure high-level modules (controllers and services) depend on abstractions (interfaces) rather than concrete implementations.

## Advantages of Applying SOLID Principles

### 1. SRP

<b>Advantage:</b> Each class has a single responsibility, making the code easier to understand, maintain, and test.  
<b>Example:</b> In the `CarController`, the class is only responsible for handling HTTP requests related to cars. The business logic is handled by the `CarService`, and data access is managed by the `CarRepository`. This separation makes the code more modular and easier to manage.

### 2. OCP

<b>Advantage:</b> Classes are open for extension but closed for modification, allowing new functionality to be added without changing source code.  
<b>Example:</b> By using interfaces for services and repositories, new implementations can be added without modifying the source code. For instance, if a new type of repository is needed, it can be implemented and injected without changing the existing `CarServiceImpl`.

### 3. LSP

<b>Advantage:</b> Subclasses can replace their base classes without affecting the correctness of the program, ensuring that the system remains consistent and reliable.  
<b>Example:</b> The `CarServiceImpl` can be replaced with any other implementation of `CarService` without affecting the `CarController`. This ensures that the controller can work with any service implementation that extends the `CarService` interface.

### 4. ISP

<b>Advantage:</b> Clients are not forced to depend on interfaces they do not use, leading to more focused and easier-to-understand interfaces.  
<b>Example:</b> By defining specific interfaces for services and repositories, each interface contains only the methods relevant to its purpose. This prevents clients from being burdened with unnecessary methods.

### 5. DIP

<b>Advantage:</b> High-level modules do not depend on low-level modules but on abstractions, making the system more flexible and easier to modify.  
<b>Example:</b> The `CarController` depends on the `CarService` interface rather than a specific implementation. This allows different implementations of `CarService` to be used without changing the controller code.

## Disadvantages of Not Applying SOLID Principles
### 1. SRP

<b>Disadvantage:</b> Classes with multiple responsibilities become complex and difficult to maintain.  
<b>Example:</b> If the `CarController` handled both HTTP requests and business logic, any change in business logic would require changes in the controller, increasing the risk of introducing bugs.

### 2. OCP
<b>Disadvantage:</b> Modifying existing code to add new functionality can introduce bugs and make the system less stable.  
<b>Example:</b> Without OCP, adding a new type of repository would require modifying the current `CarServiceImpl`, increasing the risk of breaking existing functionality.

### 3. LSP

<b>Disadvantage:</b> Subclasses that do not follow LSP can cause unexpected behavior and bugs.  
<b>Example:</b> If a subclass of `CarService` does not correctly implement the methods of the interface, it could cause the `CarController` to behave incorrectly, leading to runtime errors.

### 4. ISP

<b>Disadvantage:</b> Large interfaces with many methods can be difficult to implement and understand.  
<b>Example:</b> If the `CarService` interface contained methods unrelated to car management, implementing the interface would be more complex and error-prone.

### 5. DIP

<b>Disadvantage:</b> High-level modules that depend on low-level modules are tightly coupled, making the system rigid and difficult to modify.  
<b>Example:</b> If the `CarController` directly depended on a specific implementation of `CarService`, changing the service implementation would require changes in the controller, reducing flexibility and increasing maintenance effort.
</details>

