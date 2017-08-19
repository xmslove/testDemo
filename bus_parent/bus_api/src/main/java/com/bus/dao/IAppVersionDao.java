package com.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bus.vo.AppVersionVO;

/**
 * 
 * @author xms
 *
 */
public interface IAppVersionDao {
    
	List<AppVersionVO> getAppVersion(@Param("version")String version);

}
