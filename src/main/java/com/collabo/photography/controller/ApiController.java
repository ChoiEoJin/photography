package com.collabo.photography.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@RequestMapping(value = "/_api.do")
	public String api(Locale locale, Model model) {
		logger.debug("api UI로 이동");
		return "api";
	}

}
