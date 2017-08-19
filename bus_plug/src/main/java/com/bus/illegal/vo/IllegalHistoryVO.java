package com.bus.illegal.vo;

import java.io.Serializable;
/**
 * 
 * @author xms
 *
 */
public class IllegalHistoryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849293071660213127L;
	/**
	 * 违章时间
	 */
	private String date;
	/**
	 * 违章地点
	 */
	private String area;
	/**
	 * 	违章行为
	 */
	private String act;
	/**
	 * 	违章代码(仅供参考，不一定有值)
	 */
	private String code;
	/**
	 * 	违章扣分(仅供参考，不一定有值)
	 */
	private String fen;
	/**
	 * 	违章罚款(仅供参考，不一定有值)
	 */
	private String money;
	/**
	 * 	是否处理,1处理 0未处理 空未知
	 */
	private String handled;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFen() {
		return fen;
	}
	public void setFen(String fen) {
		this.fen = fen;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getHandled() {
		return handled;
	}
	public void setHandled(String handled) {
		this.handled = handled;
	}
	
}
