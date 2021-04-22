package com.projeto.seguradora.api.exception;

import org.springframework.http.HttpStatus;

public class ExceptionErros extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionErros(String mensagem, HttpStatus status) {
		super(mensagem);
	}
	
	public ExceptionErros(String msg, Throwable cause) {

		super(msg,cause);
	}


}
