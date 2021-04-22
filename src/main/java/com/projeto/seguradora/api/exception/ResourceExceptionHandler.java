package com.projeto.seguradora.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    List<ObjectError> erros = getErrors(ex);
	    ErrorResponse errorResponse = getErrorResponse(ex, status, erros);
	    return new ResponseEntity<>(errorResponse, status);
	}

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ObjectError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        ObjectError err = new ObjectError(e.getMessage(), HttpStatus.BAD_REQUEST.toString(), System.currentTimeMillis(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }



    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ObjectError> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ObjectError(error.getDefaultMessage(), error.getField(), error.getRejectedValue(), error.getCode()))
                .collect(Collectors.toList());
    }


}
