package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PaymentTest {

    List<Product> products;
    Order order;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        this.products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat", OrderStatus.WAITING_PAYMENT.getValue());
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));

        assertEquals("1424f2b7-2af2-4b6e-a43b-a25cb252e958", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(Map.of("voucherCode", "ESHOP1234ABC5678"), payment.getPaymentData());
    }

    @Test
    void testCreatePaymentNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", null,
                    "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));
        });
    }

    @Test
    void testSetOrder() {
        Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));

        Order newOrder = new Order("4f67ec89-2614-4f63-8cb3-771e180093b0", this.products, 1708560000L, "John doe", OrderStatus.WAITING_PAYMENT.getValue());

        payment.setOrder(newOrder);
        assertEquals(newOrder, payment.getOrder());
    }

    @Test
    void testSetMethod() {
        Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));

        payment.setMethod("COD");
        assertEquals("COD", payment.getMethod());
    }

    @Test
    void testSetStatus() {
        Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));

        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetPaymentData() {
        Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));

        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("voucherCode", "ESHOP1234DEF5678");
        payment.setPaymentData(newPaymentData);
        assertEquals(newPaymentData, payment.getPaymentData());
    }

    @Test
    void testInvalidVoucherCodes() {
        Payment payment = new Payment("1424f2b7-2af2-4b6e-a43b-a25cb252e958", order,
                "VOUCHER", "SUCCESS", new HashMap<>(Map.of("voucherCode", "ESHOP1234ABC5678")));

        Map<String, String> invalidVouchers = new HashMap<>();
        invalidVouchers.put("INVALID CODE", "INVALID12345");
        invalidVouchers.put("EMPTY CODE", "");
        invalidVouchers.put("NULL CODE", null);  // This could cause a NullPointerException
        invalidVouchers.put("PREFIX NOT ESHOP", "SHOP1234567890AB");
        invalidVouchers.put("SHORT CODE", "ESHOP12345");
        invalidVouchers.put("LONG CODE", "ESHOP12345678901234");
        invalidVouchers.put("LESS THAN 8 NUMBERS", "ESHOP123ABCDEFGHI");
        invalidVouchers.put("MORE THAN 8 NUMBERS", "ESHOP1234567890AB");
        invalidVouchers.put("LOWERCASE PREFIX", "eshop12345678ABCD");

        for (Map.Entry<String, String> entry : invalidVouchers.entrySet()) {
            Map<String, String> invalidVoucherData = new HashMap<>();
            invalidVoucherData.put("voucherCode", entry.getValue());

            assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(invalidVoucherData),
                    "Expected exception for case: " + entry.getKey());
        }
    }
}