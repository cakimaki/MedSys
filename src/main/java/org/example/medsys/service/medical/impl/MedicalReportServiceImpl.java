package org.example.medsys.service.medical.impl;

import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.dto.medical.reports.*;
import org.example.medsys.dto.medical.visit.VisitResponse;
import org.example.medsys.entity.medical.Patient;
import org.example.medsys.repository.medical.DiagnosisRepository;
import org.example.medsys.repository.medical.DoctorRepository;
import org.example.medsys.repository.medical.PatientRepository;
import org.example.medsys.repository.medical.VisitRepository;
import org.example.medsys.service.medical.MedicalReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final VisitRepository visitRepository;

    public MedicalReportServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, DiagnosisRepository diagnosisRepository, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.visitRepository = visitRepository;
    }

    //    a. Списък с пациенти, с дадена диагноза.
    public List<PatientResponse> patientsByDiagnosis(String diagnosis){
        List<Patient> patients = patientRepository.findDistinctByVisits_Diagnoses_Name(diagnosis);

        return patients.stream()
                .map(this::mapToPatientResponse)
                .toList();
    }

    //    b. Информация за това, коя е диагнозата/диагнозите, които са диагностицирани
    //    най-често.
    public List<DiagnosisCountResponse> mostFrequentDiagnoses() {
        return diagnosisRepository.countAllUsedDiagnoses();
    }

    //    c. Списък с пациенти, които имат даден личен лекар.
    public List<PatientResponse> patientsByGpId(Long id) {
        List<Patient> patients = patientRepository.findByGp_IdOrderByNameAsc(id);

        return patients.stream()
                .map(this::mapToPatientResponse)
                .toList();
    }

    //    d. Брой на пациентите, записани при всеки от личните лекари в системата.
    public List<CountPatientsPerGpResponse> patientsPerGp() {
        return patientRepository.countPatientsPerGp();
    }

    //    e. Брой посещения при всеки от лекарите.
    public List<CountVisitsPerGpResponse> visitsPerGp() {
        return visitRepository.countVisitsPerDoctor();
    }


    //todo

    //    g. Списък на прегледите при всички лекари в даден период.
    public List<VisitResponse> allVisitsForPeriod(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    //     h. Списък на прегледите при определен лекар за даден период.
    public List<VisitResponse> visitsByDoctorForPeriod(Long docId, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    //    i. Информация за месеца в годината, в който са издадени най-много болнични.
    public MonthStatResponse monthWithMostSickLeavesThisYear() {
        return null;
    }

    //    j. Информация за лекарят/лекарите, които са издали най-много болнични.
    public List<DoctorMostSickLeavesResponse> topSickLeaveDoctors(){
        return null;
    }



    private PatientResponse mapToPatientResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setEgn(patient.getUser().getEgn());
        response.setGpId(patient.getGp().getId());
        response.setGpName(patient.getGp().getName()); // Map GP's name
        response.setInsured(patient.isInsured());
        return response;
    }
}
