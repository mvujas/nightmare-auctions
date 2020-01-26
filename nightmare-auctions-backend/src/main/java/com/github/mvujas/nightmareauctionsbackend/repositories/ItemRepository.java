package com.github.mvujas.nightmareauctionsbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Bid;
import com.github.mvujas.nightmareauctionsbackend.model.Item;

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
	
}
