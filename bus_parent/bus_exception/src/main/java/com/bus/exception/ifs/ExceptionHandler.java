package com.bus.exception.ifs;

import com.bus.exception.UnifiedException;
import com.bus.exception.handler.ExceptionHandlerChain;
import com.bus.exception.vo.GlobalInfo;

/**
 * 
 * <Description>异常处理接口<br> 
 *  
 * @author xms<br>
 * @version 1.0<br>
 * @taskId <br>
 */
public interface ExceptionHandler extends HandlerSupport{

    /**
     * 
     * Description:处理异常<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param ue 统一异常
     * @param gi 异常处理全局信息
     * @param ehc 异常处理责任链<br>
     */
    public void handle(UnifiedException ue,GlobalInfo gi,ExceptionHandlerChain ehc);
}
