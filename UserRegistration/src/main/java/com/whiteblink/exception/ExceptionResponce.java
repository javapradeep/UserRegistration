package com.whiteblink.exception;


import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponce {
	private Date timestamp;
	private String message;
	private String errorCode;
	private String path;

}
