package com.allaboutkids.repositories;

import com.allaboutkids.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT student FROM Student student WHERE student.lastName LIKE :lastName%")
    List<Student> findByQuery(@Param("lastName")String lastName);

}
