package org.example.medsys.dto.medical.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountPatientsPerGpResponse {
    private Long id;
    private String gpName;
    private Long patientCount;
}
