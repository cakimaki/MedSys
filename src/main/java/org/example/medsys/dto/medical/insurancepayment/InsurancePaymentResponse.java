package org.example.medsys.dto.medical.insurancepayment;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class InsurancePaymentResponse {
    private Long id;
    private Integer month;
    private Integer year;
    private boolean isPaid;

}
