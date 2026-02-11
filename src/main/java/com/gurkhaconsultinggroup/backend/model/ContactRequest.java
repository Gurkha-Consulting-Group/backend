package com.gurkhaconsultinggroup.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import java.time.LocalDateTime;

@Entity
@Table(name="contact_request")
@Data
public class ContactRequest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 120)
        private String name;

        @Column(nullable = false, length = 180)
        private String email;

        @Column(nullable = false, length = 80)
        private String interest;

        @Column(nullable = false, length = 4000)
        private String message;

        @Column(nullable = false)
        private LocalDateTime createdTs = LocalDateTime.now();

        @Column(nullable = false)
        private LocalDateTime updatedTs = LocalDateTime.now();

        @CreatedBy
        @Column(length = 120, updatable = false)
        private String createdBy = "SYSTEM";

        @LastModifiedBy
        @Column(length = 120)
        private String updatedBy = "SYSTEM";
}
