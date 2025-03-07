package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Payment> paymentsData = new ArrayList<>();

    public Payment save(Payment payment) {
        int i = 0;
        for (Payment savedPayment : paymentsData) {
            if (savedPayment.getId().equals(payment.getId())) {
                paymentsData.remove(i);
                paymentsData.add(i, payment);
                return payment;
            }
            i += 1;
        }

        paymentsData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        return paymentsData.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentsData);
    }
}