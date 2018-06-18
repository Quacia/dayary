package com.inc.dayary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inc.dayary.service.DiaryService;

@Controller
public class DiaryController {
	@Autowired
	private DiaryService diaryService;
	
	@GetMapping("/")
	public String main(Model model) {
		System.out.println(diaryService.list().size());
		model.addAttribute("diaryList", diaryService.list());
		return "main";
	}
}
