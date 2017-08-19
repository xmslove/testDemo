package com.bus.web.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.dao.IStaffRebateDao;
import com.bus.vo.RebateVO;
import com.bus.web.services.IRebateService;
/**
 * 
 * @author xms
 *
 */

@Service("staffRebateService")
public class StaffRebateServiceImpl implements IRebateService{
	@Resource 
	private IStaffRebateDao staffRebateDao;

	@Override
	public List<RebateVO> findCardList() {
		return staffRebateDao.findCardList();
	}

	@Override
	@Transactional
	public void updateRebateStatus(List<String> idsList) {
		for (String id : idsList) {
			staffRebateDao.updateRebateStatus(id);
		}
		
	}

	@Override
	public List<RebateVO> findStaffList() {
		return staffRebateDao.findStaffList();
	}

	@Override
	public int staffNum(String mchNo) {
		return staffRebateDao.staffNum(mchNo);
	}

	@Override
	public String findMch(String mchNo) {
		return staffRebateDao.findMch(mchNo);
	}

	@Override
	public void updateMchRebateStatus(List<String> idsList) {
		for (String id : idsList) {
			staffRebateDao.updateMchRebateStatus(id);
		}
	}

}
