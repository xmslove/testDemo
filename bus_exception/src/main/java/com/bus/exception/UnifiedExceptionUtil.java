/**
 * Alibaba.com Inc.
 * Copyright (c) 1999-2016 All Rights Reserved.
 */
package com.bus.exception;

import java.util.Map;

import com.bus.exception.constant.ExceptionLevel;

/**
 * 统一异常util
 *xms
 */
public class UnifiedExceptionUtil {

    /**
     * 抛出轻微等级异常
     *
     * @param errorDefinition 必填，异常错误message、错误code
     * @param businessModule  必填，异常所属模块
     * @param context         选填，异常上下文，可以设置业务关键字
     * @param cause           选填
     * @throws UnifiedException
     */
    public static void throwSlightException(String message,String code, String businessModule,
                                            Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.SLIGHT, message,code, businessModule, context, cause);
    }

    /**
     * 抛出一般等级异常
     *
     * @param errorDefinition 必填，异常错误message、错误code
     * @param businessModule  必填，异常所属模块
     * @param context         选填，异常上下文，可以设置业务关键字
     * @param cause           选填
     * @throws UnifiedException
     */
    public static void throwCommonException(String message,String code, String businessModule,
                                            Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.COMMON, null,code, null, null, cause);
    }

    /**
     * 抛出严重等级异常
     *
     * @param errorDefinition 必填，异常错误message、错误code
     * @param businessModule  必填，异常所属模块
     * @param context         选填，异常上下文，可以设置业务关键字
     * @param cause           选填
     * @throws UnifiedException
     */
    public static void throwSeriousException(String message,String code, String businessModule,
                                             Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.SERIOUS, message,
        		code, businessModule, context, cause);
    }
}
