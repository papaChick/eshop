package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product changes = new Product();
        changes.setProductName("Sampo Cap Usep");
        changes.setProductQuantity(50);

        productRepository.edit(changes, product.getProductId());
        Product editedProduct = productRepository.findById(product.getProductId());
        assertEquals(changes.getProductName(), editedProduct.getProductName());
        assertEquals(changes.getProductQuantity(), editedProduct.getProductQuantity());
        assertEquals(product.getProductId(), editedProduct.getProductId());
    }

    @Test
    void testEditIfEmpty() {
        Product changes = new Product();
        changes.setProductName("Sampo Cap Usep");
        changes.setProductQuantity(50);

        productRepository.edit(changes, "eb558e9f-1c39-460e-8860-71af6af63bd6");
        Product editedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(editedProduct);
    }

    @Test
    void testDelete(){
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId(id);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
        assertNull(productRepository.findById(id));
    }

    @Test
    void testDeleteIfEmpty() {
        String id = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        productRepository.delete(id);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
        assertNull(productRepository.findById(id));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product searchProduct = productRepository.findById(product.getProductId());
        assertNotNull(searchProduct);
        assertEquals(product.getProductId(), searchProduct.getProductId());
        assertEquals(product.getProductName(), searchProduct.getProductName());
        assertEquals(product.getProductQuantity(), searchProduct.getProductQuantity());

        searchProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd8");
        assertNull(searchProduct);
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bff-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }
}