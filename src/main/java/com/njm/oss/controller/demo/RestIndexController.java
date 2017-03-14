/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.njm.oss.controller.demo;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;
import com.njm.framework.data.DataMap;
import com.njm.framework.data.DatasetList;
import com.njm.framework.data.IData;
import com.njm.framework.data.IDataset;
import com.njm.oss.common.util.EhcacheUtil;
import com.njm.oss.service.common.ContentService;

/**
 * Copyright: Copyright (c) 2015 njm.com
 * 
 * @className: IndexRestApi
 * @description: TODO
 * 
 * @version: v1.0.0
 * @author: caijiajia
 * @date: 2015-9-29
 */
@RestController
@RequestMapping(value = "/api/v1/index")
public class RestIndexController {

	private static Logger logger = LoggerFactory.getLogger(RestIndexController.class);

	@Autowired
	private ContentService contentService;
	@Autowired
	private Cache contentCache;

	private final Boolean isGetDataBy_v1 = true;
	private final String versionCode = "_v1";

	/**
	 * 
	 * @Title: initPage
	 * @Description: 初始化页面，加载首屏数据
	 * @param request
	 * @return IData
	 * @author bobo
	 * @throws Exception
	 * @date 2015-10-15 下午7:42:42
	 */
	@RequestMapping(value = "/access/initPage", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public IData initPage(HttpServletRequest request) throws Exception {

		IData all = new DataMap(EhcacheUtil.get("oss_INDEX_FIRST_SCREEN")); // 获取redis保存的数据
		if (null == all || 0 == all.size()) {// 判断redis中首屏数据是否为空
			// 调接口查询首屏数据
			IData childModeResult = contentService.qryChildModelInfoByPcode("firstScreen");
			// 获取解析对象
			String modelInfos = childModeResult.getData("result").getString("childModelInfos");
			IDataset firstScreenInfos = new DatasetList();
			if (null != modelInfos && !"".equals(modelInfos)) {
				firstScreenInfos = new DatasetList(modelInfos);

				// 循环取首屏数据
				for (int i = 0, size = firstScreenInfos.size(); i < size; i++) {
					IData firstScreenInfo = firstScreenInfos.getData(i);// 获取模块业务数据
					String modelCode = firstScreenInfo.getString("MODEL_CODE");
					if ("topBanner".equals(modelCode)) {// 顶通广告
						IData topBanner = new DataMap();// 返回页面数据对象
						IDataset topBannerDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						if (0 < topBannerDs.size()) {
							IData data = topBannerDs.getData(0);
							topBanner.put("src", data.getString("ELEMENT_CONTENT"));// 图片地址
							topBanner.put("url", data.getString("ELEMENT_ATTR1"));// 跳转地址
							topBanner.put("title", data.getString("ELEMENT_NAME"));// 悬停显示
							topBanner.put("background", data.getString("ELEMENT_ATTR2"));// 背景色
						}
						all.put("topBanner", topBanner);// 添加到页面渲染对象中
					} else if ("flashBanner".equals(modelCode)) {// 联版广告
						IDataset flashBanners = new DatasetList();// 返回页面数据对象
						IDataset flashBannerDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						for (int j = 0, fbSize = flashBannerDs.size(); j < fbSize; j++) {
							IData flashBanner = new DataMap();
							IData fbd = flashBannerDs.getData(j);
							flashBanner.put("src", fbd.getString("ELEMENT_CONTENT"));// 图片地址
							flashBanner.put("url", fbd.getString("ELEMENT_ATTR1"));// 跳转地址
							flashBanner.put("prompInfo", fbd.getString("ELEMENT_NAME"));// 图片说明
							flashBanners.add(flashBanner);
						}
						all.put("flashBanner", flashBanners);// 添加到页面渲染对象中
					} else if ("realTimeNotice".equals(modelCode)) {// 实时公告
						IDataset realTimeNotices = new DatasetList();// 返回页面数据对象
						IDataset realTimeNoticeDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						for (int j = 0, rtnSize = realTimeNoticeDs.size(); j < rtnSize; j++) {
							IData realTimeNotice = new DataMap();
							IData data = realTimeNoticeDs.getData(j);
							String redirectUrl = data.getString("ELEMENT_ATTR1");
							if ("".equals(redirectUrl)) {
								realTimeNotice.put("url", "javascript:void(0)");// 跳转地址
							} else {
								realTimeNotice.put("url", redirectUrl);// 跳转地址
							}
							realTimeNotice.put("title", data.getString("ELEMENT_NAME"));// 悬停显示
							String realTimeContent = data.getString("ELEMENT_NAME");
							if (13 < realTimeContent.length()) {// 标题字符过长则裁剪
								realTimeContent = realTimeContent.substring(0, 13) + "...";
							}
							realTimeNotice.put("content", realTimeContent);// 正文，多字隐藏
							realTimeNotices.add(realTimeNotice);
						}
						all.put("realTimeNotice", realTimeNotices);// 添加到页面渲染对象中
					} else if ("salesRank".equals(modelCode)) {// 销量排行
						IDataset salesRanks = new DatasetList();// 返回页面数据对象
						IDataset salesRanksDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						for (int j = 0, srSize = salesRanksDs.size(); j < srSize; j++) {
							IData salesRank = new DataMap();
							IData data = salesRanksDs.getData(j);
							salesRank.put("url", data.getString("ELEMENT_ATTR1"));// 跳转地址
							salesRank.put("title", data.getString("ELEMENT_NAME"));// 悬停显示
							String realTimeContent = data.getString("ELEMENT_NAME");
							if (13 < realTimeContent.length()) {// 标题字符过长则裁剪
								realTimeContent = realTimeContent.substring(0, 13) + "...";
							}
							salesRank.put("content", realTimeContent);// 正文，多字隐藏
							salesRank.put("count", data.getString("ELEMENT_ATTR2"));// 销售数量
							salesRanks.add(salesRank);
						}
						all.put("salesRank", salesRanks);// 添加到页面渲染对象中
					} else if ("timeLimit".equals(modelCode)) {// 今日特惠
						IData timeLimit = new DataMap();// 返回页面数据对象
						IDataset timeLimitDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						if (0 < timeLimitDs.size()) {
							IData data = timeLimitDs.getData(0);
							timeLimit.put("src", data.getString("ELEMENT_CONTENT"));// 图片地址
							timeLimit.put("url", data.getString("ELEMENT_ATTR1"));// 跳转地址
							timeLimit.put("title", data.getString("ELEMENT_NAME"));// 悬停显示
						}
						all.put("timeLimit", timeLimit);// 添加到页面渲染对象中
					} else if ("recommend".equals(modelCode)) {// 每日推荐
						IData recommend = new DataMap();// 返回页面数据对象
						IDataset recommendDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						if (0 < recommendDs.size()) {
							IData data = recommendDs.getData(0);
							recommend.put("src", data.getString("ELEMENT_CONTENT"));// 图片地址
							recommend.put("url", data.getString("ELEMENT_ATTR1"));// 跳转地址
							recommend.put("title", data.getString("ELEMENT_NAME"));// 悬停显示
						}
						all.put("recommend", recommend);// 添加到页面渲染对象中
					} else if ("jAgent".equals(modelCode)) {// 品牌厂商
						IDataset agents = new DatasetList();// 返回页面数据对象
						IDataset agentDs = firstScreenInfo.getDataset("ELEMENT_INFO");// 获取元素信息
						for (int j = 0, aSize = agentDs.size(); j < aSize; j++) {
							IData agent = new DataMap();
							IData data = agentDs.getData(j);
							agent.put("url", data.getString("ELEMENT_ATTR1"));// 跳转地址
							agent.put("title", data.getString("ELEMENT_NAME"));// 悬停显示
							agent.put("src", data.getString("ELEMENT_CONTENT"));// 图片地址
							agents.add(agent);
						}
						all.put("agent", agents);// 添加到页面渲染对象中
					}
				}

				// 将首屏数据放入redis
				EhcacheUtil.set("oss_INDEX_FIRST_SCREEN", all.toString()); 
			}

		}
		return all;
	}


	/**
	 * 
	 * @Title: filterProductList
	 * @Description: 过滤商品列表集合对象，去重
	 * @param goodsCode
	 * @param productList
	 * @return IDataset
	 * @author bobo
	 * @param salesType
	 * @date 2015-10-21 下午8:47:34
	 */
	private IDataset filterProductList(String goodsCode, String salesType, IDataset productList) {
		// 封装接口的入参商品编码对象，保证入参的商品编码唯一
		if (0 < productList.size()) {
			int pSize = 0;
			for (int j = 0, count = productList.size(); j < count; j++) {
				IData productMap = productList.getData(j);
				if (goodsCode.equals(productMap.getString("productId"))) {
					break;
				} else {
					pSize++;
				}
			}
			if (pSize == productList.size()) {
				IData productInfo = new DataMap();
				productInfo.put("productId", goodsCode);// 用于查询商品价格
				productInfo.put("merchandiseCd", goodsCode);// 用于查询商品销售限制
				productInfo.put("salesType", salesType);// 用于查询商品价格
				productList.add(productInfo);
			}
		} else {
			IData productInfo = new DataMap();
			productInfo.put("productId", goodsCode);// 用于查询商品价格
			productInfo.put("merchandiseCd", goodsCode);// 用于查询商品销售限制
			productInfo.put("salesType", salesType);// 用于查询商品价格
			productList.add(productInfo);
		}
		return productList;
	}


	


	/**
	 * 方法名称 :hotSearchKey 创 建 人 :xiadingli 创建时间 :2016-10-11 下午7:26:39 方法描述
	 * :查询地域热搜关键词
	 */
	@ResponseBody
	@RequestMapping("/access/navBar")
	public String navBar() throws Exception {
		Element element = new Element("111", "2222");
		contentCache.put(element);
		
		Element element2 = contentCache.get("111");
		
		return (String)element2.getObjectValue();
	}

	public IData getNavBar(String provinceID) {
		IData result = new DataMap();
		IData childModeResult = contentService.qryModelInfo("NAV_BAR", provinceID);
		result.put("resultCode", childModeResult.get("returnCode"));
		IDataset navBars = new DatasetList();// 返回页面数据对象
		if (childModeResult.get("returnCode").equals("0000")) {
			// 获取解析对象
			IData modelInfos = childModeResult.getData("result");

			IDataset hotSearchInfos = modelInfos.getDataset("ELEMENT_INFO"); // 获取元素信息
			if (null != hotSearchInfos && hotSearchInfos.size() != 0) {
				for (int i = 0, hsSize = hotSearchInfos.size(); i < hsSize; i++) {
					IData navBar = new DataMap();
					IData hsd = hotSearchInfos.getData(i);
					navBar.put("content", hsd.getString("ELEMENT_CONTENT"));// 文字内容
					navBar.put("name", hsd.getString("ELEMENT_NAME"));// 文字标题
					navBar.put("url", hsd.getString("ELEMENT_ATTR1"));// 跳转地址

					navBars.add(navBar);
				}
			} else if (provinceID != "0") {
				// 省份没有数据时用通用首页
				return getNavBar("0");
			}
		} else {
			// 省份没有数据时用通用首页
			return getNavBar("0");
		}
		result.put("navBars", navBars);
		return result;
	}
}
