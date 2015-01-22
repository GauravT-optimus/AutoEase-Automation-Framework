package com.autoease.framework.WebAppSteps;

public class Reusable {
	
	public AssertionsSteps as;
	public BrowserRelated br;
	public InputSteps is;
	public TypeAndClickSteps tf;
	

	
	
	public Reusable(){
		System.out.println("reusable contructor");
		 as=new AssertionsSteps();
		 br=new BrowserRelated();
		 is=new InputSteps();
		 tf=new TypeAndClickSteps();
		
	}
}
