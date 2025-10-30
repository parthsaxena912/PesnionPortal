package com.drdo.pensionPortal.config;



import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${rzp_test_RXpXGIPtQmUHF6}")
    private String keyId;

    @Value("${hqbZSx6P5QeEzFypTWfIoRZ5}")
    private String keySecret;

    @Bean
    public RazorpayClient razorpayClient() throws Exception {
        return new RazorpayClient(keyId, keySecret);
    }
}
