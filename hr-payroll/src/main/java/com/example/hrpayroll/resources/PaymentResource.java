package com.example.hrpayroll.resources;

import com.example.hrpayroll.entities.Payment;
import com.example.hrpayroll.services.PaymentServices;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    private PaymentServices paymentServices;

    @GetMapping("/{workerId}/days/{days}")
    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
       // throw new RuntimeException("Erro sou senior");
        final Payment payment = this.paymentServices.getPayment(workerId, days);
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentAlternative(Long workerId, Integer days) {
        final Payment payment = new Payment("Brann", 400.0, days);
        return ResponseEntity.ok(payment);
    }

}
