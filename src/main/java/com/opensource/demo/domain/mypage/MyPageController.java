package com.opensource.demo.domain.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.opensource.demo.util.SecurityContextUtil;

@Controller
public class MyPageController {

	@GetMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("name", SecurityContextUtil.getUsername());
		model.addAttribute("email", SecurityContextUtil.getEmailAddress());
		return "mypage";
	}

}
