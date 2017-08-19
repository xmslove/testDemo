package com.bus.util;

import com.bus.exception.UnifiedException;
import com.bus.exception.UnifiedExceptionUtil;
/**
 * 构建异常上下文
 * @author xms
 *
 */
public class ExceptionMsgUtil {
	
	public static void setError(int leave ,String code,Throwable cause) throws UnifiedException{
		switch (leave) {
		case 1:
			UnifiedExceptionUtil.throwSlightException(null, code, null, null, cause);
			break;
		case 2:
			UnifiedExceptionUtil.throwCommonException(null, code, null, null, cause);
			break;
		case 3:
			UnifiedExceptionUtil.throwSeriousException(null, code, null, null, cause);
	break;
		}
	};

}
