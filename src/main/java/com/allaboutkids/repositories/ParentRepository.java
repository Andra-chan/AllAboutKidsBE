package com.allaboutkids.repositories;

import com.allaboutkids.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long>{

    @Query("SELECT parent FROM Parent parent WHERE parent.lastName LIKE :lastName%")
    List<Parent> findByQuery(@Param("lastName")String lastName);

    @Query("SELECT parent FROM Parent parent WHERE parent.cnp = :cnp")
    Parent findByQueryCnp(@Param("cnp")String cnp);

}
