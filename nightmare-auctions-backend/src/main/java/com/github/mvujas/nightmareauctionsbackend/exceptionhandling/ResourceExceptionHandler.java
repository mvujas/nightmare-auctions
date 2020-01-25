package com.github.mvujas.nightmareauctionsbackend.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.DuplicateResourceException;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceNotFoundException;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(DuplicateResourceException.class)
    public SimpleErrorResponseMessage handleDuplicateResouce(
    		DuplicateResourceException ex) {
        return new SimpleErrorResponseMessage(ex.getMessage());
    }
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public SimpleErrorResponseMessage handleResourceNotFound(
    		ResourceNotFoundException ex) {
        return new SimpleErrorResponseMessage(ex.getMessage());
    }
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    public SimpleErrorResponseMessage handleUsernameNotFound(
    		UsernameNotFoundException ex) {
        return new SimpleErrorResponseMessage(ex.getMessage());
    }
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ResourceOperationException.class)
    public SimpleErrorResponseMessage handleResourceOperationException(
    		ResourceOperationException ex) {
        return new SimpleErrorResponseMessage(ex.getMessage());
    }
}
