package org.example.medsys.service.medical;


import org.example.medsys.dto.VisitDto;

import java.util.List;

public interface VisitService {
	List<VisitDto> getVisitsByPatientEmail(String email);
}
