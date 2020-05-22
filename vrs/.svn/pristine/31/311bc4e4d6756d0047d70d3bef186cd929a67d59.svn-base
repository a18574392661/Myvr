package com.bootdo.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/krpano")
@Controller
public class KrpanoController {
	private String prefix="krpano"  ;
	
	@GetMapping("")
	String tourDefault(Model model) {
		return prefix + "/tour";
	}
	
	@GetMapping("/tour")
	String tour(Model model) {
		return prefix + "/tour1";
	}
	
	@GetMapping("/krpanoself")
	String krpanoself(Model model) {
		return prefix + "/krpanoself";
	}
	
	@GetMapping("/tourmore")
	String tourmore(Model model) {
		return prefix + "/tourmore";
	}
	
	@GetMapping("/edit")
	String edit(Model model) {
		return prefix + "/tour_editor";
	}
	
	@GetMapping("/vtour")
	String vtour(Model model) {
		return "vtour/tour";
	}
	
	@GetMapping("/guide")
	String guide(Model model) {
		return "vtour/guide";
	}
	
	@GetMapping("/vtour2")
	String vtour2(Model model) {
		return "vtour/tour2";
	}

}
