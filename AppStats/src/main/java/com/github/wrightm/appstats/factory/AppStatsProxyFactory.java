package com.github.wrightm.appstats.factory;

import com.github.wrightm.appstats.interceptor.MethodTypeInterceptor;

public interface AppStatsProxyFactory {
	public <T> T creatProxy(Class<T> clazz, MethodTypeInterceptor interceptor);
}
