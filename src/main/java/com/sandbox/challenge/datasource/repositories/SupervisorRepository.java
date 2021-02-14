package com.sandbox.challenge.datasource.repositories;

import com.sandbox.challenge.datasource.entitites.SupervisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Long> {
}
