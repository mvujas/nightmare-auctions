package com.github.mvujas.nightmareauctionsbackend.services.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.github.mvujas.nightmareauctionsbackend.model.Item;

@FunctionalInterface
interface TriFunction<P1, P2, P3, R> {
	R apply(P1 param1, P2 param2, P3 param3);
}

public class ItemAllSearchSpecification implements Specification<Item> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5589675987796306439L;

	private SearchParameters searchParameters;
	private List<
		TriFunction<
			Root<Item>,
			CriteriaQuery<?>,
			CriteriaBuilder,
			Predicate>
		> filtersToPerform;

	public ItemAllSearchSpecification(SearchParameters searchParameters) {
		super();
		this.searchParameters = searchParameters;
		initializeFiltersToPerform();
	}
	
	private void initializeFiltersToPerform() {
		filtersToPerform = new LinkedList<>();
		filtersToPerform.add(this::filterMinimumPrice);
		filtersToPerform.add(this::filterMaximumPrice);
	}

	@Override
	public Predicate toPredicate(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		System.out.println(searchParameters);

		Predicate[] predicates = 
				filtersToPerform
				.stream()
				.map(check -> check.apply(root, query, builder))
				.filter(Objects::nonNull)
				.toArray(Predicate[]::new);
		
		return builder.and(predicates);
	}
	
	
	
	private Predicate filterMinimumPrice(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Integer minimumPrice = searchParameters.getMinimumPrice();
		Predicate predicate = null;
		if(minimumPrice != null) {
			predicate = builder.greaterThanOrEqualTo(
					root.get("startingPrice"), minimumPrice);
		}
		return predicate;
	}
	
	private Predicate filterMaximumPrice(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Integer maximumPrice = searchParameters.getMaximumPrice();
		Predicate predicate = null;
		if(maximumPrice != null) {
			predicate = builder.lessThanOrEqualTo(
					root.get("startingPrice"), maximumPrice);
		}
		return predicate;
	}

}
