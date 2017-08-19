package com.bus.exception.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import com.bus.exception.UnifiedException;
import com.bus.exception.api.ExceptionManager;

/**
 * 
 * <Description>切面统一异常处理,仅处理UnifiedException,若使用该切面配置，请在业务代码里throw出UnifiedException<br> 
 *  基于spring的切面异常处理
 *  xms
 * @version 1.0<br>
 * @taskId <br>
 */
public class UnifiedExceptionThrowsAdvice implements ThrowsAdvice {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(UnifiedExceptionThrowsAdvice.class);
    /**
     * 异常处理器
     */
  
    private ExceptionManager exceptionManager;

    /**
     *
     * Description:对于统一异常的处理<br> 
     *
     * @author xms<br>
     * @taskId <br>
     * @param method 错误方法
     * @param args   错误参数 
     * @param target 错误的类
     * @param ex     异常
     * @throws Throwable <br>
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
    	Map<String,Object> errorContent = new HashMap<String,Object>();
    	errorContent.put("parameter", args);
    	errorContent.put("target", target.toString());
    	
        if (ex instanceof UnifiedException) {
            UnifiedException ue = (UnifiedException) ex;
            
            ue.setBusinessModule(method.getName());
            ue.setErrorMessage(ex.getMessage());
            ue.setContext(errorContent);
            
            exceptionManager.publish(ue);
        } else {	
            logger.error("unkown exception", ex);
        }
    }

    /**
     * get exceptionManager
     * @return Returns the exceptionManager.<br> 
     */
    public ExceptionManager getExceptionManager() {
        return exceptionManager;
    }
    /** 
     * set exceptionManager
     * @param exceptionManager The exceptionManager to set. <br>
     */
    public void setExceptionManager(ExceptionManager exceptionManager) {
        this.exceptionManager = exceptionManager;
    }
    
}
