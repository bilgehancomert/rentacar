package com.rentacar.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rentacar.exception.message.ApiResponseError;
import com.rentacar.exception.ResourceNotFoundException;

@ControllerAdvice
public class RentACarExcepitonHandler extends ResponseEntityExceptionHandler{
	
	private ResponseEntity<Object> buildResponseEntity(ApiResponseError error){
		return new ResponseEntity<>(error,error.getStatus());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class) 
	protected ResponseEntity<Object> handleResourceNotFoundExcepiton(ResourceNotFoundException ex,WebRequest request){
		
		ApiResponseError error = new ApiResponseError(HttpStatus.NOT_FOUND, ex.getMessage(), 
																request.getDescription(false));
		return buildResponseEntity(error);	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		List<String> errors = ex.getBindingResult().getFieldErrors().
													stream().
													map(e->e.getDefaultMessage()).
													collect(Collectors.toList());
		
		ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST,
															errors.get(0).toString(),
															request.getDescription(false));
		
		return buildResponseEntity(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO RETURN DEĞERİ DÜZELTİLECEK.
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	//RuntimeException class'ından Handler yaptık.
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeExcepiton(RuntimeException ex,WebRequest request){
			
		ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), 
																	request.getDescription(false));

		return buildResponseEntity(error);
		
	}
	//Ata class olan Exception class'ından Handler yaptık.
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGeneralExcepiton(Exception ex,WebRequest request){
			
		ApiResponseError error = new ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), 
																	request.getDescription(false));

		return buildResponseEntity(error);
		
	}
}
