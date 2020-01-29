package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.github.mvujas.nightmareauctionsbackend.domain.SoldItem;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
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
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) Integer minimumPrice,
			@RequestParam(required = false) Integer maximumPrice,
			@RequestParam(required = false) Boolean over,
			Pageable pageable) {
		
		SearchParameters searchParams = new SearchParameters(
				name, 
				category, 
				username,
				minimumPrice, 
				maximumPrice,
				over,
				null,
				null);
	
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
				itemCreationMessage.getDetails(),
				principal.getName());
	}
	
	@GetMapping("/report")
	public void getAllItemsReport(HttpServletResponse response) 
			throws JRException, IOException {
		List<Item> items = itemService.getAllNonClosedItems();
			
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
		itemService.endAuction(id, principal.getName());
	}
	
	@GetMapping("/soldStatistics/{username}")
	@PreAuthorize("isAuthenticated()")
	public List<SoldItem> soldItemsInPeriod(
			@PathVariable String username,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date after,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date before,
			Pageable pageable,
			Principal principal) {
		if(!principal.getName().equals(username)) {
			throw new ResourceOperationException(
					"Users can see only their own statistics");
		}
		
		return itemService.soldItemsInPeriod(before, after, principal.getName());
	}

}
