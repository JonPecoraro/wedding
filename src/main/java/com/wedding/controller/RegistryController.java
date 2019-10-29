package com.wedding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/registry"}, produces = "application/json")
public class RegistryController {		
	@GetMapping("")
    public String getRsvp(Model model) {
        return "registry";
    }
}
