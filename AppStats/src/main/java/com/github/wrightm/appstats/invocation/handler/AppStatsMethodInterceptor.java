package com.github.wrightm.appstats.invocation.handler;

import java.lang.reflect.Method;

import com.github.wrightm.appstats.interceptor.MethodTypeInterceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AppStatsMethodInterceptor implements MethodInterceptor{

	private MethodTypeInterceptor interceptor;
	
	public AppStatsMethodInterceptor(MethodTypeInterceptor interceptor)
	{
		this.interceptor = interceptor;
	}
	
	public Object intercept(Object target, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		try
		{
			interceptor.intercept(target, method, args);
			Object retVal = methodProxy.invokeSuper(target, args);
			return retVal;
		}
		catch(Throwable err)
		{
			throw err;
		}
	}
}
