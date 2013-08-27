package com.github.wrightm.appstats.invocation.handler;

import java.lang.reflect.Method;

import com.github.wrightm.appstats.interceptor.AOPInterceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AppStatsMethodInterceptor implements MethodInterceptor{

	private AOPInterceptor interceptor;
	
	public AppStatsMethodInterceptor(AOPInterceptor interceptor)
	{
		this.interceptor = interceptor;
	}
	
	public Object intercept(Object target, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		try
		{
			interceptor.before(target, method, args);
			Object retVal = methodProxy.invokeSuper(target, args);
			interceptor.after(target, method, args);
			return retVal;
		}
		catch(Throwable err)
		{
			interceptor.afterThrowing(target, method, args, err);
			throw err;
		}
		finally
		{
			interceptor.afterFinally(target, method, args);
		}
	}
}
