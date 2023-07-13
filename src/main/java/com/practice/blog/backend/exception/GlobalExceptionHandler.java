package com.practice.blog.backend.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.blog.backend.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
		String message=e.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException e){
		Map<String,String> res=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(ex->{
			String fieldName=((FieldError)ex).getField();
			String message=ex.getDefaultMessage();
			res.put(fieldName,message);
		});
		return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse> methodNotSupportedException(HttpServletRequest request,HttpRequestMethodNotSupportedException e){		
		String message=e.getMessage();
		String link = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();		
		ApiResponse apiResponse=new ApiResponse(message + " "+link,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<ApiResponse> missingPathVariableException(HttpServletRequest request,MissingPathVariableException e){		
		String message=e.getMessage();
		String link = ServletUriComponentsBuilder.fromCurrentRequest().toUriString()+
				" Please check the controller request mapping of the method";
		
		ApiResponse apiResponse=new ApiResponse(message + " "+link,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
