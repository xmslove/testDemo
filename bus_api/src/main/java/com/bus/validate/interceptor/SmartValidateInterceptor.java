package com.bus.validate.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.bus.validate.SmartValidate;

/**
 * 拦截验证
 * @author xms
 *
 */
public class SmartValidateInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		Object[] arguments = invocation.getArguments();
		int k = 0;
		int length = arguments.length;
		while(k < length) {
			Object argument = arguments[k];
			//验证实体类
			SmartValidate.validate(argument);
			//验证字段
			SmartValidate.validate(invocation.getMethod(), k, argument);
			k ++;
		}
		return invocation.proceed();
	}
    
}
