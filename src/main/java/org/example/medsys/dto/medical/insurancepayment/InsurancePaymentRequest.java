package org.example.medsys.dto.medical.insurancepayment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class InsurancePaymentRequest {
    @NotNull
    private Long patientId;

    @NotNull
    @Min(1) @Max(12)
    private Integer month;

    @NotNull
    @Min(1900) @Max(2100)
    private Integer year;

    private boolean isPaid;
}
