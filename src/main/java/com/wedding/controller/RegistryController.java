package com.wedding.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/registry"}, produces = "application/json")
public class RegistryController {	
	@Value("${paypal.oauth2.clientId}")
	private String paypalClientId;
	
	@GetMapping("")
    public String getRsvp(Model model) {
		model.addAttribute("paypalClientId", paypalClientId);
        return "registry";
    }
}
