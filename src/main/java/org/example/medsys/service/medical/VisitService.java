package org.example.medsys.service.medical;


import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.visit.VisitRequest;
import org.example.medsys.dto.medical.visit.VisitResponse;

import java.util.List;

public interface VisitService {
	//admin
	@Transactional
	VisitResponse createVisit(VisitRequest request);
	
	//doctor
	@Transactional
	VisitResponse createVisitForDoctor(VisitRequest request, String doctorEgn);
	
	//common
	VisitResponse getVisitById(Long id);
	List<VisitResponse> getVisitsByPatient(Long patientId);
	List<VisitResponse> getVisitsByDoctor(Long doctorId);
	@Transactional
	VisitResponse updateVisit(Long id, VisitRequest request);
	@Transactional
	void deleteVisit(Long id);
}
