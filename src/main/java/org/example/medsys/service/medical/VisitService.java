package org.example.medsys.service.medical;


import org.example.medsys.dto.medical.visit.VisitDto;

import java.util.List;

public interface VisitService {
	List<VisitDto> getVisitsByPatientEmail(String email);
}
