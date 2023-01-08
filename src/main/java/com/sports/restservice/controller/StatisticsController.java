package com.sports.restservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.restservice.repository.playerStatsRepo;
import com.sports.restservice.service.playerStatsFilter;
import com.sports.restservice.utility.SearchCriteria;
import com.sports.restservice.model.player_stats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Defining Rest Controller which is used for request over the server and through browser
@RestController
// Mapping the request which comes to server with StatisticsController
@RequestMapping("/fifa/api/v1/statistics")
public class StatisticsController {
    // To allow this class to write logs
    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    @Autowired
    private playerStatsRepo playerStatsRepo;
    @GetMapping(value = "/get-all-details")
    public List<player_stats> getAllplayersStats(){
        logger.info("Received  a request to get all player statistics data");
        List<player_stats> StatsData = playerStatsRepo.findAll();
        logger.info("Returning {} Defense records", StatsData.size());
        return StatsData;

    }
    
    @GetMapping(value = "/get-by-id/{id}")
    public player_stats getPlayerStatsById(@PathVariable long id) {
        logger.info("Received a request to get a player statistics by id={}", id);

        player_stats PlayerStats = playerStatsRepo.findById(id).orElse(null);
        logger.info("Returning a statistics of player with id = {}", id);

        return  PlayerStats;
    }

    
    @GetMapping("/apply-filter")
    public List<player_stats> applyFilter(@RequestParam(required = false) Integer gamesPlayed,
            @RequestParam(required = false) Integer minutesPlayed,
            @RequestParam(required = false) Integer goalsScored,
            @RequestParam(required = false) Integer yearOfBirth) {


    

        Specification<player_stats> specification = Specification.where(null);
        String filters = "";
        if (gamesPlayed != null) {
            filters += "games" + " = " + gamesPlayed;
            
            specification = specification.and(new playerStatsFilter(new SearchCriteria("games", ":", gamesPlayed)));
        }
        if (minutesPlayed != null) {
            filters += ", minutes" + " = " + minutesPlayed;
            specification = specification.and(new playerStatsFilter(new SearchCriteria("minutes", ":", minutesPlayed)));
        }
        if (goalsScored != null) {
            filters += ", goals" + " = " + goalsScored;
            specification = specification.and(new playerStatsFilter(new SearchCriteria("goals", ":", goalsScored)));
        }
        if (yearOfBirth != null) {
            filters += ", birth_year" + " = " + yearOfBirth;
            specification = specification.and(new playerStatsFilter(new SearchCriteria("birth_year", ":", yearOfBirth)));
        }
        logger.info("Received a request to get all players statistics with filter {}", filters);

        List<player_stats>  StatsData = playerStatsRepo.findAll(specification);

        logger.info("Returning a defense data of players with filter");
        return StatsData;
    }
        
    }
   