package com.bus.vo;

import java.io.Serializable;

/**
 * 
 * @author xms
 *
 */
public class AppVersionVO implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2313606695767330258L;

	private Integer id ;
    
    private String version ;
    
    private Integer updateStatus;
    
    private String downloadUrl;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public Integer getUpdateStatus()
    {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus)
    {
        this.updateStatus = updateStatus;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }
    
}
