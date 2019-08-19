package com.wedding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
