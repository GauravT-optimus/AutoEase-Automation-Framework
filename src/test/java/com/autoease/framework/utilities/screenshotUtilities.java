package com.autoease.framework.utilities;

import static java.io.File.separator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.autoease.framework.WebDriverGenerator;

public class screenshotUtilities {
	
	//WebDriver driver=WebDriverGenerator.getWebDriver();
	public  static void captureErrorScreenshot(){
		String filename = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(Calendar
						.getInstance().getTime()) + ".png";
		//logger.debug("we met the error,we will generate a screenshot file for this error, file name is "
			//	+ filename);

		//File scrFile = ((TakesScreenshot) new Augmenter().augment( driver ))
	    File scrFile = ((TakesScreenshot) WebDriverGenerator.getWebDriver())
				.getScreenshotAs(OutputType.FILE);
		try {
				//Logger.debug("the source screenshot file is :"+ scrFile.getCanonicalPath());
					
				//String path = new File(".").getAbsolutePath();
				String screenshotpath = System.getProperty("user.dir");
				//logger.debug("the saved screenshot path is :" + screenshotpath);
				
				FileUtils.copyFile(scrFile, new File(screenshotpath + "\\test-output\\screenshots"+ separator + filename));
				//logger.debug("the screenshot file saved in this file path:"
					//	+ screenshotpath + "screenshot" + separator + filename);
         }
		catch (IOException e) {
			// TODO Auto-generated catch block
			//logger.error("the saved screenshot met the ioexception :" +e.getMessage());
			
		}
	
		
 }
}
