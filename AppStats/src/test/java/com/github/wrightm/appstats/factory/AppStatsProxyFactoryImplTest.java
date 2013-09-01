package com.github.wrightm.appstats.factory;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.junit.Test;

import com.github.wrightm.appstats.interceptor.AOPInterceptor;

public class AppStatsProxyFactoryImplTest {

	public class DummyInterceptor implements AOPInterceptor {

		public void before(Object target, Method method, Object[] args) {
			// TODO Auto-generated method stub
			
		}

		public void after(Object target, Method method, Object[] args) {
			// TODO Auto-generated method stub
			
		}

		public void afterThrowing(Object target, Method method, Object[] args,
				Throwable exception) {
			// TODO Auto-generated method stub
			
		}

		public void afterFinally(Object target, Method method, Object[] args) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Test
	public void test() {

		AOPInterceptor interceptor = new DummyInterceptor();
		DummyClass dummyClass = new DummyClass();	
		
		AppStatsProxyFactory appStatProxy = new AppStatsProxyFactoryImpl();
		DummyClass hello = appStatProxy.creatProxy(dummyClass.getClass(), interceptor);
		assertNotNull(hello);
	}

}
