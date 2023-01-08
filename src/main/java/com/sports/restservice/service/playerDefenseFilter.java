package com.sports.restservice.service;

import org.springframework.data.jpa.domain.Specification;

import com.sports.restservice.model.player_defence;
import com.sports.restservice.utility.SearchCriteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
// this explains how we can apply filters on defense data
public class playerDefenseFilter implements Specification<player_defence> {
    private SearchCriteria criteria;

    public playerDefenseFilter(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<player_defence> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
