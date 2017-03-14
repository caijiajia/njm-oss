/**
 * Project Name:mms
 * File Name:ServiceCode.java
 * Package Name:com.njm.mms.common
 * Date:2015-10-7下午4:17:06
 * Copyright (c) 2015, zym All Rights Reserved.
 * 药药好（杭州）网络科技有限公司
 */

package com.njm.oss.common.util;

/**
 * @ClassName: ServiceCode
 * @Description: 交易名称变更类
 * @author: zym
 * @date: 2015-10-7 下午4:17:09
 */
public class ServiceCode {

	/**
	 * 服务码
	 * SERVICE_MEMBER:订单中心
	 */
	public static String SERVICE_ORDER = "OrderService";

	/**
	 * 服务码
	 * SERVICE_MEMBER:会员
	 */
	public static String SERVICE_MEMBER = "MemberService";

	/**
	 * 服务码
	 * SERVICE_MEMBER:消息发送平台
	 */
	public static String SERVICE_MSG_SEND = "MsgSendService";
	/**
	 * 服务码
	 * SERVICE_SPES:促销平台
	 */
	public static String SERVICE_SPES = "spesService";
	
	/**
	 * 服务码
	 * SERVICE_SPES:促销平台
	 * addby chenxingxing 20160323
	 */
	public static String SERVICE_SPES_A = "SpesService";
	
	/**
	 * 服务码
	 * SERVICE_GOODS:商品平台
	 */
	public static String SERVICE_GOODS = "GoodsService";

	/**
	 * 服务码
	 * SERVICE_STOCK:库存管理
	 */
	public static String SERVICE_STOCK = "StockService";

	/**
	 * 服务码
	 * SERVICE_CMS:内容平台
	 */
	public static String SERVICE_CMS = "ContentService";


	/**
	 * 服务码
	 * SERVICE_CMS:购物车
	 */
	public static String SERVICE_SCS = "ScsService";
	
	/**
	 * 会员
	 * 交易码
	 * TRADE_M_LOGIN:用户信息查询
	 */
	public static String MEMBER_TRADE_M_QUERY_USER_INFO = "mQueryUserInfo";

	/**
	 * 会员
	 * 交易码
	 * TRADE_M_ADD_CUSTOMER:会员注册
	 */
	public static String MEMBER_TRADE_M_ADD_CUSTOMER = "mAddCustomer";

	/**
	 * 会员
	 * 交易码
	 * TRADE_M_QUERY_CUS:客户信息及状态查询
	 */
	public static String MEMBER_TRADE_M_QUERY_CUS = "mQueryCus";

	/**
	 * 会员
	 * 交易码
	 * TRADE_M_PASSWORD:密码服务
	 */
	public static String MEMBER_TRADE_M_PASSWORD = "mPassword";

	/**
	 * 会员
	 * 交易码
	 * TRADE_M_UPDATE_USER_TYPE:账户可用性状态更新
	 */
	public static String MEMBER_TRADE_M_UPDATE_USER_TYPE = "mUpdateUserType";

	/**
	 * 会员
	 * 交易码
	 * TRADE_M_QUERY_ADDRESS:地址信息查询
	 */
	public static String MEMBER_TRADE_M_QUERY_ADDRESS = "mQueryAddress";
	/**
	 * 消息发送平台
	 * 交易码
	 * TRADE_SMS_SEND:短信消息发送接口
	 */
	public static String SMS_TRADE_SMS_SEND = "smsSend";

	/**
	 * 消息发送平台
	 * 交易码
	 * TRADE_CODE_VERIFICATION:验证码校验接口
	 */
	public static String SMS_TRADE_CODE_VERIFICATION = "codeVerification";
	/**
	 * 消息发送平台
	 * 交易码
	 * TRADE_QRY_ORDERS_INFO:查询订单信息接口
	 */
	public static String ORDER_TRADE_QRY_ORDERS_INFO = "qryOrdersInfo";
	/**
	 * 消息发送平台
	 * 交易码
	 * TRADE_QUERY_COLLECTIONS:查询我的收藏接口
	 */
	public static String MEMBER_TRADE_QUERY_COLLECTIONS = "mQueryMyCollections";
	/**
	 * 促销平台
	 * 交易码
	 * TRADE_SEARCH_4PAGE:查询四级页
	 */
	public static String SPES_TRADE_SEARCH_4PAGE = "search4Page";
	
	
	/**
	 * 促销平台
	 * 交易码
	 * TRADE_SEARCH_4PAGE:秒杀活动查询四级页
	 */
	public static String SPES_TRADE_SEARCH_SECKILL_4PAGE = "searchSeckill4Page";

	/**
	 * 促销平台
	 * 交易码
	 * SPES_TRADE_SEARCH_GOODS_PRICE:查询商品价格
	 */
	public static String SPES_TRADE_QUERY_SIMPLE_ACT = "querySimpleAct";
	
	public static String SPES_TRADE_COLLECT_COUPON = "freeCouponReceive";

	/**
	 * 商品平台
	 * 交易码
	 * GOODS_TRADE_QUERY_MERC:查询单个商品信息
	 */
	public static String GOODS_TRADE_QUERY_MERC = "gQueryMerc";

	/**
	 * 商品平台
	 * 交易码
	 * GOODS_TRADE_QUERY_MERCINFOS:查询多个商品信息
	 */
	public static String GOODS_TRADE_QUERY_MERCINFOS = "gQueryMercInfos";
	
	/**
	 * 商品平台
	 * 交易码
	 * GOODS_TRADE_QUERY_MERCINFOS:查询多个商品信息
	 */
	public static String GOODS_TRADE_QUERY_MERC_LIST = "gQueryMercList";
	
	/**
	 * 会员
	 * 交易码
	 * GOODS_TRADE_ADD_COLLECTIONS:加入收藏夹
	 */
	public static String MEMB_TRADE_ADD_COLLECTIONS = "mAddMyCollections";
	
	public static String MEMB_TRADE_M_LABEL_OPERTIONS = "mLabelOpertions";

	/**
	 * 内容平台
	 * 交易码
	 * GOODS_TRADE_QUERY_MERC_LIMIT:商品购买限制
	 */
	public static String GOODS_TRADE_QUERY_MERC_LIMIT = "gQueryMercLimit";

	/**
	 * 内容平台
	 * 交易码
	 * GOODS_TRADE_QUERY_MERC_PRICE:查询商品价格
	 */
	public static String GOODS_TRADE_QUERY_MERC_PRICE = "gQueryMercPrice";

	/**
	 * 商品平台
	 * 交易码
	 * GOODS_TRADE_ADD_OUTSTOCK_REGISTER:缺货登记接口
	 */
	public static String GOODS_TRADE_ADD_OUTSTOCK_REGISTER = "gAddOutOfStockRegister";
	/**
	 * 内容平台
	 * 交易码
	 * GOODS_TRADE_QUERY_GOODS_MENUS:查询商品分类菜单
	 */
	public static String GOODS_TRADE_QUERY_GOODS_MENUS = "gQueryGoodsMenus";
	
	//商品到货通知
	public static String GOODS_TRADE_ADD_ARRIVAL_REMIND = "gAddArrivalRemind";
	
	public static String GOODS_TRADE_QUERY_ARRIVAL_REMIND = "gQueryArrivalRemind";

	/**
	 * 内容平台
	 * 交易码
	 * STOCK_TRADE_QUERY_GOODS_AMOUNT:查询商品库存
	 */
	public static String STOCK_TRADE_QUERY_GOODS_AMOUNT = "qryGoodsAmount";

	/**
	 * 内容平台
	 * 交易码
	 * CMS_TRADE_QRY_CHILD_MODEL_CONTENT:查询子模块信息
	 */
	public static String CMS_TRADE_QRY_CHILD_MODEL_CONTENT = "qryChildModelContent";

	/**
	 * 内容平台
	 * 交易码
	 * CMS_TRADE_QRY_MODEL_CONTENT:查询模块信息
	 */
	public static String CMS_TRADE_QRY_MODEL_CONTENT = "qryModelContent";

	/**
	 * 内容平台
	 * 交易码
	 * CMS_TRADE_QRY_PAGE_CONTENT:查询页面信息
	 */
	public static String CMS_TRADE_QRY_PAGE_CONTENT = "qryPageContent";
	
	
	/**
	 * 购物车
	 * 交易码
	 * TRADE_M_LOGIN:秒杀加入购物车
	 */
	public static String SCS_TRADE_ADD_2_MSCART = "add2MsCart";
	
	public static String SCS_TRADE_ADD_TO_CART1 = "add2Cart1";
	
	
	/**
	 * 促销
	 * 交易码
	 * TRADE_M_LOGIN:免费领券查询接口
	 * addby chenxingxing 
	 */
	public static String SPES_QUERY_FREE_COUPON = "queryFreeCoupon";
	
	/**
	 * 促销
	 * 交易码
	 * TRADE_M_LOGIN:免费领券查询接口
	 * addby chenxingxing 
	 */
	public static String SPES_FREE_COUPON_RECEIVE = "freeCouponReceive";
	
	/**
	 * 会员
	 * 交易码
	 * MEMBER_TRADE_QUERY_USER_ADDRESS:IP获取访问地址
	 * addby chenxingxing  20160509 
	 */
	public static String MEMBER_TRADE_QUERY_USER_ADDRESS = "mQueryUserLoginAddress";
	
	/**
	 * 内容平台
	 * 交易码
	 * CMS_TRADE_QRY_CHILD_MODEL_AREA_CONTENT:查询子模块区域信息
	 * addby chenxingxing  20160511 
	 */
	public static String CMS_TRADE_QRY_CHILD_MODEL_AREA_CONTENT = "qryChildModelAreaContent";
	
	//查询抽奖活动及奖项明细
	public static String CMS_TRADE_QRY_ACT_DRAW_INFO = "qryActDrawInfo";
	public static String CMS_TRADE_DO_ACT_DRAW = "doActDraw";

	//查询抽奖活动人员名单
	public static String CMS_TRADE_QRY_SEL_PLAYER_LIST = "selRegisterList";
	//查询抽奖活动人员名单
	public static String CMS_TRADE_QUERY_WINNER_LIST = "queryWinnerList";
}
