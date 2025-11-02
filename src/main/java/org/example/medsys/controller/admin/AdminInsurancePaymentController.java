package org.example.medsys.controller.admin;

import jakarta.validation.Valid;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentRequest;
import org.example.medsys.dto.medical.insurancepayment.InsurancePaymentResponse;
import org.example.medsys.service.medical.InsurancePaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/insurance-payment")
public class AdminInsurancePaymentController {

    private final InsurancePaymentService insuranceService;

    public AdminInsurancePaymentController(InsurancePaymentService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping()
    public ResponseEntity<InsurancePaymentResponse> createInsurancePayment(@Valid @RequestBody InsurancePaymentRequest request){
        InsurancePaymentResponse response = insuranceService.createInsurancePayment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{paymentId}/paid")
    public ResponseEntity<InsurancePaymentResponse> changeToPaid(@PathVariable Long paymentId){
        InsurancePaymentResponse response = insuranceService.changeToPaid(paymentId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{paymentId}/not-paid")
    public ResponseEntity<InsurancePaymentResponse> changeToNotPaid(@PathVariable Long paymentId){
        InsurancePaymentResponse response = insuranceService.changeToNotPaid(paymentId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{patientId}")
    public ResponseEntity<List<InsurancePaymentResponse>> getPaymentsInYear(
            @PathVariable Long patientId,
            @RequestParam(required = false) Integer year){

        List<InsurancePaymentResponse> payments;

        if(year == null){
            payments = insuranceService.getInsurancePaymentsForPatientId(patientId);
        }else{
            payments = insuranceService.getInsurancePaymentsForPatientIdForYear(patientId, year);
        }

        return ResponseEntity.ok(payments);
    }


}
