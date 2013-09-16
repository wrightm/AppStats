package com.github.wrightm.appstats.factory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import com.github.wrightm.appstats.interceptor.MethodTypeInterceptor;
import com.github.wrightm.appstats.invocation.handler.AppStatsMethodInterceptor;

public class AppStatsProxyFactoryImpl implements AppStatsProxyFactory {
	
	@SuppressWarnings("unchecked")
	public <T> T creatProxy(Class<T> clazz, MethodTypeInterceptor interceptor)
	{
		MethodInterceptor methodInterceptor = new AppStatsMethodInterceptor(interceptor);
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(methodInterceptor);
		
		return (T)enhancer.create();
	}
}
