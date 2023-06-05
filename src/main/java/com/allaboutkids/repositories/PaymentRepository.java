package com.allaboutkids.repositories;

import com.allaboutkids.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT payment FROM Payment payment WHERE payment.description LIKE :description%")
    List<Payment> findByQuery(@Param("description")String description);

}
