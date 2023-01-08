package com.sports.restservice.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sports.restservice.repository.playerDefenceRepo;
import com.sports.restservice.service.playerDefenseFilter;
import com.sports.restservice.utility.SearchCriteria;
import com.sports.restservice.model.player_defence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



// Defining Rest Controller which is used for request over the server and through browser
@RestController
// Mapping the request which comes to server with DefenseController
@RequestMapping("/fifa/api/v1/defense")
public class DefenseController {
    
    // To allow this class to write logs
    private static final Logger logger = LoggerFactory.getLogger(DefenseController.class);


    @Autowired
    public playerDefenceRepo playerDefenceRepo;
    @GetMapping(value = "/get-all-details")
    public List<player_defence> getAllPlayersDefence() {
        logger.info("Received a request to get all player Defense data");
        List<player_defence> DefenseData = playerDefenceRepo.findAll();
        logger.info("Returning {} Defense records", DefenseData.size());
        return DefenseData;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public player_defence getPlayerDefenseById(@PathVariable long id) {
        logger.info("Received a request to get a player defense data by id={}",id);
        player_defence DefensePlayer = playerDefenceRepo.findById(id).orElse(null);
        logger.info("Returning a defense data of player with id = {}", id);

        return DefensePlayer;
    }

    @GetMapping("/apply-filter")
    public List<player_defence> applyFilter(@RequestParam(required = false) String position,
            @RequestParam(required = false) String team,
            @RequestParam(required = false) Integer tackles,
            @RequestParam(required = false) Integer tacklesWon) {

        Specification<player_defence> specification = Specification.where(null);
        String filters = "";
        if (position != null) {
            filters += "position" + " = " + position;
            specification = specification.and(new playerDefenseFilter(new SearchCriteria("position", ":", position)));
        }
        if (team != null) {
            filters += ", team" + " = " + team;

            specification = specification.and(new playerDefenseFilter(new SearchCriteria("team", ":", team)));
        }
        if (tackles != null) {
            filters += ", tackles" + " = " + tackles;

            specification = specification.and(new playerDefenseFilter(new SearchCriteria("tackles", ":", tackles)));
        }
        if (tacklesWon != null) {
            filters += ", tackles_won" + " = " + tacklesWon;

            specification = specification
                    .and(new playerDefenseFilter(new SearchCriteria("tackles_won", ":", tacklesWon)));
        }
       

        logger.info("Received a request to get all players defense data with filter {}",filters);
        
        List<player_defence> DefenseData = playerDefenceRepo.findAll(specification);
        logger.info("Returning a defense data of players with filter");
        return DefenseData;

    }
}