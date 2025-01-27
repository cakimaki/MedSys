package org.example.medsys.repository.medical;
import org.example.medsys.entity.medical.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeave,Long> {
}
