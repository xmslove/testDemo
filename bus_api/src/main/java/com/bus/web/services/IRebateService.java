package com.bus.web.services;

import java.util.List;

import com.bus.vo.RebateVO;

public interface IRebateService {
    /**
     * 查询销售返利集合
     * @return
     */
	List<RebateVO> findCardList();
    /**
     * 更新销售返利状态
     * @param list
     */
	void updateRebateStatus(List<String> list);
    /**
     * 查询商家当月下面的销售中的续费卡数
     * @return
     */
	List<RebateVO> findStaffList();
	/**
	 * 查询商家下面的销售数量
	 * @param mchNo
	 * @return
	 */
	int staffNum(String mchNo);
	/**
	 * 查询商家的openid
	 * @param mchNo
	 * @return
	 */
	String findMch(String mchNo);
	/**
	 * 更新商家返利状态
	 * @param list
	 */
	void updateMchRebateStatus(List<String> list);

}
