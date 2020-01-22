package com.github.mvujas.nightmareauctionsbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.model.Item;
import com.github.mvujas.nightmareauctionsbackend.presentationview.ItemPresentationView;
import com.github.mvujas.nightmareauctionsbackend.services.ItemService;
import com.github.mvujas.nightmareauctionsbackend.services.search.ItemAllSearchSpecification;
import com.github.mvujas.nightmareauctionsbackend.services.search.SearchParameters;

@RestController
@RequestMapping("${api.url}/item")
public class ItemRestController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping
	@JsonView(ItemPresentationView.SummaryView.class)
	public Page<Item> getAllItems(
			@RequestBody(required = false) SearchParameters searchParams,
			Pageable pageable) {
		return itemService.getAll(
				new ItemAllSearchSpecification(searchParams),
				pageable);
				
	}

}
