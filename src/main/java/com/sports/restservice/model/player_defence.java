package com.sports.restservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_defence") // Providing table name which is already present in DB
// these models which will store entities received from database 
public class player_defence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String player;
    @Column
    private String position;
    @Column
    private String team;
    @Column
    private String age;
    @Column
    private Integer birth_year;
    @Column
    private Double minutes_90s;
    @Column
    private Integer tackles;
    @Column
    private Integer tackles_won;
    @Column
    private Integer tackles_def_3rd;
    @Column
    private Integer tackles_mid_3rd;
    @Column
    private Integer tackles_att_3rd;
    @Column
    private Integer dribble_tackles;
    @Column
    private Integer dribbles_vs;
    @Column
    private Double dribble_tackles_pct;
    @Column
    private Integer dribbled_past;
    @Column
    private Integer blocks;
    @Column
    private Integer blocked_shots;
    @Column
    private Integer blocked_passes;
    @Column
    private Integer Integererceptions;
    @Column
    private Integer tackles_Integererceptions;
    @Column
    private Integer clearances;
    @Column
    private Integer errors;
}