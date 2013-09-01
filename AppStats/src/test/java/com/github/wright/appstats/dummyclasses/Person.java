package com.github.wright.appstats.dummyclasses;

import com.github.wrightm.appstats.annotations.InvokeIntercept;


public interface Person {
	
	@InvokeIntercept
	void setName(String name);
	
	
	String getName();
	
}
