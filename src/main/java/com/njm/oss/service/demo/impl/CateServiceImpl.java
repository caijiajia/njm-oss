package com.njm.oss.service.demo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njm.oss.dao.demo.CateDao;
import com.njm.oss.model.demo.Cate;
import com.njm.oss.service.demo.CateService;

@Service
public class CateServiceImpl implements CateService {
	
	@Resource
	private CateDao cateDao;

	@Override
	public Cate queryInfoCateById(int id) {
		return cateDao.selectInfoById(id);
	}

	@Override
	public Cate queryInfoByCateId(int categoryId) {
		
		return cateDao.selectInfoByCateId(categoryId);
	}

	@Override
	public List<Cate> queryListByParentId(int categoryId) {
		return cateDao.selectListByParentId(categoryId);
	}

}
