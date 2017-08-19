package com.bus.dao;


import java.util.List;










import org.apache.ibatis.annotations.Param;

import com.bus.vo.IllegalMsgVO;
import com.bus.vo.LineVO;
import com.bus.vo.MoveLineMsgVO;
import com.bus.vo.PhotoVO;

public interface IIllegalDao {

	List<IllegalMsgVO> isIllegal(@Param("imsi")String imsi);

	void moveLine(@Param("imsi")String imsi, @Param("MoveLineList")List<MoveLineMsgVO> mm);

	List<LineVO> getAllLine(@Param("imsi")String imsi,@Param("startDate")String startDate);

	void uploadPhoto(@Param("imsi")String imsi, @Param("key")String key);

	PhotoVO getPhoto(@Param("imsi")String imsi);

	void uploadVide(@Param("imsi")String imsi, @Param("key")String key);

	String getVoide(@Param("imsi")String imsi);

	void removeIllegal(@Param("id")Integer id);

}
