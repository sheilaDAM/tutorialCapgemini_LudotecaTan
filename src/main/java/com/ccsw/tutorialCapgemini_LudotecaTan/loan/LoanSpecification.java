/**
 * 
 */
package com.ccsw.tutorialCapgemini_LudotecaTan.loan;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.tutorialCapgemini_LudotecaTan.common.criteria.SearchCriteria;
import com.ccsw.tutorialCapgemini_LudotecaTan.loan.model.Loan;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Project info :)
 *
 * @project Ludoteca Tán
 * @author Sheila Isabel Jiménez Nieto
 * @since 4 jul 2024
 * @lastModified 4 jul 2024
 * @version 1.0
 *
 **/

public class LoanSpecification implements Specification<Loan> {

	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	public LoanSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getKey().contains(".")) {
			String[] keys = criteria.getKey().split("\\.");
			Path<?> path = root.get(keys[0]);
			for (int i = 1; i < keys.length; i++) {
				path = path.get(keys[i]);
			}

			if (criteria.getOperation().equalsIgnoreCase(":")) {
				if (path.getJavaType() == String.class) {
					return builder.like(builder.lower((Expression<String>) path),
							"%" + criteria.getValue().toString().toLowerCase() + "%");
				} else {
					return builder.equal(path, criteria.getValue());
				}
			} else if (criteria.getOperation().equalsIgnoreCase(">=")) {
				return builder.greaterThanOrEqualTo(path.as(LocalDate.class), (LocalDate) criteria.getValue());
			} else if (criteria.getOperation().equalsIgnoreCase("<=")) {
				return builder.lessThanOrEqualTo(path.as(LocalDate.class), (LocalDate) criteria.getValue());
			}
		}

		switch (criteria.getOperation()) {
		case ":":
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(builder.lower(root.get(criteria.getKey())),
						"%" + criteria.getValue().toString().toLowerCase() + "%");
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		case ">=":
			return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(LocalDate.class),
					(LocalDate) criteria.getValue());
		case "<=":
			return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(LocalDate.class),
					(LocalDate) criteria.getValue());
		default:
			return null;
		}

	}

	/*
	 * @Override public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?>
	 * query, CriteriaBuilder builder) { if (criteria.getKey().contains(".")) {
	 * String[] keys = criteria.getKey().split("\\."); Path<?> path =
	 * root.get(keys[0]); for (int i = 1; i < keys.length; i++) { path =
	 * path.get(keys[i]); } return builder.equal(path, criteria.getValue()); }
	 * 
	 * switch (criteria.getOperation()) { case ":": return
	 * builder.equal(root.get(criteria.getKey()), criteria.getValue()); case ">":
	 * return builder.greaterThanOrEqualTo(root.get(criteria.getKey()),
	 * criteria.getValue().toString()); case "<": return
	 * builder.lessThanOrEqualTo(root.get(criteria.getKey()),
	 * criteria.getValue().toString()); default: return null; } }
	 */

}
