package com.github.mvujas.nightmareauctionsbackend.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.mvujas.nightmareauctionsbackend.repositories.CategoryRepository;
import com.github.mvujas.nightmareauctionsbackend.validation.annotations.ExistantCategoryName;

public class ExistantCategoryNameValidator implements ConstraintValidator<ExistantCategoryName, String> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && categoryRepository.findByName(value) != null;
	}

}
