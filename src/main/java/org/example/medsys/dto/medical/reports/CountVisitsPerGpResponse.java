package org.example.medsys.dto.medical.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountVisitsPerGpResponse {
    private Long count;
    private Long gpId;
    private String gpName;

}
