package com.drdo.pensionPortal.controller;



import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private RazorpayClient razorpayClient;

    // Admin creates payment order for user
    @PostMapping("/create-order")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> data) throws Exception {
        int amount = (Integer) data.get("amount");

        JSONObject options = new JSONObject();
        options.put("amount", amount); // in paise
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());
        options.put("payment_capture", 1);

        Order order = razorpayClient.Orders.create(options);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.get("id"));
        response.put("amount", order.get("amount"));
        response.put("currency", order.get("currency"));
        return response;
    }

    @PostMapping("/verify")
    public Map<String, String> verifyPayment(@RequestBody Map<String, String> data) {
        // Optional: implement signature verification
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
