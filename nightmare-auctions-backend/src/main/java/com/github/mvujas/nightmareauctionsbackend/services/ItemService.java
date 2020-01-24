package com.github.mvujas.nightmareauctionsbackend.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.controllers.messages.ItemCreationMessage;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceNotFoundException;
import com.github.mvujas.nightmareauctionsbackend.model.Category;
import com.github.mvujas.nightmareauctionsbackend.model.Item;
import com.github.mvujas.nightmareauctionsbackend.model.User;
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

}
