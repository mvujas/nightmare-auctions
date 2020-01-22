package com.github.mvujas.nightmareauctionsbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.model.Category;
import com.github.mvujas.nightmareauctionsbackend.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

}
