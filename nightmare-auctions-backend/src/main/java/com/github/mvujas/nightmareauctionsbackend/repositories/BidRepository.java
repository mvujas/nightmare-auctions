package com.github.mvujas.nightmareauctionsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Bid;
import com.github.mvujas.nightmareauctionsbackend.model.Item;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

	Bid findFirstByItemOrderByPriceDesc(Item item);
	
}
