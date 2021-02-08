package com.whiteblink.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;



@SuppressWarnings("serial")
public class ExceptionLogger extends RuntimeException{
	private static final Logger logger=LoggerFactory.getLogger(ExceptionLogger.class);

	private HttpStatus status;
	private String errorCode = "";
	private String message = "";
	//private Throwable exception;

	public ExceptionLogger(String errorCode, HttpStatus status) {
		System.out.println("exceptionLogger");
		this.errorCode = errorCode;
		this.status = status;
		logger.error("ErrorCode:{}", errorCode);
	}

	public ExceptionLogger(String errorCode, String message, HttpStatus status) {
		this.message = message;
		this.errorCode = errorCode;
		this.status = status;
		logger.error("errorCode:{},message:{}", errorCode, message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
