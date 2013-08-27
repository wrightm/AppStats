package com.github.wrightm.appstats.interceptor;

import java.lang.reflect.Method;

public interface AOPInterceptor 
{
	public abstract void 		 before(Object target, Method method, Object[] args);
	public abstract void 		 after(Object target, Method method, Object[] args);
	public abstract void 		 afterThrowing(Object target, Method method, Object[] args, Throwable exception);
	public abstract void	 	 afterFinally(Object target, Method method, Object[] args);
}
