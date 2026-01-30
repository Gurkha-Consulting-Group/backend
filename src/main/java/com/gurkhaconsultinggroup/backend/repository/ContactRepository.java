package com.gurkhaconsultinggroup.backend.repository;

import com.gurkhaconsultinggroup.backend.model.ContactRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactRequest,Long> {
}
