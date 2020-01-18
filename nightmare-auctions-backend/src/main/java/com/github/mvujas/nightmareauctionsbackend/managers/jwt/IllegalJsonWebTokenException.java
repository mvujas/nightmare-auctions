package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class IllegalJsonWebTokenException extends RuntimeException {

	public IllegalJsonWebTokenException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalJsonWebTokenException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public IllegalJsonWebTokenException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public IllegalJsonWebTokenException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public IllegalJsonWebTokenException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
