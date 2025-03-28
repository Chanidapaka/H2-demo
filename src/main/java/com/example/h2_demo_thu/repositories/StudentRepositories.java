package com.example.h2_demo_thu.repositories;

import com.example.h2_demo_thu.entities.Student;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositories extends JpaRepository<Student, Long> {
}
