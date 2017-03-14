package com.yaoyaohao.test.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.njm.oss.dao.demo.ProdDao;
import com.njm.oss.model.demo.Prod;
import com.njm.oss.service.demo.ProdService;

public class ProductTest {

	private Logger log = Logger.getLogger(this.getClass());

	private ProdDao prodDao;
	private ProdService prodBean;

	@Before
	public void before() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring/applicationContext.xml" });
		prodDao = (ProdDao) context.getBean("prodDao");
		prodBean = (ProdService) context.getBean("prodServiceImpl");
	}

	@Test
	public void getInfo() {

		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据产品编码查询产品信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		String prodId = "434";
		log.debug("调试输出----->产品编码 :" + prodId);

		Prod prod = prodDao.selectInfoById(434);

//		Prod prod = prodDao.selectInfoByProdId(prodId);

		log.debug("调试输出----->查询出分类信息 :" + JSONObject.toJSONString(prod));
	}

	@Test
	public void getList() {

		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		int cateId = 508;
		log.debug("调试输出----->分类编码 :" + cateId);

		List<Prod> prods = prodDao.selectListByCateId(cateId);

		log.debug("调试输出----->查询出分类树列表 :" + JSONArray.toJSONString(prods));
	}

	@Test
	public void getListByPageParam1() {

		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码分页查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		int cateId = 508;
		int start = 0;
		int size = 3;
		log.debug("调试输出----->分类编码 :" + cateId);
		log.debug("调试输出----->开始位置 :" + start);
		log.debug("调试输出----->查询数量 :" + size);

		List<Prod> prods = prodDao.selectListByPageParam1(cateId, start, size);

		log.debug("调试输出----->查询出分类树列表 :" + JSONArray.toJSONString(prods));
	}

	@Test
	public void getListByPageParam2() {

		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码分页查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		int cateId = 508;
		int start = 0;
		int size = 3;
		log.debug("调试输出----->分类编码 :" + cateId);
		log.debug("调试输出----->开始位置 :" + start);
		log.debug("调试输出----->查询数量 :" + size);

		List<Prod> prods = prodDao.selectListByPageParam2(cateId, start, size);

		log.debug("调试输出----->查询出分类树列表 :" + JSONArray.toJSONString(prods));
	}

	@Test
	public void getListByPageParam3() {

		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码分页查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		int cateId = 508;
		int start = 0;
		int size = 3;
		log.debug("调试输出----->分类编码 :" + cateId);
		log.debug("调试输出----->开始位置 :" + start);
		log.debug("调试输出----->查询数量 :" + size);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("purchase_id", cateId);
		param.put("start", start);
		param.put("size", size);

		List<Prod> prods = prodDao.selectListByPageParam3(param);

		log.debug("调试输出----->查询出分类树列表 :" + JSONArray.toJSONString(prods));
	}

	@Test
	public void getPageList() {

		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码分页查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		int cateId = 508;
		boolean needCount = true;
		int pageNum = 1;
		int pageSize = 3;
		log.debug("调试输出----->分类编码 :" + cateId);
		log.debug("调试输出----->开始位置 :" + pageNum);
		log.debug("调试输出----->查询数量 :" + pageSize);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("needCount", needCount);
		param.put("purchase_id", cateId);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);

		Map<String, Object> result = prodBean.queryListByPageParam(param);

		log.debug("调试输出----->查询出产品列表 :" + JSONArray.toJSONString(result));

		needCount = false;
		param.put("needCount", needCount);
		param.put("cateId", cateId);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		Map<String, Object> result2 = prodBean.queryListByPageParam(param);

		log.debug("调试输出----->查询出产品列表 :" + JSONArray.toJSONString(result2));

	}
}
