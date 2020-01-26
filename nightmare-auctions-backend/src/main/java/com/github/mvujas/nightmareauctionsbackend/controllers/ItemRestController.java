package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.controllers.messages.BidMessage;
import com.github.mvujas.nightmareauctionsbackend.controllers.messages.ItemCreationMessage;
import com.github.mvujas.nightmareauctionsbackend.managers.JasperManager;
import com.github.mvujas.nightmareauctionsbackend.model.Item;
import com.github.mvujas.nightmareauctionsbackend.presentationview.ItemPresentationView;
import com.github.mvujas.nightmareauctionsbackend.services.ItemService;
import com.github.mvujas.nightmareauctionsbackend.services.search.ItemAllSearchSpecification;
import com.github.mvujas.nightmareauctionsbackend.services.search.SearchParameters;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping("${api.url}/item")
public class ItemRestController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private JasperManager jasperManager;
	
	@GetMapping
	@JsonView(ItemPresentationView.SummaryView.class)
	public Page<Item> getAllItems(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String categoryName,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Integer minimumPrice,
			@RequestParam(required = false) Integer maximumPrice,
			@RequestParam(required = false) Boolean over,
			Pageable pageable) {
		
		SearchParameters searchParams = new SearchParameters(
				name, 
				categoryName, 
				username,
				minimumPrice, 
				maximumPrice,
				over);
	
		return itemService.getAll(
				new ItemAllSearchSpecification(searchParams),
				pageable);
	}
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public void addItem(
			@Valid @RequestBody(required = true) ItemCreationMessage itemCreationMessage,
			Principal principal) {
		itemService.createItem(
				itemCreationMessage.getName().trim(),
				itemCreationMessage.getStartingPrice(),
				itemCreationMessage.getCategoryName(),
				principal.getName());
	}
	
	@GetMapping("/report")
	public void getAllItemsReport(HttpServletResponse response) 
			throws JRException, IOException {
		List<Item> items = itemService.getAll();
			
		JasperReport jasperReport = jasperManager.loadReport("all-items-report");
		JasperPrint jasperPrint = jasperManager.fillReport(jasperReport, items);
		
		jasperManager.packPrintPdfIntoResponse(response, jasperPrint, "Ongoing Auctions Report");
	}
	
	@GetMapping("/{id}")
	@JsonView(ItemPresentationView.FullView.class)
	public Item getById(@PathVariable(required = true) Integer id) {
		return itemService.getById(id);
	}
	
	@PostMapping("/{id}/bid")
	@PreAuthorize("isAuthenticated()")
	public void bidItem(
			@PathVariable(required = true) Integer id,
			@Valid @RequestBody(required = true) BidMessage bidMessage,
			Principal principal) {
		itemService.addBid(id, principal.getName(), bidMessage.getPrice());
	}
	
	@PostMapping("/{id}/end")
	@PreAuthorize("isAuthenticated()")
	public void endAuction(
			@PathVariable(required = true) Integer id,
			Principal principal) {
		System.out.println("Stiglo nesto");
		
		itemService.endAuction(id, principal.getName());
	}

}
