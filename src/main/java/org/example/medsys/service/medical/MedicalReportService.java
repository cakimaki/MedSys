package org.example.medsys.service.medical;

import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.dto.medical.reports.*;
import org.example.medsys.dto.medical.visit.VisitResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalReportService {
    //    a. Списък с пациенти, с дадена диагноза.
    public List<PatientResponse> patientsByDiagnosis(String diagnosis);

    //    b. Информация за това, коя е диагнозата/диагнозите, които са диагностицирани
    //    най-често.
    public List<DiagnosisCountResponse> mostFrequentDiagnoses();

    //    c. Списък с пациенти, които имат даден личен лекар.
    public List<PatientResponse> patientsByGpId(Long id);

    //    d. Брой на пациентите, записани при всеки от личните лекари в системата.
    public List<CountPatientsPerGpResponse> patientsPerGp();

    //    e. Брой посещения при всеки от лекарите.
    public List<CountVisitsPerGpResponse> visitsPerGp();

    //    g. Списък на прегледите при всички лекари в даден период.
    public List<VisitResponse> allVisitsForPeriod(LocalDateTime from, LocalDateTime to);

    //     h. Списък на прегледите при определен лекар за даден период.
    public List<VisitResponse> visitsByDoctorForPeriod(Long docId, LocalDateTime from, LocalDateTime to);

    //    i. Информация за месеца в годината, в който са издадени най-много болнични.
    public MonthStatResponse monthWithMostSickLeavesThisYear();

    //    j. Информация за лекарят/лекарите, които са издали най-много болнични.
    public List<DoctorMostSickLeavesResponse> topSickLeaveDoctors();

}
