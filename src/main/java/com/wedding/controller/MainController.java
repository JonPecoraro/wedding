package com.wedding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedding.util.S3Util;

@Controller
@RequestMapping(value = {"", "/"}, produces = "application/json")
public class MainController {	
	@GetMapping("/")
    public String getIndex() {
        return "index";
    }
	
	@GetMapping("/error")
    public String getError() {
        return "error";
    }
	
	@GetMapping("/geImageFromS3")
	@ResponseBody
	public String geImageFromS3(@RequestParam String key) {
		if (key.startsWith("/")) {
			key = key.replaceFirst("/", "");
		}
		if (!key.startsWith("img")) {
			key = "img/" + key;
		}
		return "{\"url\":\"" + S3Util.getS3ImageLink(key) + "\"}";
	}
}
