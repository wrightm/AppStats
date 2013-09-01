package com.github.wright.appstats.dummyclasses;

import com.github.wrightm.appstats.annotations.InvokeIntercept;

public class Teacher implements Person {

	@InvokeIntercept
	public String getSubject() {
		return null;
	}
	
	public void setName(String name) {
		
	}

	public String getName() {
		return null;
	}
	
	

}
