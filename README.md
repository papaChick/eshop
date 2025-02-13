# Reflection 1
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

The functions in the source code are broken down to it's smallest form. It's written to perform a single task and be small in size. For example:
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

# Reflection 2
### Part 1
Writing unit tests ensures my code is valid and functions as intended. I've realized that unit and functional testing are essential in software development. They speed up testing, making the process more efficient. Without automation, testing would be slow and time-consuming.

Determining the number of unit tests required for a class depends on several factors, including the complexity of the class, and the number of methods, and the potential edge cases that need to be covered. A rule-of-thumb is to write unit tests for each methods and additional tests to cover the potential edge cases and error handling.

To ensure that our unit tests is enough to verify the program, it's best to utilize code coverage tools. Code coverage, as its name suggests, measures how much of your code is executed while running tests. 100% code coverage doesn't imply that your code is bug-free and invulnerable, it simply suggests that each line of code within your code is executed. Therefore, code coverage cannot be used as proof that the code is flawless, it simply reflects the extent of test execution.

### Part 2
The new functional test suite would negatively impact the code cleanliness. Duplicating setup and instance variables from `CreateProductFunctionalTest.java` leads to redundant code, increasing the code size and introduces higher risk of error for future maintenance.
To improve this, a better approach is to create a parent class that contains the shared setup logic and use inheritance to avoid duplication. Additionally, minimizing instance variables helps reduce confusion and improves readability.

