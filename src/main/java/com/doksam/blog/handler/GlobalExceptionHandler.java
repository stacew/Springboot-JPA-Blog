package com.doksam.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.doksam.blog.dto.ResponseDto;

@ControllerAdvice // Exception이 throw 됐을 때, 이쪽으로 오게 하려면
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseDto handleArgumentException(IllegalArgumentException e) {
		return new ResponseDto(HttpStatus.BAD_REQUEST, "GlobalExceptionHandler : " + e.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseDto handleException(Exception e) {
		return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, "GlobalExceptionHandler : " + e.getMessage());
	}
}
