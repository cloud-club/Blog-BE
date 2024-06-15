package cloudclub.blog.common.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessResponseStatus {

	// S1XX : Server response success
	SUCCESS(HttpStatus.OK ,"S101", "요청이 성공하였습니다."),
	REDIRECT(HttpStatus.FOUND, "S102", "지정된 URL로 이동합니다.");
	// S2XX : something

	private HttpStatus httpStatus;
	private String statusCode;
	private String message;

}
