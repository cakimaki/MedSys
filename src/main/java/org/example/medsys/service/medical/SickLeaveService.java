package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.SickLeaveRequest;
import org.example.medsys.dto.medical.SickLeaveResponse;
import org.example.medsys.dto.medical.sickleave.SickLeaveUpdateRequest;

public interface SickLeaveService {
	@Transactional
	SickLeaveResponse createSickLeave(SickLeaveRequest request);
	
	SickLeaveResponse updateSickLeave(Long id, SickLeaveUpdateRequest request);
	
	SickLeaveResponse getSickLeaveByVisitId(Long visitId);
	
	@Transactional
	void deleteSickLeave(Long id);
}
