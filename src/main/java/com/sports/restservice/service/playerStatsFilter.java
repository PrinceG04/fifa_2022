package com.sports.restservice.service;

import org.springframework.data.jpa.domain.Specification;

import com.sports.restservice.model.player_stats;
import com.sports.restservice.utility.SearchCriteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



// this explains how we can apply filters on player statistics data

public class playerStatsFilter implements Specification<player_stats> {
    private SearchCriteria criteria;

    public playerStatsFilter(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<player_stats> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String>get(criteria.getField()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String>get(criteria.getField()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getField()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getField()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getField()), criteria.getValue());
            }
        }
        return null;
    }
}
