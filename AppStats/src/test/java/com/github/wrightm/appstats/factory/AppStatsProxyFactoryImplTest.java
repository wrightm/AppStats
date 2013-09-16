package com.github.wrightm.appstats.factory;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.junit.Test;

import com.github.wrightm.appstats.interceptor.MethodTypeInterceptor;

public class AppStatsProxyFactoryImplTest {

	public class DummyInterceptor implements MethodTypeInterceptor {

		public void intercept(Object target, Method method, Object[] args) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Test
	public void test() {

		MethodTypeInterceptor interceptor = new DummyInterceptor();
		DummyClass dummyClass = new DummyClass();	
		
		AppStatsProxyFactory appStatProxy = new AppStatsProxyFactoryImpl();
		DummyClass hello = appStatProxy.creatProxy(dummyClass.getClass(), interceptor);
		assertNotNull(hello);
	}

}
