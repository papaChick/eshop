package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    public Product create(Product product);
    public Product edit(Product changes, UUID id);
    public void delete(UUID id);
    public Product findById(UUID id);
    public List<Product> findAll();
}