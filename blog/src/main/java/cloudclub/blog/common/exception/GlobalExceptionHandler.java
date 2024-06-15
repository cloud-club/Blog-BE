package cloudclub.blog.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cloudclub.blog.common.enums.FailResponseStatus;
import cloudclub.blog.common.model.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RestApiException.class)
	public <T> ResponseEntity<ResponseDto<T>> handleCustomException(final RestApiException e) {
		final FailResponseStatus errorCode = e.getFailResponseStatus();
		e.printStackTrace();
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(false)
			.statusCode(errorCode.getStatusCode().toString())
			.message(errorCode.getMessage())
			.data(null)
			.build();
		return new ResponseEntity<>(dto, errorCode.getHttpStatusCode());
	}

	@ExceptionHandler({Exception.class})
	public <T> ResponseEntity<ResponseDto<T>> handleAllException(final Exception ex) {
		final FailResponseStatus errorCode = FailResponseStatus.INTERNAL_SERVER_ERROR;
		ex.printStackTrace();
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(false)
			.statusCode(errorCode.getStatusCode().toString())
			.message(errorCode.getMessage())
			.data(null)
			.build();
		return new ResponseEntity<>(dto, errorCode.getHttpStatusCode());
	}

}