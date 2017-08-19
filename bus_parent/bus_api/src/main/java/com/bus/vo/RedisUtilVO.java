package com.bus.vo;

import java.io.Serializable;

/**
 * 
 * @author xms
 * @version 2016年10月19日
 */
public class RedisUtilVO implements Serializable
{
    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = -9038779116303326399L;
    private String key;
    private Object obj;

    public RedisUtilVO(){
        
    }
    
    public RedisUtilVO(String key,Object obj){
        super();
        this.key = key ; 
        this.obj = obj ; 
        }
    
    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }

}
