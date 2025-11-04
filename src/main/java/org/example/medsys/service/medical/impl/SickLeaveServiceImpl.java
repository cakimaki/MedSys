package org.example.medsys.service.medical.impl;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.sickleave.SickLeaveRequest;
import org.example.medsys.dto.medical.sickleave.SickLeaveResponse;
import org.example.medsys.dto.medical.sickleave.SickLeaveUpdateRequest;
import org.example.medsys.entity.medical.SickLeave;
import org.example.medsys.entity.medical.Visit;
import org.example.medsys.repository.medical.SickLeaveRepository;
import org.example.medsys.repository.medical.VisitRepository;
import org.example.medsys.service.medical.SickLeaveService;
import org.springframework.stereotype.Service;

@Service
public class SickLeaveServiceImpl implements SickLeaveService {
	
	private final SickLeaveRepository sickLeaveRepository;
	private final VisitRepository visitRepository;
	
	public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository, VisitRepository visitRepository) {
		this.sickLeaveRepository = sickLeaveRepository;
		this.visitRepository = visitRepository;
	}
	
	@Transactional
	@Override
	public SickLeaveResponse createSickLeave(SickLeaveRequest request) {
		// Fetch Visit
		Visit visit = visitRepository.findById(request.getVisitId())
				.orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + request.getVisitId()));

        if(visit.getSickLeave() != null){
            throw new IllegalArgumentException("Sickleave for this visit is already existing.");
        }
		// Create SickLeave
		SickLeave sickLeave = new SickLeave();
		sickLeave.setVisit(visit);
		sickLeave.setStartDate(request.getStartDate());
		sickLeave.setDurationDays(request.getDurationDays());
		
		SickLeave savedSickLeave = sickLeaveRepository.save(sickLeave);
		
		// Map to response
		return mapToSickLeaveResponse(savedSickLeave);
	}
	
	@Override
	public SickLeaveResponse updateSickLeave(Long id, SickLeaveUpdateRequest request) {
		SickLeave sickLeave = sickLeaveRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("SickLeave not found with id: " + id));
		
		sickLeave.setStartDate(request.getStartDate());
		sickLeave.setDurationDays(request.getDurationDays());
		
		SickLeave updatedSickLeave = sickLeaveRepository.save(sickLeave);
		return mapToSickLeaveResponse(updatedSickLeave);
	}
	
	@Override
	public SickLeaveResponse getSickLeaveByVisitId(Long visitId) {
		SickLeave sickLeave = sickLeaveRepository.findByVisitId(visitId)
				.orElseThrow(() -> new IllegalArgumentException("SickLeave not found for visit id: " + visitId));
		return mapToSickLeaveResponse(sickLeave);
	}
	
	@Transactional
	@Override
	public void deleteSickLeave(Long id) {
		SickLeave sickLeave = sickLeaveRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("SickLeave not found with id: " + id));

        Visit visit = sickLeave.getVisit();
        if(visit != null){
            visit.setSickLeave(null);
            sickLeave.setVisit(null);
        }
        
		sickLeaveRepository.delete(sickLeave);
	}
	
	private SickLeaveResponse mapToSickLeaveResponse(SickLeave sickLeave) {
		SickLeaveResponse response = new SickLeaveResponse();
		response.setId(sickLeave.getId());
		response.setVisitId(sickLeave.getVisit().getId());
		response.setStartDate(sickLeave.getStartDate());
		response.setDurationDays(sickLeave.getDurationDays());
		return response;
	}
}