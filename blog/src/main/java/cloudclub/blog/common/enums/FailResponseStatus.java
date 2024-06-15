package cloudclub.blog.common.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FailResponseStatus {

	// example
	// HttpStatus 관련 논의 필요
	BAD_REQUEST("BAD_REQUEST", "E400", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
	INVALID_PARAM_ERROR("INVALID_PARAM_ERROR", "E401", HttpStatus.BAD_REQUEST, "파라미터가 유효하지 않습니다."),
	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR",
		"E500",
		HttpStatus.INTERNAL_SERVER_ERROR,
		"내부 서버 오류입니다.");

	private final String statusName;
	private final String statusCode;
	private final HttpStatus httpStatusCode;
	private final String message;


	@Override
	public String toString() {
		return message;
	}
}
