package com.github.wrightm.appstats.factory;

import com.github.wrightm.appstats.interceptor.AOPInterceptor;

public interface AppStatsProxyFactory {
	public <T> T creatProxy(Class<T> clazz, AOPInterceptor interceptor);
}
