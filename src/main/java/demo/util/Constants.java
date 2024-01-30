package demo.util;

public class Constants {
	
	public static final String API_DL_STATUS = "api.dl.status";
	public static final String API_DL_APPLY = "api.dl.apply";
	
	public static final String TABLE_LOG = "ehrms_log";
	
	public static final String REQUEST = "request";
	public static final String FAILED = "Failed";
	public static final String ACCEPT = "accept";

	
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final String AUTHORIZATION = "authorization";
	public static final String COUNT = "count";
	public static final String X_AUTH_TOKEN = "x-authenticated-user-token";
	
	public static final String MSG_SLOT_NOT_AVAILABLE = "Slot not available";
	public static final String MSG_UNAUTHORIZED_USER = "Unauthorized";
	
	public static final String TOPIC_NAME = "dl_test";
    public static final String GROUP_ID = "group_id";
	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}

}
