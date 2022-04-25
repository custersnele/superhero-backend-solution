package be.pxl.superhero.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidatedExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseBody> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		return new ResponseEntity<>(new ErrorResponseBody("Validation failed.", errors), HttpStatus.BAD_REQUEST);
	}


	private static class ErrorResponseBody {
		private final String message;
		private final List<String> errors;

		public ErrorResponseBody(String message, List<String> errors) {
			this.message = message;
			this.errors = errors;
		}

		public String getMessage() {
			return message;
		}

		public List<String> getErrors() {
			return errors;
		}
	}
}
