package com.example.hrpayroll.services;

import com.example.hrpayroll.dto.Worker;
import com.example.hrpayroll.entities.Payment;
import com.example.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {
    @Autowired
    private WorkerFeignClient workerFeignClient;

    public PaymentServices() {
    }

    public Payment getPayment(Long workerid, int days) {
        final Worker worker = this.workerFeignClient.findId(workerid).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
