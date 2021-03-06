package com.github.mvujas.nightmareauctionsbackend.services.search;

import java.util.Date;
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
		filtersToPerform.add(this::filterCategory);
		filtersToPerform.add(this::filterName);
		filtersToPerform.add(this::filterAuthorUsername);
		filtersToPerform.add(this::filterIsOver);
		filtersToPerform.add(this::filterBefore);
		filtersToPerform.add(this::filterAfter);
	}

	@Override
	public Predicate toPredicate(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Predicate[] predicates = new Predicate[0];
		
		if(searchParameters != null) {
			predicates = 
				filtersToPerform
					.stream()
					.map(check -> check.apply(root, query, builder))
					.filter(Objects::nonNull)
					.toArray(Predicate[]::new);
		}
		
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
	
	private Predicate filterCategory(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		String categoryName = searchParameters.getCategoryName();
		Predicate predicate = null;
		if(categoryName != null) {
			predicate = builder.equal(
					root.join("category").get("name"), 
					categoryName);
		}
		return predicate;
	}
	
	private Predicate filterAuthorUsername(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		String username = searchParameters.getUsername();
		Predicate predicate = null;
		if(username != null) {
			predicate = builder.equal(
					root.join("author").get("username"), 
					username);
		}
		return predicate;
	}
	
	private Predicate filterName(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Boolean isOver = searchParameters.isOver();
		Predicate predicate = null;
		if(isOver != null) {
			predicate = builder.equal(root.get("over"), isOver);
		}
		return predicate;
	}

	private Predicate filterIsOver(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		String name = searchParameters.getName();
		Predicate predicate = null;
		if(name != null) {
			predicate = builder
					.like(
						builder.lower(root.get("name")), 
						containsLowerCase(name));
		}
		return predicate;
	}
	
	private Predicate filterBefore(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Date before = searchParameters.getBefore();
		Predicate predicate = null;
		if(before != null) {
			predicate = builder
					.lessThanOrEqualTo(root.get("closingTime"), before);
		}
		return predicate;
	}
	
	private Predicate filterAfter(
			Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Date after = searchParameters.getBefore();
		Predicate predicate = null;
		if(after != null) {
			predicate = builder
					.greaterThanOrEqualTo(root.get("closingTime"), after);
		}
		return predicate;
	}
	
	
	
	private String containsLowerCase(String searchField) {
	    return "%" + searchField.toLowerCase() + "%";
	}

}
