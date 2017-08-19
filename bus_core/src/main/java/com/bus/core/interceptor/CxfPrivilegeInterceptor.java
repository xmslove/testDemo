package com.bus.core.interceptor;



import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


/**
 * 
 * @author xms
 *
 */
public class CxfPrivilegeInterceptor extends AbstractPhaseInterceptor<Message>{
	
	public CxfPrivilegeInterceptor() {
		super(Phase.WRITE);
	}

	public void handleMessage(Message message) throws Fault {
		
	}
	
}
