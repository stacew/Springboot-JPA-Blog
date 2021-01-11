package com.doksam.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.dto.ResponseDto;

@ControllerAdvice // Exception이 throw 됐을 때, 이쪽으로 오게 하려면
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class)
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
		return new ResponseDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}	
}
