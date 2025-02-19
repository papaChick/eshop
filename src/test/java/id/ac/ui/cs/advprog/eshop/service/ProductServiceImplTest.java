package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);

        Product created = productService.create(product);

        assertNotNull(created);
        assertEquals(product.getProductId(), created.getProductId());
        assertEquals(product.getProductName(), created.getProductName());
        assertEquals(product.getProductQuantity(), created.getProductQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testEdit() {
        Product changes = new Product();
        changes.setProductName("Updated Product");
        changes.setProductQuantity(20);

        when(productRepository.edit(changes, product.getProductId())).thenReturn(changes);

        Product edited = productService.edit(changes, product.getProductId());

        assertNotNull(edited);
        assertEquals(changes.getProductName(), edited.getProductName());
        assertEquals(changes.getProductQuantity(), edited.getProductQuantity());
        verify(productRepository, times(1)).edit(changes, product.getProductId());
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(product.getProductId());

        productService.delete(product.getProductId());

        verify(productRepository, times(1)).delete(product.getProductId());
    }

    @Test
    void testFindById() {
        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product found = productService.findById(product.getProductId());

        assertNotNull(found);
        assertEquals(product.getProductId(), found.getProductId());
        assertEquals(product.getProductName(), found.getProductName());
        assertEquals(product.getProductQuantity(), found.getProductQuantity());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Product secondProduct = new Product();
        secondProduct.setProductId("second-id");
        secondProduct.setProductName("Second Product");
        secondProduct.setProductQuantity(5);
        productList.add(secondProduct);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> found = productService.findAll();

        assertNotNull(found);
        assertEquals(2, found.size());
        assertEquals(product.getProductId(), found.get(0).getProductId());
        assertEquals(secondProduct.getProductId(), found.get(1).getProductId());
        verify(productRepository, times(1)).findAll();
    }
}