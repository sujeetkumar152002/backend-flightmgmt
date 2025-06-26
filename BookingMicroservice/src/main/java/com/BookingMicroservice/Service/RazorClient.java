package com.BookingMicroservice.Service;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "PaymentGateway", path = "/api/payments")
public interface RazorClient {

    @PostMapping("/create")
    ResponseEntity<Map<String, Object>> createPayment(@RequestBody Map<String, Object> requestBody);

    @PostMapping("/verify")
    ResponseEntity<Map<String, Object>> verifyPayment(@RequestBody Map<String, String> requestBody);
}
