package org.example.medsys.service.medical;


import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.VisitRequest;
import org.example.medsys.dto.medical.VisitResponse;
import org.example.medsys.dto.medical.visit.VisitDto;

import java.util.List;

public interface VisitService {
	@Transactional
	VisitResponse createVisit(VisitRequest request);
	
	List<VisitResponse> getAllVisits();
	
	VisitResponse getVisitById(Long id);
	
	List<VisitResponse> getVisitsByPatient(Long patientId);
	
	@Transactional
	VisitResponse updateVisit(Long id, VisitRequest request);
	
	@Transactional
	void deleteVisit(Long id);
}
