package com.leanPlateform.repository;

import com.leanPlateform.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByClientId(Long clientId);

    List<Session> findByConsultantIdAndDateTimeAfter(Long consultantId, LocalDateTime dateTime);
}
