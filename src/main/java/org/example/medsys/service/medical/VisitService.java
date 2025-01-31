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
	List<VisitResponse> getVisitsByPatientId(Long patientId);
	
	List<VisitResponse> getVisitsByPatientEgn(String patientEgn);
	
	List<VisitResponse> getVisitsByDoctorId(Long doctorId);
	@Transactional
	VisitResponse updateVisit(Long id, VisitRequest request);
	
	@Transactional
	VisitResponse updateVisitForDoctor(Long id, VisitRequest request, String doctorEgn);
	
	@Transactional
	void deleteVisit(Long id);
	
	@Transactional
	void deleteVisitForDoctor(Long id, String doctorEgn);
}
