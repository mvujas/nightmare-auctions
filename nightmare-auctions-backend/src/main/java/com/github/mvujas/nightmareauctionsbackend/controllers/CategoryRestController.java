package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mvujas.nightmareauctionsbackend.model.Category;
import com.github.mvujas.nightmareauctionsbackend.services.CategoryService;

@RestController
@RequestMapping("${api.url}/category")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAll();
	}

}
