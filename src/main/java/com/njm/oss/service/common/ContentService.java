/** 
 * Project Name:oss 
 * File Name:ContentService.java 
 * Package Name:com.yaoyaohao.oss.common.service 
 * Date:2015-10-19下午1:35:23 
 * Copyright (c) 2015, bobo All Rights Reserved. 
 * 药药好（杭州）网络科技有限公司
*/  
  
package com.njm.oss.service.common;  

import com.njm.framework.data.IData;

public interface ContentService {

	/**
	 * 
	 * @Title:	qryChildModelInfoByCode
	 * @Description:   通过父模块编码查询子模块信息
	 * @param pModelCode
	 * @return  IData  
	 * @author bobo
	 * @date 2015-10-19 下午1:38:06
	 */
	public IData qryChildModelInfoByPcode(String pModelCode);
	
	/**
	 * 
	 * @Title:	qryChildModelInfoByPid
	 * @Description:    通过父模块ID查询子模块信息
	 * @param pModelCode
	 * @return  IData  
	 * @author bobo
	 * @date 2015-10-19 下午2:28:12
	 */
	public IData qryChildModelInfoByPid(String pModelCode);
	
	/**
	 * 
	 * @Title:	qryChildModelInfo
	 * @Description:   查询子模块信息
	 * @param inParam
	 * @return
	 * @throws Exception  IData  
	 * @author bobo
	 * @date 2016-3-29 下午5:03:09
	 */
	public IData qryChildModelInfo(IData inParam) throws Exception;
	
	/**
	 * 
	 * @Title:	qryModelInfo
	 * @Description:   查询模块信息
	 * @param modelCode
	 * @return  IData  
	 * @author bobo
	 * @date 2015-10-19 下午2:30:32
	 */
	public IData qryModelInfo(String modelCode,String ... provinceID);
	
	/**
	 * 
	 * @Title:	qryPageInfo
	 * @Description:  查询页面及模块&元素信息
	 * @param param
	 * @return  IData  
	 * @author bobo
	 * @throws Exception 
	 * @date 2015-12-8 下午1:34:17
	 */
	public IData qryPageInfo(IData pageParam) throws Exception;
	
	/**
	 * 
	 * @Title:	qryModelInfoWithCount
	 * @Description:   查询(含起始块，数量的)模块信息
	 * @param modelCode--模块编码  startIndex--起始索引  maxCount--查询总量
	 * @return  IData  
	 * @author chenxingxing
	 * @date 2016-03-22 下午2:30:32
	 */
	public IData qryModelInfoWithCount(String modelCode,String startIndex,String maxCount);
	
	
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
	public IData qryModelInfoByParent(String pModelId,String pModelCode,String mIndex,String mOffset,String eTotal);
	
	/**
	 * 
	 * @Title:	qryChildAreaModelInfo
	 * @Description:   通过父模块编码,区域查询子模块信息
	 * @param pModelCode
	 * @return  IData  
	 * @author chenxingxing
	 * @date 2016-05-11 下午1:38:06
	 */
	public IData qryChildAreaModelInfo(String pModelCode,String provinceID);
	
}
  