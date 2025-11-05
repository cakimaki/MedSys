package org.example.medsys.repository.medical;

import org.example.medsys.dto.medical.reports.CountVisitsPerGpResponse;
import org.example.medsys.entity.medical.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPatientId(Long patientId);

    List<Visit> findAllByPatientId(Long patientId);

    List<Visit> findAllByDoctorId(Long doctorId);

    List<Visit> findAllByPatient_User_Egn(String egn);

    @Query("""
                select new org.example.medsys.dto.medical.reports.CountVisitsPerGpResponse(
                    count(v), d.id, d.name
                )
                from Visit v
                join v.doctor d
                group by d.id, d.name
                order by count(v) desc, d.name
            """)
    List<CountVisitsPerGpResponse> countVisitsPerDoctor();

    @Query("""
        select distinct v
        from Visit v
        join fetch v.patient p
        join fetch v.doctor d
        left join fetch v.diagnoses di
        where v.dateTime >= :from and v.dateTime < :to
        order by v.dateTime desc
    """)
    List<Visit> findVisitsInPeriod(@Param("from") LocalDateTime from,
                                   @Param("to") LocalDateTime to);

    // h) Списък на прегледите при определен лекар в период
    @Query("""
        select distinct v
        from Visit v
        join fetch v.patient p
        join fetch v.doctor d
        left join fetch v.diagnoses di
        where d.id = :doctorId
          and v.dateTime >= :from and v.dateTime < :to
        order by v.dateTime desc
    """)
    List<Visit> findVisitsByDoctorInPeriod(@Param("doctorId") Long doctorId,
                                           @Param("from") LocalDateTime from,
                                           @Param("to") LocalDateTime to);

}