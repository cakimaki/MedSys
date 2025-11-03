package org.example.medsys.dto.medical.insurancepayment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class InsurancePaymentRequest {
    @NotNull
    private Long patientId;

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

    private boolean isPaid;
}
