package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        Order order2 = new Order("cf6071f3-4c39-4e7b-9c2c-91572c98b506",
                products, 1708570000L, "Bambang Pamungkas");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC");
        Payment payment = new Payment("d550c61b-f80f-47ac-8dad-8d413aaaee10", order, PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Depok");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("dfc0cff97-6529-4d7d-8e94-84f26457a93c", order2, PaymentMethod.COD.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData2);

        payments.add(payment);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jakarta");
        paymentData.put("deliveryFee", "20000");

        Payment newPayment = new Payment(payment.getId(), payment.getOrder(),
                PaymentMethod.COD.getValue(), payment.getStatus(), paymentData);
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.getFirst().getId());
        assertEquals(newPayment.getId(), result.getId());
        assertEquals(newPayment.getId(), findResult.getId());
        assertEquals(newPayment.getMethod(), findResult.getMethod());
        assertEquals(newPayment.getOrder(), findResult.getOrder());
        assertEquals(newPayment.getStatus(), findResult.getStatus());
        assertEquals(newPayment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("2871e88e-917d-4920-b943-abce4e7801ef");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> checkSize = paymentRepository.findAll();

        assertEquals(2, checkSize.size());
    }
}