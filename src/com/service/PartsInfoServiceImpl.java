package com.service;

import com.dao.PartsInfoDao;
import com.entity.PartsInfo;
import com.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PartsInfoServiceImpl implements PartsInfoService{
	@Resource
	PartsInfoDao dao;

	@Override
	public Result buyParts(String id) {
		// TODO Auto-generated method stub
		dao.buyParts(id);
		return new Result("0", "�������ɹ�", null);
	}

	@Override
	public Result findAllParts() {
		// TODO Auto-generated method stub
		List<PartsInfo> list=dao.findAllParts();
		return new Result("0", "�����Ϣ���سɹ�", list);
	}

	@Override
	public Result useParts(String id) {
		// TODO Auto-generated method stub
		dao.useParts(id);
		return new Result("0", "�����ʹ��", null);
	}
	
}
