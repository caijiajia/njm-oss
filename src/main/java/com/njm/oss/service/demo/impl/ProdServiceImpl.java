package com.njm.oss.service.demo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.njm.oss.common.util.Pager;
import com.njm.oss.dao.demo.ProdDao;
import com.njm.oss.model.demo.Prod;
import com.njm.oss.service.common.BaseService;
import com.njm.oss.service.demo.ProdService;

@Service
public class ProdServiceImpl extends BaseService implements ProdService {

	@Resource
	private ProdDao prodDao;

	/*
	 * add insert
	 * query select
	 * modify update
	 * remove delete
	 */
	@Override
	public Prod getInfoById(int id) {
		return prodDao.selectInfoById(id);
	}

	@Override
	public Prod getInfoByProdId(String productId) {
		return prodDao.selectInfoByProdId(productId);
	}

	@Override
	public List<Prod> queryListByCateId(int categoryId) {
		return prodDao.selectListByCateId(categoryId);
	}

	@Override
	public Map<String, Object> queryListByPageParam(Map<String, Object> param) {
		
		log.debug("调试输出----->传入参数，param :" + param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("info", "查询失败");
		
		boolean needCount = (Boolean) param.get("needCount");

		Pager page = new Pager();
		int pageNum = (Integer) param.get("pageNum");
		int pageSize = (Integer) param.get("pageSize");
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);

		PageHelper.startPage(pageNum, pageSize, needCount);
		List<Prod> prods = prodDao.selectListByPageParam4(param);
		result.put("prods", prods);
		log.debug("调试输出----->查询结果，产品列表 prods :" + JSONObject.toJSONString(prods));

		if (needCount) {
			long total = ((Page<Prod>) prods).getTotal();
			log.debug("调试输出----->查询结果，产品记录总条数 :" + total);
			page.setTotal(total);
			page.calculatePageCount(total, pageSize);
			result.put("page", page);
		}
		log.debug("调试输出----->查询结果，分页信息 prods :" + JSONObject.toJSONString(page));

		log.debug("调试输出----->查询结果，result :" + JSONObject.toJSONString(result));

		result.put("code", 1);
		result.put("info", "查询成功");

		return result;
	}
}
