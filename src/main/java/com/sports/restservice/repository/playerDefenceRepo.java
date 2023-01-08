package com.sports.restservice.repository;

import com.sports.restservice.model.player_defence;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

// Making Repository class which will comunicate with database table using Spring Jpa Dependency
public interface playerDefenceRepo extends JpaRepository<player_defence, Long> {
    // This is for finding records with specific filters
    List<player_defence> findAll(Specification<player_defence> spec);
}