package com.student.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // This looks for students whose name contains the search letters
    @Query("SELECT s FROM Student s WHERE lower(s.name) LIKE lower(concat('%', :name, '%'))")
    List<Student> findByNameContaining(String name);
}