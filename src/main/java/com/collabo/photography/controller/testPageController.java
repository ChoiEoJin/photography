package com.collabo.photography.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class testPageController {
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.GET)
	public String upload() {
		return "upload";
	}
}
