package com.careconnect.consultantservice.repository;

import com.careconnect.consultantservice.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    Optional<Consultant> findByEmail(String email);
    Optional<Consultant> findByPhone(String phone);
    List<Consultant> findBySpecializationIgnoreCase(String specialization);
    List<Consultant> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
