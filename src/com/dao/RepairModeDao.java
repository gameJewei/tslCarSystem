package com.dao;

import com.entity.RepairMode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepairModeDao {
	public void addOrderInfo(RepairMode oi);
	public List<RepairMode> findAllOrder();
	public void delOrderInfo(String id);
	public void changStatus(@Param("id")String id,@Param("status")Integer status);
	public List<RepairMode> findOrder(String user_id);
	public List<RepairMode> searchOrderInfo(Map<String,Object> params);
	public List<RepairMode> findByStatus(Integer status);
	public List<RepairMode> findBySort();
}
