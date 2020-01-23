package com.github.mvujas.nightmareauctionsbackend.exceptionhandling;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationErrorExceptionHandler {

	@ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }
	
	private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        Error error = new Error("validation error");
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
	
	static class SimpleFieldError {
		private String field;
		private String message;
		
		SimpleFieldError(String field, String message) {
			this.field = field;
			this.message = message;
		}
		
		public String getField() {
			return field;
		}
		
		public String getMessage() {
			return message;
		}
	}

    static class Error {
        private final String message;
        private List<SimpleFieldError> errors = new ArrayList<>();

        Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String objectName, String errorMessage) {
            errors.add(new SimpleFieldError(objectName, errorMessage));
        }

        public List<SimpleFieldError> getErrors() {
            return errors;
        }
    }
	
}
