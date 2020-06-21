package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RController {
	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "<h3>Immobi Spring</h3>";
	}
	
}
