package com.in.exeption;


import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomizedResponceEntityExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleallException(Exception ex, WebRequest request) {


		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage()
				,request.getDescription(false));

		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidOTP.class)
	public final ResponseEntity<ErrorDetails> handleInvalidOTP(Exception ex, WebRequest request) {


		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage()
				,request.getDescription(false));

		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUser.class)
	public final ResponseEntity<ErrorDetails> handleInvalidException(Exception ex, WebRequest request) {


		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getLocalizedMessage()
				,request.getDescription(false));

		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OTPexpired.class)
	public final ResponseEntity<ErrorDetails> handleSucsessException(Exception ex, WebRequest request) {



		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage()
				,request.getDescription(false));

		return new ResponseEntity<>(errorDetails,HttpStatus.OK);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage()

				,request.getDescription(false));

		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);


	}

}