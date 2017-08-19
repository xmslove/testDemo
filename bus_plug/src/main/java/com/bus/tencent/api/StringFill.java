package com.bus.tencent.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 占位符替换
 * @author xms
 *
 */

public class StringFill {
	
	public static String fillStringByArgs(String str,String[] arr){
        Matcher m=Pattern.compile("\\{(\\d)\\}").matcher(str);
        while(m.find()){
            str=str.replace(m.group(),arr[Integer.parseInt(m.group(1))]);
        }
        return str;
    }

}
