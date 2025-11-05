package org.example.medsys.controller.admin;

import org.example.medsys.dto.medical.patient.PatientResponse;
import org.example.medsys.dto.medical.reports.*;
import org.example.medsys.dto.medical.visit.VisitResponse;
import org.example.medsys.service.medical.MedicalReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/reports")
public class AdminReportsController {

    private final MedicalReportService reportService;

    public AdminReportsController(MedicalReportService reports) {
        this.reportService = reports;
    }

    // a) Patients with a given diagnosis
    @GetMapping("/patients/by-diagnosis")
    public ResponseEntity<List<PatientResponse>> patientsByDiagnosis(
            @RequestParam String diagnosis) {
        return ResponseEntity.ok(reportService.patientsByDiagnosis(diagnosis));
    }

    // b) Most frequent diagnoses
    @GetMapping("/diagnoses/top")
    public ResponseEntity<List<DiagnosisCountResponse>> topDiagnoses() {
        return ResponseEntity.ok(reportService.mostFrequentDiagnoses());
    }

    // c) Patients registered with a given GP
    @GetMapping("/patients/by-gp")
    public ResponseEntity<List<PatientResponse>> patientsByGp(
            @RequestParam("gpId") Long gpId) {
        return ResponseEntity.ok(reportService.patientsByGpId(gpId));
    }

    // d) Number of patients per GP
    @GetMapping("/gps/patient-count")
    public ResponseEntity<List<CountPatientsPerGpResponse>> patientsPerGp() {
        return ResponseEntity.ok(reportService.patientsPerGp());
    }

    // e) Number of visits per doctor
    @GetMapping("/visits/count-by-doctor")
    public ResponseEntity<List<CountVisitsPerGpResponse>> visitsPerDoctor() {
        return ResponseEntity.ok(reportService.visitsPerGp());
    }


    //todo

    // g) All visits in a period
    @GetMapping("/visits/in-period")
    public ResponseEntity<List<VisitResponse>> allVisitsForPeriod(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(reportService.allVisitsForPeriod(from, to));
    }

    // h) Visits for a specific doctor in a period
    @GetMapping("/visits/by-doctor")
    public ResponseEntity<List<VisitResponse>> visitsByDoctorForPeriod(
            @RequestParam Long doctorId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(reportService.visitsByDoctorForPeriod(doctorId, from, to));
    }

    // i) Month in the current year with the most sick leaves
    @GetMapping("/sick-leaves/top-month-this-year")
    public ResponseEntity<MonthStatResponse> monthWithMostSickLeavesThisYear() {
        return ResponseEntity.ok(reportService.monthWithMostSickLeavesThisYear());
    }

    // j) Doctors who issued the most sick leaves (optional period, top N)
    @GetMapping("/sick-leaves/top-doctors")
    public ResponseEntity<List<DoctorMostSickLeavesResponse>> topSickLeaveDoctors(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(reportService.topSickLeaveDoctors());
    }
}

