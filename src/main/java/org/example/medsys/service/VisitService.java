package org.example.medsys.service;


import org.example.medsys.dto.VisitDto;

import java.util.List;

public interface VisitService {
	List<VisitDto> getVisitsByPatientEmail(String email);
}
