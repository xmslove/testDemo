package com.bus.tencent.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class ResultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6349257996453326590L;
	
	/**
	 * 返回码
	 */
	private String ret_code;
	/**
	 * 请求出错时的错误信息
	 */
	private String err_msg;
	/**
	 * 	
	 *	请求正确时，若有额外数据要返回
	 *  ，则结果封装在该字段的json中。若无额外数据，则可能无此字段
	 */
	private ResultJsonVO result;
	
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public ResultJsonVO getResult() {
		return result;
	}
	public void setResult(ResultJsonVO result) {
		this.result = result;
	}
	
}
