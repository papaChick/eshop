package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
public class Product {
    private final UUID productId = UUID.randomUUID();
    private String productName;
    private int productQuantity;
}