/** 
 * Project Name:oss 
 * File Name:ContentServiceImpl.java 
 * Package Name:com.njm.oss.common.service.impl 
 * Date:2015-10-19下午1:39:09 
 * Copyright (c) 2015, bobo All Rights Reserved. 
 * 药药好（杭州）网络科技有限公司
*/  
  
package com.njm.oss.service.common.impl;  

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.njm.framework.data.DataMap;
import com.njm.framework.data.IData;
import com.njm.oss.common.util.EhcacheUtil;
import com.njm.oss.common.util.ServiceCode;
import com.njm.oss.common.util.UipServiceUtil;
import com.njm.oss.service.common.BaseService;
import com.njm.oss.service.common.ContentService;

/**
 * 
 * @ClassName:  ContentServiceImpl   
 * @Description:	内容管理接口实现类
 * @author bobo
 * @date:   2015-10-19 下午1:40:57   
 *
 */
@Service
public class ContentServiceImpl extends BaseService implements ContentService{
	
	/**
	 * 
	 * <p>Title: qryChildModelInfoByCode</p>   
	 * <p>Description: 通过父模块编码查询子模块信息</p>   
	 * @param pModelCode
	 * @return   
	 * @see com.njm.oss.service.common.ContentService#qryChildModelInfoByPcode(java.lang.String)   
	 * @author bobo
	 * @date 2015-10-19 下午1:40:48
	 */
	public IData qryChildModelInfoByPcode(String pModelCode) {
		JSONObject param = new JSONObject();
		param.put("pModelCode", pModelCode);
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_CHILD_MODEL_CONTENT, param);

		return result;
	}
	
	/**
	 * 
	 * <p>Title: qryChildModelInfoByPid</p>   
	 * <p>Description: 通过父模块ID查询子模块信息</p>   
	 * @param pModelId
	 * @return   
	 * @see com.njm.oss.service.common.ContentService#qryChildModelInfoByPid(java.lang.String)   
	 * @author bobo
	 * @date 2015-10-19 下午2:27:55
	 */
	public IData qryChildModelInfoByPid(String pModelId) {
		JSONObject param = new JSONObject();
		param.put("pModelId", pModelId);
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_CHILD_MODEL_CONTENT, param);

		return result;
	}
	
	/**
	 * 
	 * <p>Title: qryChildModelInfo</p>   
	 * <p>Description:  查询子模块信息</p>   
	 * @param inParam
	 * @return
	 * @throws Exception   
	 * @see com.njm.oss.service.common.ContentService#qryChildModelInfo(com.yyh.framework.data.IData)   
	 * @author bobo
	 * @date 2016-3-29 下午5:03:39
	 */
	public IData qryChildModelInfo(IData inParam) throws Exception {
		JSONObject param = new JSONObject();
		String[] keys = inParam.getNames();
		for(String key : keys){
			param.put(key, inParam.get(key));
		}
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_CHILD_MODEL_CONTENT, param);
		return result;
	}
	
	/**
	 * 
	 * <p>Title: qryModelInfo</p>   
	 * <p>Description: 查询模块信息</p>   
	 * @param modelCode
	 * @return   
	 * @see com.njm.oss.service.common.ContentService#qryModelInfo(java.lang.String)   
	 * @author bobo
	 * @date 2015-10-19 下午2:32:03
	 */
	public IData qryModelInfo(String modelCode,String ... provinceID) {
		JSONObject param = new JSONObject();
		param.put("modelCode", modelCode);
		if(provinceID!=null&&!"".equals(provinceID) && 0 < provinceID.length)
			param.put("areaCode", provinceID[0]);
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_MODEL_CONTENT, param);

		return result;
	}
	
	/**
	 * 
	 * <p>Title: qryPageInfo</p>   
	 * <p>Description: 查询页面及模块&元素信息</p>   
	 * @param pageParam
	 * @return   
	 * @see com.njm.oss.service.common.ContentService#qryPageInfo(com.yyh.framework.data.IData)   
	 * @author bobo
	 * @throws Exception 
	 * @date 2015-12-8 下午1:35:32
	 */
	public IData qryPageInfo(IData pageParam) throws Exception {
		String pageCode = pageParam.getString("pageCode");
		IData result = new DataMap(EhcacheUtil.get("oss_ACT_CONTENT_" + pageCode));
		if (null == result || 0 == result.size()) {// 判断redis中该页面编码的内容数据是否为空
			JSONObject param = new JSONObject();
			param.put("pageCode", pageCode);
			result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_PAGE_CONTENT, param);
		
			// 只有成功的数据才保存至redis
			if ("0000".equals(result.getString("returnCode"))) {
				//空数据也不保存redis
				IData pageInfoResult = result.getData("result");
				if(0 < pageInfoResult.size()){
					EhcacheUtil.set("oss_ACT_CONTENT_" + pageCode, result.toString());
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 
	 * <p>Title: qryPageInfoWithCount</p>   
	 * <p>Description: 查询(含起始块，数量的)模块信息</p>   
	 * @param String modelCode,String startIndex,String maxCount
	 * @return   
	 * @see com.njm.oss.service.common.ContentService#qryModelInfoWithCount(java.lang.String)   
	 * @author chenxingxing
	 * @date 2016-03-22 下午2:32:03
	 */
	public IData qryModelInfoWithCount(String modelCode,String startIndex,String maxCount) {
		JSONObject params=new JSONObject();
		params.put("modelCode", modelCode);
		params.put("startIndex", startIndex);
		params.put("maxCount", maxCount);
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_MODEL_CONTENT, params);
		return result;
	}
	
	/**
	 * 
	 * @Title:	qryModelInfoByParent
	 * @Description:   通过父模块ID／父模块编码查询下一级子模块信息及该模
	 * @param 
	 * 	pModelId 父模块ID
	 * 	pModelCode 父模块编码      父模块ID和父模块编码至少传一个，若两个都传，默认传父模块ID
	 * 	mIndex	模块起始索引值   从0开始，不传默认0
	 * 	mOffset	模块偏移量	mOffset	模块偏移量	从模块起始索引值起往后的偏移值，例:传1，则表示从mIndex开始再往后请求一个模块量，则总模块请求个数为2；传2则总请求模块数为3
不传默认全部获取
	 *	eTotal	元素个数	单模块下的元素请求个数不传默认全部获取
	 * @return  IData  
	 * @author chenxingxing
	 * @date 2016-03-22 下午2:30:32
	 */
	public IData qryModelInfoByParent(String pModelId,String pModelCode,String mIndex,String mOffset,String eTotal)
	{
		JSONObject params=new JSONObject();
		if(pModelId!=null && !pModelId.equals(""))
			params.put("pModelId", pModelId);
		else 
			params.put("pModelCode", pModelCode);
		if(mIndex!=null && !mIndex.equals(""))
			params.put("mIndex", mIndex);
		if(mOffset!=null && !mOffset.equals(""))
			params.put("mOffset", mOffset);
		if(eTotal!=null && !eTotal.equals(""))
			params.put("eTotal", eTotal);
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_CHILD_MODEL_CONTENT, params);
		return result;
	}
	
	public IData qryChildAreaModelInfo(String pModelCode,String provinceID){
		JSONObject params=new JSONObject();
		params.put("pModelCode", pModelCode);
		params.put("areaCode", provinceID);
		IData result = UipServiceUtil.callUip(ServiceCode.SERVICE_CMS, ServiceCode.CMS_TRADE_QRY_CHILD_MODEL_AREA_CONTENT, params);
		return result;
	}
}
  