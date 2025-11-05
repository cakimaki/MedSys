package org.example.medsys.dto.medical.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiagnosisCountResponse {
    private Long diagnosisId;
    private String diagnosisName;
    private Long count;
}
