package cloudclub.blog.common.exception;

import cloudclub.blog.common.enums.FailResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

	private FailResponseStatus failResponseStatus;

	public RestApiException(FailResponseStatus e) {
		super(e.getMessage());
		this.failResponseStatus = e;
	}

}