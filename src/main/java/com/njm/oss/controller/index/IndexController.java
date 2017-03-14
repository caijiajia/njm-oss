package com.njm.oss.controller.index;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.njm.oss.controller.common.BaseController;

/**
 * Copyright: Copyright (c) 2015 yaoyaohao.com
 * 
 * @className: IndexController
 * @description: TODO

 * @version: v1.0.0
 * @author: caijiajia
 * @date: 2017-3-14
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {
	
	@RequestMapping("/initPage")
	public String init(Model model){
		model.addAttribute("can_export", "11");
		return "index";
	}
}
