package cloudclub.blog.common.model;

import java.net.http.HttpResponse;

import lombok.Builder;

@Builder
public class ResponsePagingDto<T> {
	private String statusCode;

	private String message;

	private Long page;

	private Integer size;

	private T data;

}