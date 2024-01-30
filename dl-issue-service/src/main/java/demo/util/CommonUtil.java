package demo.util;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import demo.model.ApiResponse;
import demo.model.ApiResponseParams;

public class CommonUtil {


	public static ApiResponse createResponse(String api) {
		ApiResponse response = new ApiResponse();
		response.setId(api);
		response.setVer("1.0");
		response.setParams(new ApiResponseParams());
		response.getParams().setStatus("success");
		response.setResponseCode(HttpStatus.OK);
		response.setTs(LocalDateTime.now().toString());
		return response;
	}

	
}
