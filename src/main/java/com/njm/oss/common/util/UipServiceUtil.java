/**
 * Project Name:mms
 * File Name:UipServiceUtil.java
 * Package Name:com.njm.mms.common
 * Date:2015-9-23上午11:39:36
 * Copyright (c) 2015, qwn All Rights Reserved.
 * 药药好（杭州）网络科技有限公司
 */

package com.njm.oss.common.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.njm.framework.data.DataMap;
import com.njm.framework.data.IData;
import com.yyh.uip.client.intf.dto.UIPClientHead;
import com.yyh.uip.client.intf.dto.UIPClientResponse;
import com.yyh.uip.client.service.UipClientHandle;

/**
 * @ClassName: UipServiceUtil
 * @Description:TODO(调用uip服务工具类)
 * @author qwn
 * @date: 2015-9-23 上午11:39:42
 * 
 */
public class UipServiceUtil {

	private static Logger log = Logger.getLogger(UipServiceUtil.class);

	public static IData callUip(String service_code, String trade_code, JSON json) {
		log.debug("");
		log.debug("UIP接口调用----->服务编码 :" + service_code + ", 交易代码 :" + trade_code);
		log.debug("UIP接口调用----->调用参数 :" + json.toJSONString());

		IData result = null;

		try {

			UIPClientHead head = new UIPClientHead();
			head.setServiceCode(service_code);
			head.setOperation(trade_code);

			UipClientHandle handle = new UipClientHandle();
			UIPClientResponse response = handle.sendJsonMsg(head, json,null);

			String body = response.getUipBody();
			result = new DataMap(body);

		}
		catch (Exception e) {
			result = new DataMap();
			result.put("code", "9999");
			result.put("returnCode", "9999");
			result.put("info", "接口调用异常");
			result.put("msg", e.getMessage());
			log.debug("调试输出----->UIP调用接口异常，异常信息:" + e.getMessage());
			e.printStackTrace();
		}
		log.debug("UIP接口调用----->返回结果 :" + JSON.toJSONString(result));
		return result;
	}
}
