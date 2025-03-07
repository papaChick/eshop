package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;

    @Setter
    Order order;

    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, Order order, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.status = status;

        if (order == null) {
            throw new IllegalArgumentException("order is null");
        } else {
            this.order = order;
        }

        if (paymentData == null) {
            throw new IllegalArgumentException("paymentData is not valid");
        } else if (!validatePaymentData(paymentData)) {
            throw new IllegalArgumentException("paymentData is not valid");
        } else {
            this.paymentData = paymentData;
        }
    }

    private boolean validatePaymentData(Map<String, String> paymentData) {
        String[] methodList = {"VOUCHER", "COD"};
        if (this.method.equals(methodList[0])) {
            return validateVoucherPayment(paymentData.get("voucherCode"));
        } else if (this.method.equals(methodList[1])) {
            return validateCODPayment(paymentData);
        } else {
            return false;
        }
    }

    private boolean validateVoucherPayment(String voucherCode) {
        if (voucherCode == null) {
            return false;
        }
        else if (voucherCode.length() != 16) {
            return false;
        }
        else if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        return voucherCode.chars().filter(Character::isDigit).count() == 8;
    }

    private boolean validateCODPayment(Map<String, String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address == null || deliveryFee == null) {
            return false;
        }

        if (address.length() == 0 || deliveryFee.length() == 0) {
            return false;
        }

        return true;
    }

    public void setStatus(String status) {
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.asList(statusList).contains(status)) {
            this.status = status;
        }
    }

    public void setMethod(String method) {
        String[] methodList = {"VOUCHER", "COD"};
        if (Arrays.asList(methodList).contains(method)) {
            this.method = method;
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (validatePaymentData(paymentData)) {
            this.paymentData = paymentData;
        } else {
            throw new IllegalArgumentException("paymentData is not valid");
        }
    }
}