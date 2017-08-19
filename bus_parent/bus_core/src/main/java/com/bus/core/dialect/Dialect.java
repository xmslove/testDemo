package com.bus.core.dialect;
/**
 * 
 * @author xms
 *
 */
public class Dialect {
	
    public boolean supportsLimit(){
    	return false;
    }

    public boolean supportsLimitOffset() {
    	return supportsLimit();
    }
    
    /**
     * 灏唖ql鍙樻垚鍒嗛〉sql璇彞,鐩存帴浣跨敤offset,limit鐨勫�浣滀负鍗犱綅绗�</br>
     * 婧愪唬鐮佷负: getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit))
     */
    public String getLimitString(String sql, int offset, int limit) {
    	return getLimitString(sql,offset,Integer.toString(offset),limit,Integer.toString(limit));
    }
    
    /**
     * 灏唖ql鍙樻垚鍒嗛〉sql璇彞,鎻愪緵灏唎ffset鍙妉imit浣跨敤鍗犱綅绗�placeholder)鏇挎崲.
     * <pre>
     * 濡俶ysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 灏嗚繑鍥�
     * select * from user limit :offset,:limit
     * </pre>
     * @return 鍖呭惈鍗犱綅绗︾殑鍒嗛〉sql
     */
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit,String limitPlaceholder) {
    	throw new UnsupportedOperationException("paged queries not supported");
    }
    
}
