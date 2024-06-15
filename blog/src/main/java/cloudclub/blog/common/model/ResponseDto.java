package cloudclub.blog.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

	private boolean successOrNot;
	private String statusCode;
	private String message;
	private T data;

}

