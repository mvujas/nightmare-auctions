package com.github.mvujas.nightmareauctionsbackend.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.mvujas.nightmareauctionsbackend.validation.ExistantCategoryNameValidator;


@Constraint(validatedBy = ExistantCategoryNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExistantCategoryName {

	public String message() default "There is no category with this name";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};
	
}
