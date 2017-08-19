package com.bus.exception.handler;

import com.bus.exception.UnifiedException;
import com.bus.exception.constant.ExceptionLevel;
import com.bus.exception.vo.GlobalInfo;
import com.bus.mail.Email;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.annotation.Resource;

/**
 * <Description>打印logback日志文件<br>
 * 
 * @author xms<br>
 * @version 1.0<br>
 * @taskId <br>
 */
public class LogBackExceptionHandler extends AbsExceptionHandler {
    // logger
    private Logger LOG = LoggerFactory.getLogger(LogBackExceptionHandler.class);
    
    /**支持的异常等级
    @see ExceptionLevel
    **/
    private int level = 3;
    
    @Resource
    private Email mail;
    
    @Override
    public boolean supports(UnifiedException ue) {
        return ue.getLevel().getValue() <= level;
    }

    @Override
    protected void doHandle(UnifiedException ue, GlobalInfo gi) {
        Map<String,Object> context = ue.getContext();
        String errorContext = null == context ? "" : JSONObject.valueToString(context);
        LOG.error(
                "system:" + gi.getSystemCode() + " module:" + ue.getBusinessModule() + " error:"
                        + ue.getErrorMessage() + " context:" + errorContext, ue);
        
        String  mailContent = "<html><head></head><body><h1>发生错误的类和参数:</h1><h1>"+errorContext+"</h1>"+
                              "<h1>发生错误的方法:</h1><h1>"+ue.getBusinessModule()+"</h1>"+
    		                  "<h1>发生错误的级别:</h1><h1>"+ue.getLevel().getValue()+"</h1>"+
    		                  "<h1>发生错误的code:</h1><h1>"+ue.getErrorCode()+"</h1>"+
    		                  "<h1>发生错误的信息:</h1><h1>"+ue.getErrorMessage()+"</h1>"+
    		                  "<h1>发生错误异常类型:</h1><h1>"+ue.getCause()+"</h1></body></html>";
        
        mail.sendMail("aicar系统异常",mailContent,"xumengsi@xccnet.com");
    }

    /** 
     * get level
     * @return Returns the level.<br> 
     */
    public int getLevel() {
        return level;
    }

    /** 
     * set level
     * @param level The level to set. <br>
     */ 
    public void setLevel(int level) {
        this.level = level;
    }

	/*public Email getMail() {
		return mail;
	}

	public void setMail(Email mail) {
		this.mail = mail;
	}*/

}
