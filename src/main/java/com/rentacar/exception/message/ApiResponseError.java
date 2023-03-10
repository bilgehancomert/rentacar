package com.rentacar.exception.message;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponseError {

	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Setter(AccessLevel.NONE)
	private LocalDateTime timeStamp;

	private String message;

	private String requestURI;

	private ApiResponseError() {
		timeStamp = LocalDateTime.now();
	}

	public ApiResponseError(HttpStatus status) {
		this();
		this.message = "Unexpected Error";
		this.status = status;
	}

	public ApiResponseError(HttpStatus status, String message, String requestURI) {
		this(status);
		this.status = status;
		this.message = message;
		this.requestURI = requestURI;
	}

}
