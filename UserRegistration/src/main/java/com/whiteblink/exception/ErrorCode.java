package com.whiteblink.exception;

public enum ErrorCode{
	OK("IS_200","Success"),
	CREATED("IS_201","Created"),
	ACCEPTED("IS_202","Accepted"),
	NOCONTENT("IS_204","No Data Found"),
	UNAUTHORISED("IS_401","Unauthrised Service"),
	NOTFOUND("IS_404","File Not Found"),
	METHODNOTALLOWED("IS_405","Method Not Allowed"),
	NOTACCEPTABLE("IS_406","Data Must be In Proper Format"),
	REQUESTTIMEOUT("IS_408","Request Takes Much Time"),
	UNSUPPORTEDMEDIATYPE("IS_415","Data Must be In Proper Format"),
	SERVER_EXCEPTION("IS_500","Server Is Not Running"),
	BADREQUEST("IS_400","Bad Request"),
	DBException("IS_500","SQLException");

	private final String statusCode;
	private final String message;
	private ErrorCode(String statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	public String getValue() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}

}
