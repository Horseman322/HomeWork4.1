package com.example.homework36.repository;



import com.example.homework36.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findAllByAge(int age);
    Collection<Student> findAllByAgeBetween(int minAge, int maxAge);

}
