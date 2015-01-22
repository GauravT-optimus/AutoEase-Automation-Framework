package com.autoease.framework.utilities;

/**
* @ClassName: NumberUtils
* @Description: TODO
* @author alterhu2020@gmail.com
* @date Apr 9, 2014 5:56:41 PM
* 
*/

public class NumberUtils {

	
	/** 
	* @Title: getRandomNumber 
	* @Description: TODO
	* @author alterhu2020@gmail.com
	* @param @param min
	* @param @param max
	* @param @return    
	* @return int    return type
	* @throws 
	*/ 
	
	public static int getRandomNumber(int min,int max) {
		int returnvalue = min + (int) (Math.random() * max);
		return returnvalue;
	}
}
