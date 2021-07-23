package com.cloud.mini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.mini.model.StoreStats;
import com.cloud.mini.repository.StoreStatsRepository;

@Controller
public class InfoController {
	@Autowired
	StoreStatsRepository storeStatsRepository;
	
	@GetMapping("/market_info")
	public String marketInfo() {
		return "market_info";
	}
	
	@GetMapping("/market_detail")
	public String marketDetail(@RequestParam("market") String market) {
		return "/market_detail/" + market;
	}
	
	@PostMapping("/market_detail")
	@ResponseBody
	public List<StoreStats> getStores(@RequestParam("market") String market){
		List<StoreStats> result = null;
		if(market.equals("bupyeongkkangtong")) {
			result = storeStatsRepository.findAllByStore(1);
		}
		else if(market.equals("busanjin")) {
			result = storeStatsRepository.findAllByStore(2);
		}
		return result;
	}
}
