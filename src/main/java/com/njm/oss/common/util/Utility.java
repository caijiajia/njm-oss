package com.njm.oss.common.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.njm.framework.data.DataMap;
import com.njm.framework.data.DatasetList;
import com.njm.framework.data.IData;
import com.njm.framework.data.IDataset;

public class Utility {
	/**
	 * 方法名称 :dealNum
	 * 创 建 人 :lijinlong
	 * 创建时间 :2015-11-3 下午8:01:14
	 * 方法描述 :处理商品购买数量限制信息
	 * 参 数 :@param num
	 * 结 果 :void
	 * 异 常 :@throws
	 */
	public static void dealNum(IData num) {

		int sales_type = num.getInt("sales_type", 0);
		long middle_num = num.getLong("middle_num", 1);
		long whole_num = num.getLong("whole_num", 1);
		long stock_num = num.getLong("stock_num", 0);
		long min_num = num.getLong("min_num", 1);
		long max_num = num.getLong("max_num", 0);

		num.put("code", 0);
		num.put("info", "处理失败");

		long step = 1;
		if (sales_type == 2) {
			step = whole_num;
		}
		else
			if (sales_type == 1) {
				step = middle_num;
			}
		num.put("step", step);

		if (min_num < step) {
			min_num = step;
		}

		min_num = (int) Math.ceil((double) min_num / (double) step) * step;
		num.put("min_num", min_num);

		if (max_num > 0) {
			// 当最大购买数量超过库存是，将最大购买数量设置为库存
			max_num = (max_num > stock_num) ? stock_num : max_num;
		}
		else {
			max_num = stock_num;
		}

		if (max_num < min_num) {
			max_num = min_num;
		}
		num.put("max_num", max_num);

		if (stock_num <= 0) {
			// 没有库存
			num.put("info", "没有库存");
			return;
		}

		if (min_num > stock_num) {
			// 最小购买数量超过没有库存
			num.put("info", "最小购买数量超过库存，无足够可售库存");
			return;
		}

		max_num = ((int) Math.floor((double) max_num / (double) step)) * step;

		num.put("code", 1);
		num.put("info", "处理成功");

		num.put("max_num", max_num);
	}

	/**
	 * 方法名称 :dealPrice
	 * 创 建 人 :lijinlong
	 * 创建时间 :2015-11-3 下午8:02:02
	 * 方法描述 :处理商品价格信息
	 * 参 数 :@param price
	 * 参 数 :@param num
	 * 结 果 :void
	 * 异 常 :@throws
	 */
	public static void dealPrice(IData price, IData num) {

		double init_price = 0;
		double old_price = 0;
		double zero_price = price.getDouble("zero_price", 0);
		double middle_price = price.getDouble("middle_price", 0);
		double whole_price = price.getDouble("whole_price", 0);

		double regionZeroPrice = price.getDouble("regionZeroPrice", 0);//指定区域价
		double regionMediumPrice = price.getDouble("regionMediumPrice", 0);//指定区域价
		double regionWholePrice = price.getDouble("regionWholePrice", 0);//指定区域价

		double promotional_price = price.getDouble("promotional_price", 0);//特价

		double protocolPrice = price.getDouble("protocolPrice", 0);//指定价

		long sales_type = num.getLong("sales_type", 0);//销售限制类型
		long min_num = num.getLong("min_num", 1);
		long middle_num = num.getLong("middle_num", 1);
		long whole_num = num.getLong("whole_num", 1);

		int type = 0;//价格类型 0：基础价；1：区域价；2：特价；3：指定价；4：套餐价；

		int old_price_type = 0;//原价类型 0：基础价；1：区域价；2：特价；3： 指定价；

		//存在指定区域价，设置价格类型为区域价
		if (regionZeroPrice > 0) {
			zero_price = regionZeroPrice;
			middle_price = regionMediumPrice;
			whole_price = regionWholePrice;

			old_price_type = 1;
			type = 1;
		}

		price.put("zero_price", zero_price);
		price.put("middle_price", middle_price);
		price.put("whole_price", whole_price);

		old_price = zero_price;
		init_price = zero_price;

		//根据商品销售控制类型，及最小购买数量计算 原价和初始价格
		//两种 销售控制数量相同时，按销售控制类型 价格
		if (sales_type == 0) {
			if (min_num >= whole_num) {
				old_price = whole_price;
				init_price = whole_price;
			}
			if (min_num >= middle_num) {
				old_price = middle_price;
				init_price = middle_price;
			}
			if (min_num >= 1) {
				old_price = zero_price;
				init_price = zero_price;
			}
		}

		if (sales_type == 1) {
			if (min_num >= whole_num) {
				old_price = whole_price;
				init_price = whole_price;
			}
			if (min_num >= middle_num) {
				old_price = middle_price;
				init_price = middle_price;
			}
		}

		if (sales_type == 2) {
			old_price = whole_price;
			init_price = whole_price;
		}

		//存在特价，价格类型设置为特价
		if (promotional_price > 0) {
			type = 2;
			init_price = promotional_price;
		}

		//存在指定价，价格类型设置为特价
		if (protocolPrice > 0) {
			type = 3;
			init_price = protocolPrice;
		}

		double bonusAmont = price.getDouble("bonusAmont", 0);
		//存在优惠价格证明为套餐价格
		if (bonusAmont > 0) {
			type = 4;

			if (promotional_price > 0) {
				old_price = promotional_price;
			}

			if (protocolPrice > 0) {
				old_price = protocolPrice;
			}

			init_price = old_price - bonusAmont;
		}

		price.put("type", type);
		price.put("init_price", init_price);
		price.put("old_price_type", old_price_type);
		price.put("old_price", old_price);

		if (init_price <= 0) {
			price.put("code", 0);
		}
		else {
			price.put("code", 1);
		}

	}

	/**
	 * 方法名称 :getSubdataDataset
	 * 创 建 人 :lijinlong
	 * 创建时间 :2015-11-4 上午11:27:18
	 * 方法描述 :取Dataset中符合条件key 和对应 value 子列表
	 * 参 数 :@param dataset
	 * 参 数 :@param keyStr
	 * 参 数 :@param keyValue
	 * 参 数 :@param split
	 * 参 数 :@return
	 * 结 果 :IDataset
	 * 异 常 :@throws
	 */
	public static IDataset getSubdataDataset(IDataset dataset, String keyStr, String keyValue, String split) {
		IDataset subDataset = new DatasetList();

		if (keyStr == null || keyValue == null) {
			return null;
		}

		if (split == null) {
			split = ",";
		}
		String[] keys = keyStr.split("[" + split + "]");
		String[] values = keyValue.split("[" + split + "]");

		if (keys.length > values.length) {
			return subDataset;
		}

		IData tempData = null;
		for (int i = 0, size = dataset.size(); i < size; i++) {
			tempData = dataset.getData(i);

			boolean flag = false;
			for (int j = 0, len = keys.length; j < len; j++) {
				String key = keys[j];
				String value = values[j];
				if (!tempData.containsKey(key)) {
					flag = true;
					break;
				}
				String matchValue = tempData.get(key).toString();
				if (!value.equals(matchValue)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				subDataset.add(tempData);
			}
		}
		return subDataset;
	}

	public static IData dealActivity(IData spes) {

		IData result = new DataMap();

		IData price_info = new DataMap();

		price_info.put("zero_price", spes.get("zeroPrice"));
		price_info.put("middle_price", spes.get("mediumPackPrice"));
		price_info.put("whole_price", spes.get("wholePackPrice"));

		if (null != spes.get("regionZeroPrice")) {// 区域价 价格不为空，设置价格类型为区域价
			price_info.put("regionZeroPrice", spes.get("regionZeroPrice"));
			price_info.put("regionMediumPrice", spes.get("regionMediumPrice"));
			price_info.put("regionWholePrice", spes.get("regionWholePrice"));
		}

		if (null != spes.get("protocolPrice")) {// 指定价 优先级最高
			price_info.put("protocolPrice", spes.get("protocolPrice"));
		}

		result.put("price_info", price_info);

		if (1 != spes.getInt("verifyResult")) {//没有促销返回
			return result;
		}

		IDataset activityList = spes.getDataset("activityList");
		IData activity = null;

		IData activityMap = new DataMap();

		for (int i = 0, size = activityList.size(); i < size; i++) {
			activity = activityList.getData(i);

			if (1 == activity.getInt("activityType")) {
				IData activityType1Result = dealActivityType1(activity);

				if (activityType1Result.containsKey("type1_4")) {
					price_info
							.put("promotional_price", activityType1Result.getData("type1_4").get("promotional_price"));
				}

				activityMap.putAll(activityType1Result);
				continue;
			}
			if (2 == activity.getInt("activityType")) {

				IData activityType2Result = dealActivityType2(activity);

				activityMap.putAll(activityType2Result);
				continue;
			}
		}

		result.put("activity", activityMap);

		return result;
	}

	public static IData dealActivityType1(IData activity) {
		IData result = new DataMap();

		IData zheRang = new DataMap();//用于记录单品折让优惠活动折让阶梯数量及价格；
		IDataset zheRangList = new DatasetList();

		String danPinSongQuanLabel = null;

		IDataset zengPinList = new DatasetList();

		IDataset liPinList = new DatasetList();

		//特价活动信息Map
		IData teJia = new DataMap();

		String giftId = "";
		String giftName = "";

		IDataset bonusLevelList = activity.getDataset("bonusLevelList");
		for (int j = 0, size2 = bonusLevelList.size(); j < size2; j++) {
			IData bonusLevel = bonusLevelList.getData(j);

			int baseAddUpType = bonusLevel.getInt("baseAddUpType");
			int baseAmount = Double.valueOf(bonusLevel.getDouble("baseAmount")).intValue();

			int baseQuantifierType = bonusLevel.getInt("baseQuantifierType");

			IDataset bonusList = bonusLevel.getDataset("bonusList");
			for (int i = 0, size3 = bonusList.size(); i < size3; i++) {
				IData bonus = bonusList.getData(i);
				String bonusType = bonus.getString("bonusType");

				//bonusType = 1，单品折让活动
				if ("1".equals(bonusType)) {
					IData zheRangInfo = new DataMap();
					zheRangInfo.put("baseAmount", baseAmount);
					zheRangInfo.put("baseAddUpType", baseAddUpType);
					zheRangInfo.put("baseQuantifierType", baseQuantifierType);

					zheRangInfo.put("bonusAmont", bonus.get("bonusAmont"));
					zheRangInfo.put("unitType", bonus.get("unitType"));

					zheRang.put("activityList", zheRangList);
					zheRang.put("activityId", activity.getString("activityId"));
					zheRang.put("maxBuyCount", Math.round(activity.getDouble("maxBuyCount", -1)));

					zheRangList.add(zheRangInfo);
					continue;
				}

				// 单品 返券
				if ("2".equals(bonusType)) {
					danPinSongQuanLabel = activity.getString("promotionLabel");
				}

				// bonusType = 3，3=送赠品
				if ("3".equals(bonusType) || "6".equals(bonusType)) {
					IData giftsInfos = new DataMap();
					giftsInfos.put("baseAmount", baseAmount);
					giftsInfos.put("baseAddUpType", baseAddUpType);
					giftsInfos.put("baseQuantifierType", baseQuantifierType);
					giftsInfos.put("favoraTerms", bonus.get("favoraTerms"));

					giftsInfos.put("favoraAmont", bonus.get("favoraAmont"));
					giftsInfos.put("bonusAmont", bonus.get("bonusAmont"));
					giftsInfos.put("bonusLabel", bonus.get("bonusLabel"));
					giftsInfos.put("unitType", bonus.get("unitType"));
					IDataset giftsInfo = bonus.getDataset("giftsInfo");

					if (giftsInfo.size() > 0) {
						IData giftsDetail = giftsInfo.getData(0);
						giftId = giftsDetail.getString("giftId");

						String giftProductType = giftsDetail.getString("giftProductType");
						String giftNumberOnetime = giftsDetail.getString("giftNumberOnetime");
						String remainGiftNumber = giftsDetail.getString("remainGiftNumber");
						giftName = giftsDetail.getString("giftName");
						giftsInfos.put("giftId", giftId);
						giftsInfos.put("giftName", giftName);
						giftsInfos.put("giftProductType", giftProductType);
						giftsInfos.put("giftNumberOnetime", giftNumberOnetime);
						giftsInfos.put("remainGiftNumber", remainGiftNumber);

					}
					if ("3".equals(bonusType)) {
						zengPinList.add(giftsInfos);
					}
					else
						if ("6".equals(bonusType)) {
							liPinList.add(giftsInfos);
						}
					continue;
				}

				//bonusType = 4，单品特价活动
				if ("4".equals(bonusType)) {
					teJia.put("promotional_price", bonus.get("promotionalPrice"));
					continue;
				}
			}
		}

		if (!zheRangList.isEmpty()) {
			zheRang.put("activityList", zheRangList);
			zheRang.put("activityId", activity.getString("activityId"));
			zheRang.put("activityType", activity.getString("activityType"));
			zheRang.put("promotionLabel", activity.getString("promotionLabel"));

			result.put("type1_1", zheRang);
		}

		if (null != danPinSongQuanLabel) {
			result.put("type1_2", danPinSongQuanLabel);
		}

		if (!zengPinList.isEmpty()) {
			IData zengPin = new DataMap();// 赠品
			zengPin.put("activityList", zengPinList);
			zengPin.put("giftName", giftName);
			zengPin.put("giftId", giftId);
			zengPin.put("activityId", activity.getString("activityId"));
			zengPin.put("activityType", activity.getString("activityType"));
			zengPin.put("promotionLabel", activity.getString("promotionLabel"));

			result.put("type1_3", zengPin);
		}

		if (!teJia.isEmpty()) {
			teJia.put("activityId", activity.getString("activityId"));
			teJia.put("activityType", activity.getString("activityType"));
			teJia.put("promotionLabel", activity.getString("promotionLabel"));

			result.put("type1_4", teJia);
		}

		if (!liPinList.isEmpty()) {
			IData liPin = new DataMap();// 礼品
			liPin.put("activityList", liPinList);
			liPin.put("activityId", activity.getString("activityId"));
			liPin.put("activityType", activity.getString("activityType"));
			liPin.put("promotionLabel", activity.getString("promotionLabel"));

			result.put("type1_6", liPin);
		}

		return result;
	}

	//activityType = 2，订单类型促销活动
	public static IData dealActivityType2(IData activity) {

		IData result = new DataMap();
		IDataset bonusLevelList = activity.getDataset("bonusLevelList");

		if (null != bonusLevelList && !bonusLevelList.isEmpty()) {

			IData dingDanManJian = new DataMap();

			IData bonusLevel = null;
			IDataset bonusList = null;
			IData bonus = null;

			for (int i = 0, bonusLevelSize = bonusLevelList.size(); i < bonusLevelSize; i++) {
				bonusLevel = bonusLevelList.getData(i);
				bonusList = bonusLevel.getDataset("bonusList");
				for (int j = 0, bonusSize = bonusList.size(); j < bonusSize; j++) {
					bonus = bonusList.getData(j);
					String bonusType = bonus.getString("bonusType");

					if ("1".equals(bonusType)) {
						dingDanManJian.put("activityId", activity.getString("activityId"));
						dingDanManJian.put("activityType", activity.getString("activityType"));
						dingDanManJian.put("promotionLabel", activity.getString("promotionLabel"));
					}
				}
			}
			if (!dingDanManJian.isEmpty()) {
				result.put("type2_1", dingDanManJian);
			}

		}
		else {
			result.put("type2_1", activity);
		}

		return result;
	}

	/**
	 * @Title: getRequestIp
	 * @Description: 获取用户IP
	 * @param request
	 * @return String
	 * @author chenxingxing
	 * @throws Exception
	 * @date 2016-05-09 下午3:14:09
	 */
	public static String getRequestIp(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
				//根据网卡取本机配置的IP  
				InetAddress inet = InetAddress.getLocalHost();
				ip = inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
		if (ip != null && ip.length() > 15) {//"***.***.***.***".length() = 15  
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

	public static IData ip2Area(IData inParam) {
		JSONObject param = new JSONObject();
		String[] keys = inParam.getNames();
		for (String key : keys) {
			param.put(key, inParam.get(key));
		}
		IData cartResult = UipServiceUtil.callUip(ServiceCode.SERVICE_MEMBER,
				ServiceCode.MEMBER_TRADE_QUERY_USER_ADDRESS, param);

		return cartResult;
	}
	
}
