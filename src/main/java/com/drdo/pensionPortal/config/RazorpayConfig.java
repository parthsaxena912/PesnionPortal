package com.drdo.pensionPortal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.razorpay.RazorpayClient;

@Configuration
public class RazorpayConfig {

    private final RazorpayClient client;

    public RazorpayConfig(
            @Value("${razorpay.key.id}") String keyId,
            @Value("${razorpay.key.secret}") String keySecret) throws Exception {

        this.client = new RazorpayClient(keyId, keySecret);
        System.out.println("✅ Razorpay client initialized successfully with key: " + keyId);
    }

    public RazorpayClient getClient() {
        return client;
    }
}
