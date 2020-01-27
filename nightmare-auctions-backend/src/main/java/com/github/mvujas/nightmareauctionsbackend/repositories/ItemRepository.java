package com.github.mvujas.nightmareauctionsbackend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Bid;
import com.github.mvujas.nightmareauctionsbackend.model.Item;
import com.github.mvujas.nightmareauctionsbackend.model.User;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

	@Query(
		"SELECT b "
		+ "FROM Bid b "
		+ "WHERE b.item = :item "
		+ "ORDER BY b.price DESC")
	Bid findHighestBidForItem(@Param("item") Item item);
	
	@Query("SELECT i FROM Item i WHERE i.closingTime IS NULL")
	List<Item> getAllNonClosedItems();
	
	@Query("SELECT i "
			+ "FROM Item i "
			+ "WHERE i.author = :user AND i.closingTime IS NOT NULL "
			+ "		AND i.closingTime >= :after AND i.closingTime <= :before")
	List<Item> getSoldInPeriod(
			@Param("after") Date after,
			@Param("before") Date before,
			@Param("user") User user);
	
}
