package com.nturbo1.satWebsiteProjBack.web.controller.constants;

public class RestApiConst {
	
	public static final String API_ROOT_PATH = "/api/{apiVersion}";

	public static final String STUDENT_API_ROOT_PATH = API_ROOT_PATH + "/students";
	
//	public static final String STUDENT_COURSES_API_PATH = STUDENT_API_ROOT_PATH + 
//			"/{studentId:\\d+}/courses";

	public static final String TEST_API_ROOT_PATH = API_ROOT_PATH + "/tests";
  
	public static final String AUTH_API_ROOT_PATH = API_ROOT_PATH + "/auth";
  
	public static final String LOGOUT_AUTH_PATH = AUTH_API_ROOT_PATH + "/logout";
  
	public static final String PAYMENT_API_ROOT_PATH = API_ROOT_PATH + "/payment";
	
	public static final String CHARGE_PAYMENT_PATH = PAYMENT_API_ROOT_PATH + "/charge";
  
	public static final String COURSES_API_ROOT_PATH = API_ROOT_PATH + "/courses";

	private RestApiConst(){
	}
}
