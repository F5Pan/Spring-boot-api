package com.wayne.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wayne.exception.InvalidRequestException;
import com.wayne.exception.NotFoundException;
import com.wayne.resource.ErrorResource;
import com.wayne.resource.FieldResource;
import com.wayne.resource.InvalidErrorResource;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ResponseEntity<?> handlerNotFound(RuntimeException e) {

		ErrorResource errorResource = new ErrorResource(e.getMessage());
		return new ResponseEntity<Object>(errorResource, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<?> handlerInvalidRequest(InvalidRequestException e){
	 Errors errors = e.getErrors();
	 List<FieldResource> fieldResources = new ArrayList<FieldResource>();
	 List<FieldError> fieldErrors = errors.getFieldErrors();
	 for (FieldError fieldError : fieldErrors) {
		FieldResource fieldResource = new FieldResource(fieldError.getObjectName()
				,fieldError.getField(),fieldError.getCode(),fieldError.getDefaultMessage());
		
		fieldResources.add(fieldResource);
	}
		InvalidErrorResource ier = new InvalidErrorResource(e.getMessage(),fieldResources);
		return new ResponseEntity<Object>(ier, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<?> handlerException(Exception e){
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
