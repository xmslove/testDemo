package com.bus.exception.handler;

import com.bus.exception.UnifiedException;
import com.bus.exception.handler.repository.ExceptionHandlerRepository;
import com.bus.exception.vo.GlobalInfo;

import javax.annotation.Resource;

import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * 
 * <Description>mongo db异常处理器<br> 
 *  
 * @author xms<br>
 * @version 1.0<br>
 * @taskId <br>
 */
public class MongoDBExceptionHandler extends AbsExceptionHandler{
    //支持的异常等级 @see ExceptionLevel
    private int level = 2;
    
    @Resource
    private ExceptionHandlerRepository exceptionHandlerRepository;
    
    @Override
    public boolean supports(UnifiedException ue) {
        if (ue.getLevel().getValue() <= level) {
            return true;
        } else {
            return false;
        }
    };

    @Override
    protected void doHandle(UnifiedException ue, GlobalInfo gi) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ue.printStackTrace(printWriter);
        String exception = ue.getMessage() + stringWriter.toString();
        ExceptionHandlerRepository.ExceptionRecord er = new ExceptionHandlerRepository.ExceptionRecord(gi.getSystemCode(), ue
                .getLevel().getDescription(), ue.getErrorMessage(), ue.getErrorCode(), exception);
        exceptionHandlerRepository.save(er);
    };

    /** 
     * get level
     * @return Returns the level.<br> 
     */
    public int getLevel() {
        return level;
    };

    /** 
     * set level
     * @param level The level to set. <br>
     */
    public void setLevel(int level) {
        this.level = level;
    };
}
