package com.whiteblink.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class GlobalExceptionHadler {

	@ExceptionHandler(ExceptionLogger.class)
	public ResponseEntity<ExceptionResponce> anyExceptionHandler(
			ExceptionLogger el,WebRequest request){
		ExceptionResponce response=new ExceptionResponce(new Date(), el.getMessage(),el.getErrorCode(),request.getDescription(false));
		return new ResponseEntity<ExceptionResponce>(response,el.getStatus());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionResponce> runTimeExceptionHandler(
			RuntimeException re,WebRequest request)
	{
		ExceptionResponce response=new ExceptionResponce(new Date(), re.getMessage(),ErrorCode.BADREQUEST.getValue(),request.getDescription(false));
		return new ResponseEntity<ExceptionResponce>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponce> exceptionHandler(Exception e,WebRequest request){
		ExceptionResponce response=new ExceptionResponce(
				new Date(),e.getMessage(),ErrorCode.NOTFOUND.getValue(),request.getDescription(false));
		return new ResponseEntity<ExceptionResponce>(response,HttpStatus.BAD_REQUEST);
	}

}
