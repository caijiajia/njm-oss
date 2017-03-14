package com.njm.oss.controller.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.njm.oss.controller.common.BaseController;

/**
 * @ClassName: MqLogController  
 * @Description: MQ报文日志查询功能
 * @author liujianzhu
 * @date 2015年11月3日 上午10:55:48
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
	
	@RequestMapping("/index")
	public String init(Model model){
		model.addAttribute("can_export", "11");
		return "demo/prodInfo";
	}
}
