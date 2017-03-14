package com.njm.oss.controller.login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.njm.oss.controller.common.BaseController;

/**
 * @ClassName: MqLogController  
 * @Description: MQ报文日志查询功能
 * @author 
 * @date 2015年11月3日 上午10:55:48
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@RequestMapping("/initPage")
	public String init(Model model){
		model.addAttribute("can_export", "11");
		return "login";
	}
}
