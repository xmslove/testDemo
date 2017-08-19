package com.bus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bus.vo.RebateVO;

/**
 * 
 * @author xms
 *
 */
public interface IStaffRebateDao {

	List<RebateVO> findCardList();

	void updateRebateStatus(@Param("id")String id);

	List<RebateVO> findStaffList();

	int staffNum(@Param("mchNo")String mchNo);

	String findMch(@Param("mchNo")String mchNo);

	void updateMchRebateStatus(@Param("id")String id);

}
