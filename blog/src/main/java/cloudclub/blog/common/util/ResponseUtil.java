package cloudclub.blog.common.util;

import cloudclub.blog.common.enums.SuccessResponseStatus;
import cloudclub.blog.common.model.ResponseDto;
import cloudclub.blog.common.model.ResponsePagingDto;
import lombok.experimental.UtilityClass;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtil {

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse() {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(SuccessResponseStatus.SUCCESS.getStatusCode())
			.message(SuccessResponseStatus.SUCCESS.getMessage())
			.data(null)
			.build();
		return new ResponseEntity<>(dto, SuccessResponseStatus.SUCCESS.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(HttpHeaders httpHeaders) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(SuccessResponseStatus.SUCCESS.getStatusCode())
			.message(SuccessResponseStatus.SUCCESS.getMessage())
			.data(null)
			.build();
		return new ResponseEntity<>(dto, httpHeaders, SuccessResponseStatus.SUCCESS.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(T data) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(SuccessResponseStatus.SUCCESS.getStatusCode())
			.message(SuccessResponseStatus.SUCCESS.getMessage())
			.data(data)
			.build();
		return new ResponseEntity<>(dto, SuccessResponseStatus.SUCCESS.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(T data, HttpHeaders httpHeaders) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(SuccessResponseStatus.SUCCESS.getStatusCode())
			.message(SuccessResponseStatus.SUCCESS.getMessage())
			.data(data)
			.build();
		return new ResponseEntity<>(dto, httpHeaders, SuccessResponseStatus.SUCCESS.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(SuccessResponseStatus status) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(status.getStatusCode())
			.message(status.getMessage())
			.data(null)
			.build();
		return new ResponseEntity<>(dto, status.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(SuccessResponseStatus status, HttpHeaders httpHeaders) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(status.getStatusCode())
			.message(status.getMessage())
			.data(null)
			.build();
		return new ResponseEntity<>(dto, httpHeaders, status.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(SuccessResponseStatus status, T data) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(status.getStatusCode())
			.message(status.getMessage())
			.data(data)
			.build();
		return new ResponseEntity<>(dto, status.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponseDto<T>> createSuccessResponse(SuccessResponseStatus status, T data, HttpHeaders httpHeaders) {
		ResponseDto<T> dto = ResponseDto.<T>builder()
			.successOrNot(true)
			.statusCode(status.getStatusCode())
			.message(status.getMessage())
			.data(data)
			.build();
		return new ResponseEntity<>(dto, httpHeaders, status.getHttpStatus());
	}



	public static <T> ResponseEntity<ResponsePagingDto<T>> createSuccessResponse(T data, Pageable pageable) {
		ResponsePagingDto<T> dto = ResponsePagingDto.<T>builder()
			.statusCode(SuccessResponseStatus.SUCCESS.getStatusCode())
			.message(SuccessResponseStatus.SUCCESS.getMessage())
			.data(data)
			.page(pageable.getOffset())
			.size(pageable.getPageSize())
			.build();
		return new ResponseEntity<>(dto, SuccessResponseStatus.SUCCESS.getHttpStatus());
	}

	public static <T> ResponseEntity<ResponsePagingDto<T>> createSuccessResponse(T data, HttpHeaders httpHeaders, Pageable pageable) {
		ResponsePagingDto<T> dto = ResponsePagingDto.<T>builder()
			.statusCode(SuccessResponseStatus.SUCCESS.getStatusCode())
			.message(SuccessResponseStatus.SUCCESS.getMessage())
			.data(data)
			.page(pageable.getOffset())
			.size(pageable.getPageSize())
			.build();
		return new ResponseEntity<>(dto, httpHeaders, SuccessResponseStatus.SUCCESS.getHttpStatus());
	}
}
