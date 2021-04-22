package com.projeto.seguradora.api.exception;

public class ObjectError {

	private final String message;
	private final String field;
	private final Object parameter;
    private final String status;
	
	public ObjectError(String message, String field, Object parameter, String status) {
		super();
		this.message = message;
		this.field = field;
		this.parameter = parameter;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public String getField() {
		return field;
	}

	public Object getParameter() {
		return parameter;
	}

	public String getStatus() {
		return status;
	}
	
	

	
	
}
