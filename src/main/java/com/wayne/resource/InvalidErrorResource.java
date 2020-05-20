package com.wayne.resource;

public class InvalidErrorResource {

	private String message;
	private Object errors;

	public InvalidErrorResource(String message, Object errors) {
		super();
		this.message = message;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

}
