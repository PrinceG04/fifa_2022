

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
@Table(name = "player_stats") //Providing table name which is already present in DB
// these models which will store entities received from database
public class player_stats {
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
    private String club;
    @Column
    private int birth_year;
    @Column
    private int games;
    @Column
    private int games_starts;
    @Column
    private int minutes;
    @Column
    private Double minutes_90s;
    @Column
    private int goals;
    @Column
    private int assists;
    @Column
    private int goals_pens;
    @Column
    private int pens_made;
    @Column
    private int pens_att;
    @Column
    private int cards_yellow;
    @Column
    private int cards_red;
    @Column
    private Double goals_per90;
    @Column
    private Double assists_per90;
    @Column
    private Double goals_assists_per90;
    @Column
    private Double goals_pens_per90;
    @Column
    private Double goals_assists_pens_per90;
    @Column
    private Double xg;
    @Column
    private Double npxg;
    @Column
    private Double xg_assist;
    @Column
    private Double npxg_xg_assist;
    @Column
    private Double xg_per90;
    @Column
    private Double xg_assist_per90;
    @Column
    private Double xg_xg_assist_per90;
    @Column
    private Double npxg_per90;
    @Column
    private Double npxg_xg_assist_per90;
}