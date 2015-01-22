package com.autoease.framework;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.autoease.framework.utilities.TimeUtils;

//import com.autoease.framework.utilities.TimeUtils;

/**
* @ClassName: GlobalDefinition
* @Description: TODO
* @author GAURAV TIWARI [gauravtiwari91@yahoo.com]
* @date Apr 9, 2014 5:56:22 PM
* 
*/

public class GlobalConstants {
	
	
	//Capture screenshot property
	
	public final static String CAPTURE_SCREENSHOT="Y";
	
	
	//Project name
	public final static String PROJECT_NAME = "AutoEase Framework";
	public final static String PROJECT_DIR = System.getProperty("user.dir");
	  
	 //additional folder path settings
	  public final static String Log4J_LOG_DIR="test-logs";   // IE console log folder
	  public final static String REPORTRESULT_DIR="test-result";
	  public final static String TESTNG_REPORT_DIR="test-output";
	  public final static String RESOURCES_DIR="test-resources";
	  public final static String TESTSUITE_DIR="testsuite-pro";
	  public final static String SELENIUM_DRIVER_PATH="test-drivers";
	  public final static String TEST_CONFIG="test-configs";
	  
	  //email report file name
	  public final static String DAILY_REPORT_FILE="TestingExecutionReport_"+ 
              new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())+".htm";
	  
	  //resource folder  
	  public final static String MOUSE_CURSE_ICON="cursor_white.png";
	  public final static String E2E_EXCEL_FILE="TestData.xls";
	  public final static String REPORT_TEMPLATE_FILE="report_template.htm";
	  public final static String EMAIL_LOG_FILE="logo.png";
	  public final static String AUTOITX_FILE="AutoItX3.dll";
	  	  
	  //screen capture file name convercation 
	  public final static String DESKTOP_SCREEN_RECORDER=PROJECT_NAME+"_"+new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime())+".mp4";
	  //JDBC settings
	  public static String DB_DRIVER_NAME="com.mysql.jdbc.Driver";
	  public static String DB_URL="jdbc:mysql://HUCHAN3.asiapacific.hpqcorp.net:3306/qtpresult";
	  public static String DB_USER="root";
	  public static String DB_PASSWORD="root";
	    
	  //selenium page and waiting settings 
	  public static int PAGE_LOADING_TIME=150;
	  public static int WEBELEMENT_LOADING_TIME=20;
	  public static int JS_TIMEOUT=140;
	  public static int WINDOW_LOAD_TIME=30;
	  
	  //
		public static int DEFAULT_IMPLICIT_WAITTIME=20;
	// identifier strategies
		
		public static String CSS="css";
		public static String ID="id";
		public static String NAME="name";
		public static String TAG_NAME="tagname";
		public static String CLASS_NAME="classname";
		public static String LINK_TEXT="linktxt";
		public static String PARTIAL_LINK_TEXT="prtlinktxt";
		public static String XPATH="xpath";
		
	  
	  //script started time
	  public static String CURRENT_TIME=TimeUtils.getCurrentTime(Calendar.getInstance().getTime());
	  
	  //browser download folder settings
	  public static String BROWSER_DOWNLOAD_DIR=PROJECT_DIR+"\\Downloads\\";
	    
}
