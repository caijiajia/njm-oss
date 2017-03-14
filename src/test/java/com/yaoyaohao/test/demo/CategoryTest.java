package com.yaoyaohao.test.demo;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.njm.oss.model.demo.Cate;
import com.njm.oss.service.demo.CateService;

public class CategoryTest {
	
	private Logger log = Logger.getLogger(this.getClass());

	private CateService categoryBean;

	@Before
	public void before() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring/applicationContext.xml"});
		categoryBean = (CateService) context.getBean("cateServiceImpl");
	}

	@Test
	public void getInfo() {
		
		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码查询分类树信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		
		int categoryId = 508;
		log.debug("调试输出----->分类编码 :" + categoryId);
		log.error("调试输出----->分类编码 :" + categoryId);
		
		Cate category = categoryBean.queryInfoByCateId(categoryId);

		log.debug("调试输出----->查询出分类信息 :" + JSONObject.toJSONString(category));
		log.error("调试输出----->查询出分类信息 :" + JSONObject.toJSONString(category));
	}

	@Test
	public void getList() {
		
		log.debug("");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码查询子分类树列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");
		
		int categoryId = 1;
		log.debug("调试输出----->分类编码 :" + categoryId);
		
		List<Cate> categorys = categoryBean.queryListByParentId(categoryId);
		
		log.debug("调试输出----->查询出分类树列表 :" + JSONArray.toJSONString(categorys));
	}
}
