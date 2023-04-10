package com.example.ssarijileo.api.singing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ssarijileo.api.singing.entity.Singing;

@Repository
public interface SingingJpaRepository extends JpaRepository<Singing, Long> {
}
