package com.sports.restservice.repository;

import com.sports.restservice.model.player_stats;


import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

// Making Repository class which will comunicate with database table using Spring Jpa Dependency

public interface playerStatsRepo extends JpaRepository<player_stats, Long> {
    // This is for finding records with specific filters
    List<player_stats> findAll(Specification<player_stats> spec);

}