package org.example.medsys.service.medical;

import jakarta.transaction.Transactional;
import org.example.medsys.dto.medical.doctor.DoctorRequest;
import org.example.medsys.dto.medical.doctor.DoctorResponse;

public interface DoctorService {
	@Transactional
	DoctorResponse createDoctor(DoctorRequest request);
}
