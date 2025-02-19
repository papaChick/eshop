package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        assertEquals("createProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        when(productService.create(product)).thenReturn(product);

        String viewName = productController.createProductPost(product, model);

        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testEditProductPage() {
        when(productService.findById(product.getProductId())).thenReturn(product);

        String viewName = productController.editProductPage(product.getProductId(), model);

        assertEquals("editProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
        verify(productService, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProduct() {
        Product changes = new Product();
        changes.setProductName("Updated Product");
        changes.setProductQuantity(20);

        when(productService.edit(changes, product.getProductId())).thenReturn(changes);

        String viewName = productController.editProduct(changes, product.getProductId(), model);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).edit(changes, product.getProductId());
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productService).delete(product.getProductId());

        String viewName = productController.deleteProduct(product.getProductId(), model);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).delete(product.getProductId());
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Product secondProduct = new Product();
        secondProduct.setProductId("second-id");
        secondProduct.setProductName("Second Product");
        secondProduct.setProductQuantity(5);
        productList.add(secondProduct);

        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        assertEquals("productList", viewName);
        verify(model, times(1)).addAttribute("products", productList);
        verify(productService, times(1)).findAll();
    }
}