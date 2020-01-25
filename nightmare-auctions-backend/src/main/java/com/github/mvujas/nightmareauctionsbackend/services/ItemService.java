package com.github.mvujas.nightmareauctionsbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceNotFoundException;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
import com.github.mvujas.nightmareauctionsbackend.model.Bid;
import com.github.mvujas.nightmareauctionsbackend.model.Category;
import com.github.mvujas.nightmareauctionsbackend.model.Item;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.repositories.BidRepository;
import com.github.mvujas.nightmareauctionsbackend.repositories.CategoryRepository;
import com.github.mvujas.nightmareauctionsbackend.repositories.ItemRepository;
import com.github.mvujas.nightmareauctionsbackend.repositories.UserRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BidRepository bidRepository;
	
	public List<Item> getAll() {
		return itemRepository.findAll();
	}
	
	public Page<Item> getAll(
			Specification<Item> specification, Pageable pageable) {
		return itemRepository.findAll(specification, pageable);
	}
	
	public Item getById(int id) {
		Item item = itemRepository
				.findById(id)
				.orElse(null);
		
		if(item == null) {
			throw new ResourceNotFoundException(
					"There is no item with given id");
		}

		return item;
	}
	
	public void createItem(
			String name,
			int startingPrice,
			String categoryName,
			String authorUsername) {
		
		Category category = 
				categoryRepository.findByName(categoryName);
		
		User user = userRepository.findByUsername(authorUsername);
		
		Item item = new Item(
				name, 
				startingPrice, 
				category);
		
		item.setAuthor(user);
		
		itemRepository.saveAndFlush(item);
	}
	
	public void addBid(
			int itemId,
			String bidderUsername,
			int price) {
		Item item = itemRepository.findById(itemId).orElse(null);
		if(item == null) {
			throw new ResourceNotFoundException("There is no item under given id");
		}
		
		if(item.isOver()) {
			throw new ResourceOperationException("Auction is over");
		}
		
		if(item.getPrice() >= price) {
			throw new ResourceOperationException("Bid price is lower or equal than current item price");
		}
		
		User bidder = userRepository.findByUsername(bidderUsername);
		if(bidder == null) {
			throw new ResourceOperationException("Cannot find user under given username");
		}
		
		if(bidder == item.getAuthor()) {
			throw new ResourceOperationException("Author of item cannot bid for it");
		}
		
		Bid bid = new Bid();
		bid.setAuthor(bidder);
		bid.setItem(item);
		bid.setPrice(price);
	
		bidRepository.saveAndFlush(bid);
	}
	
	public void endAuction(
			int itemId,
			String userUsername) {
		Item item = itemRepository.findById(itemId).orElse(null);
		if(item == null) {
			throw new ResourceNotFoundException("There is no item under given id");
		}
		
		if(item.isOver()) {
			throw new ResourceOperationException("Auction is over");
		}
		
		User user = userRepository.findByUsername(userUsername);
		if(user == null) {
			throw new ResourceOperationException("Cannot find user under given username");
		}
		
		if(user != item.getAuthor()) {
			throw new ResourceOperationException("Only author of auction can end it");
		}
		
		item.endAuction();
		
		itemRepository.saveAndFlush(item);
		
		// TO-DO create ocena object
	}

}
