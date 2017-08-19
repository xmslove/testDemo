package com.bus.util;

import java.util.Random;

/**
 * 创建时间�?016�?�?3�?下午2:48:48
 * 
 * 随机字符�?
 * 
 * @author xms
 * @version 2.2
 */

public class RandomUtil {

	public static final String ALL_CHAR = "-_#&$@+-*/%()[]0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String NUMBER_CHAR = "0123456789";

	public static final String SPECIAL_CHAR = "-_#&$@+-*/%()[]";
	
	public static final String LETTER_NUMBER_CHAR = LETTER_CHAR + NUMBER_CHAR;

	/**
	 * 返回�?��定长的随机字符串
	 * 
	 * @param chars
	 *            模型�?
	 * @param length
	 *            随机长度
	 * @return
	 */
	public static String randomString(String chars, int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回�?��定长的随机字符串字母全部大写
	 * 
	 * @param chars
	 *            模型�?
	 * @param length
	 *            随机字符串长�?
	 * @return 随机字符�?
	 */
	public static String randomLowerString(String chars, int length) {
		return randomString(chars, length).toLowerCase();
	}

	/**
	 * 返回�?��定长的随机字符串字母全部小写
	 * 
	 * @param chars
	 *            模型�?
	 * @param length
	 *            随机字符串长�?
	 * @return 随机字符�?
	 */
	public static String randomUpperString(String chars, int length) {
		return randomString(chars, length).toLowerCase();
	}

	/**
	 * 生成�?��定长的纯0字符�?
	 * 
	 * @param length
	 *            字符串长�?
	 * @return �?字符�?
	 */
	public static String randomZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

}
