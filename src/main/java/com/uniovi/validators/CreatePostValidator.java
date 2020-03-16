package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Publicacion;

@Component
public class CreatePostValidator implements Validator {

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
