package com.bus.tencent.vo;

/**
 * 单个设备推送实体类
 * @author xms
 *
 */
public class PushVO extends BaseVO {

	/**
	 * 针对某一设备推送，token是设备的唯一识别 ID
	 */
	private String device_token;
	/**
	 * 消息类型：1：通知 2：透传消息。iOS平台请填0
	 */
	private String message_type;
	/**
	 * {"content":"this is content","title":"this is title", "vibrate":1}
	 */
	private String message;
	/**
	 * 消息离线存储时间（单位为秒），最长存储时间3天。若设置为0，则使用默认值（3天）
	 */
	private String expire_time;
	/**
	 * 指定推送时间，格式为year-mon-day hour:min:sec 若小于服务器当前时间，则会立即推送
	 */
	private String send_time;
	/**
	 * 0表示按注册时提供的包名分发消息；1表示按access id分发消息，所有以该access id成功注册推送的app均可收到消息。本字段对iOS平台无效
	 */
	private String multi_pkg;
	/**
	 * 向iOS设备推送时必填，1表示推送生产环境；2表示推送开发环境。推送Android平台不填或填0
	 */
	private String environment;
	/**
	 * 针对某一账号推送，帐号可以是qq号，邮箱号，openid，手机号等各种类型
	 */
	private String account;
	/**
     *Json数组格式，每个元素是一个account，string类型，单次发送account不超过100个。例：[“account1”,”account2”,”account3”]
	 */
	private String account_list;
	/**
	 * 创建批量推送消息 接口的返回值中的 push_id
	 */
	private String push_id;
	
	
	public String getDevice_token() {
		return device_token;
	}
	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getMulti_pkg() {
		return multi_pkg;
	}
	public void setMulti_pkg(String multi_pkg) {
		this.multi_pkg = multi_pkg;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccount_list() {
		return account_list;
	}
	public void setAccount_list(String account_list) {
		this.account_list = account_list;
	}
	public String getPush_id() {
		return push_id;
	}
	public void setPush_id(String push_id) {
		this.push_id = push_id;
	}
}
