package com.cloud.mini.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloud.mini.service.StoreDataService;

@Controller
public class HomeController {
	
	@GetMapping({"/", "/home"})
	public String index() {
		return "index";
	}
}