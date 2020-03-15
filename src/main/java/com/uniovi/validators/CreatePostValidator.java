package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Publicacion;
import com.uniovi.services.PostsService;

@Component
public class CreatePostValidator implements Validator {

	@Autowired
	private PostsService postsService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Publicacion.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "texto", "Error.empty");
	}

}
