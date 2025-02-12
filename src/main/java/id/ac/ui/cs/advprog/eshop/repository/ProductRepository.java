package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product edit(Product changes, UUID id) {
        Product product = findById(id);

        // Set name & quantity
        if (product != null) {
            product.setProductName(changes.getProductName());
            product.setProductQuantity(changes.getProductQuantity());
        }

        return product;
    }

    public void delete(UUID id) {
        Product product = findById(id);
        if (product != null) {
            productData.remove(product);
        }
    }

    public Product findById(UUID id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}