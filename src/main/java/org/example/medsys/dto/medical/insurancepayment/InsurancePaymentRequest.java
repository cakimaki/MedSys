package org.example.medsys.dto.medical.insurancepayment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class InsurancePaymentRequest {
    @NotBlank
    private Long patient_id;
    @NotBlank
    private Integer month;
    @NotBlank
    private Integer year;
    @NotBlank
    private boolean isPaid;
}
