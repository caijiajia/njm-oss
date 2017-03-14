package com.njm.oss.controller.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.njm.oss.common.util.EhcacheUtil;
import com.njm.oss.common.util.ServiceCode;
import com.njm.oss.common.util.UipServiceUtil;
import com.njm.oss.controller.common.BaseController;
import com.njm.oss.model.demo.Cate;
import com.njm.oss.model.demo.Prod;
import com.njm.oss.service.demo.CateService;
import com.njm.oss.service.demo.ProdService;

@Controller
@RequestMapping("/api/v1/demo")
public class RestDemoController extends BaseController {

	@Resource
	private CateService cateService;

	@Resource
	private ProdService prodService;

	@ResponseBody
	@RequestMapping("/index/floor/{cateIds}")
	public Map<String, Object> floor(@PathVariable String[] cateIds) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据产品编码查询产品信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("info", "调用通过");

		for (String cateId : cateIds) {
			System.out.println("调试输出----->分类编码 :" + cateId);

			List<Prod> prods = prodService.queryListByCateId(508);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("prods", prods);
			result.put(cateId, data);
		}

		return result;

	}

	@ResponseBody
	@RequestMapping("/cate/info/{cateId}")
	public Map<String, Object> getInfo(@PathVariable int cateId) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码查询分类树信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		log.debug("调试输出----->分类编码 :" + cateId);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 1);
		result.put("info", "调用通过");

		Cate cate = cateService.queryInfoByCateId(cateId);

		result.put("cate", cate);

		return result;
	}

	@ResponseBody
	@RequestMapping("/cate/list/{cateId}")
	public Map<String, Object> getList(@PathVariable int cateId) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码查询子分类树列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		log.debug("调试输出----->分类编码 :" + cateId);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 1);
		result.put("info", "调用通过");

		List<Cate> cates = cateService.queryListByParentId(cateId);

		result.put("cates", cates);

		return result;
	}

	@RequestMapping("/prod/model/{prodId}")
	public String getModel(@PathVariable String prodId, Model model) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据产品编码查询产品信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		log.debug("调试输出----->产品编码 :" + prodId);

		Prod prod = prodService.getInfoByProdId(prodId);
		model.addAttribute("prod", prod);

		return "prodInfo";
	}

	@RequestMapping("/prod/mv/{prodtId}")
	public ModelAndView getMV(@PathVariable String prodtId) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据产品编码查询产品信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		log.debug("调试输出----->产品编码 :" + prodtId);

		Prod prod = prodService.getInfoByProdId(prodtId);
		ModelAndView mv = new ModelAndView("prodInfo");
		mv.addObject("prod", prod);

		return mv;
	}

	@ResponseBody
	@RequestMapping("/prod/info/{prodId}")
	public Map<String, Object> getProdInfo(@PathVariable String prodId) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据产品编码查询产品信息");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		JSONObject json = new JSONObject();

		JSONArray merIdList = new JSONArray();
		JSONObject mer = new JSONObject();
//		mer.put("merchandiseId", "XLP00000002");
		mer.put("merchandiseId", 434);
		merIdList.add(mer);

		json.put("merIdList", merIdList);
		System.out.println("json :" + json);
		UipServiceUtil.callUip(ServiceCode.SERVICE_GOODS, ServiceCode.GOODS_TRADE_QUERY_MERCINFOS, json);

		log.debug("调试输出----->产品编码 :" + prodId);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 1);
		result.put("info", "调用通过");

		Prod prod = prodService.getInfoByProdId(prodId);
		result.put("prod", prod);

		try {
			log.debug("调试输出----->我先睡个5秒钟。。。");
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("/prod/list/{cateId}")
	public Map<String, Object> getProdList(@PathVariable int cateId) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		log.debug("调试输出----->分类编码 :" + cateId);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 1);
		result.put("info", "调用通过");

		try {
			log.debug("调试输出----->我先睡个5秒钟。。。");
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<Prod> prods = prodService.queryListByCateId(cateId);
		result.put("prods", prods);

		return result;
	}

	@ResponseBody
	@RequestMapping("/prod/pageList")
	public Map<String, Object> getProdPageList(HttpServletRequest request) {

		log.debug("+----------------------------------------------------------------------------------------------------+");
		log.debug("+                                                  根据分类编码分页查询分类下产品列表");
		log.debug("+----------------------------------------------------------------------------------------------------+");

		String cateId = request.getParameter("cateId");
		boolean needPage = Boolean.parseBoolean(request.getParameter("needCount"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		log.debug("调试输出----->接收页面参数，分类编码 :" + cateId);
		log.debug("调试输出----->接收页面参数，是否需要分页 :" + needPage);
		log.debug("调试输出----->接收页面参数，要查询的分页 :" + pageNum);
		log.debug("调试输出----->接收页面参数，每页展示行数 :" + pageSize);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cateId", cateId);
		param.put("needCount", needPage);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);

		Map<String, Object> result = prodService.queryListByPageParam(param);

		return result;
	}
	
	@ResponseBody
	@RequestMapping("/ehcache/getdata")
	public Map<String, Object> getEhcacheData(HttpServletRequest request) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cateId", "1");
		param.put("needCount", "2");
		param.put("pageNum", "3");
		param.put("pageSize", "4");

		EhcacheUtil.set("test", param);

		return (Map)EhcacheUtil.getObject("test");
	}

}
