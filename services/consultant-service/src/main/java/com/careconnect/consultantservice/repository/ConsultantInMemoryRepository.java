package com.careconnect.consultantservice.repository;

import com.careconnect.consultantservice.entity.Consultant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ConsultantInMemoryRepository {

    private final ConcurrentHashMap<Long, Consultant> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public Consultant save(Consultant consultant) {
        if (consultant.getId() == null) {
            consultant.setId(idSequence.incrementAndGet());
        }
        store.put(consultant.getId(), consultant);
        return consultant;
    }

    public Optional<Consultant> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Consultant> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Long id) {
        store.remove(id);
    }

    public Optional<Consultant> findByEmail(String email) {
        return store.values().stream()
                .filter(c -> email != null && email.equalsIgnoreCase(c.getEmail()))
                .findFirst();
    }

    public Optional<Consultant> findByPhone(String phone) {
        return store.values().stream()
                .filter(c -> phone != null && phone.equalsIgnoreCase(c.getPhone()))
                .findFirst();
    }

    public List<Consultant> findBySpecializationIgnoreCase(String specialization) {
        if (specialization == null) return findAll();
        String s = specialization.toLowerCase();
        return store.values().stream()
                .filter(c -> c.getSpecialization() != null &&
                        c.getSpecialization().toLowerCase().contains(s))
                .toList();
    }

    public List<Consultant> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName) {

        String f = firstName == null ? "" : firstName.toLowerCase();
        String l = lastName == null ? "" : lastName.toLowerCase();

        return store.values().stream()
                .filter(c ->
                        (c.getFirstName() != null && c.getFirstName().toLowerCase().contains(f)) ||
                                (c.getLastName() != null && c.getLastName().toLowerCase().contains(l))
                )
                .toList();
    }
}
