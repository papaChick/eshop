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

Most of the clean code principles & secure coding practices are handled by Spring Boot. Spring Boot is an MVC (Model-View-Controller) framework, which generally have their data structure's behaviour encapsulated. It is done to prevent the data being exposed directly. An example of this is the accessors (Setter & Getter) of the Product object. Furthermore, Spring Boot encodes data sent to users properly. I tried populating the input with common XSS payloads, and Spring Boot successfully prevents it from running. Spring Boot encodes the data into HTML Entity, therefore makes it clearer to parse HTML tags and data.

The web project is a small one, it only have Create, Update, and Delete. I don't find the need to comment the source code, the functions are quite self-explanatory. It should be noted that Spring Boot does not enable input validation by default. 
