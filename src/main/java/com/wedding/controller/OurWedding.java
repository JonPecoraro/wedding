package com.wedding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/our-wedding"}, produces = "application/json")
public class OurWedding {
	@GetMapping("")
    public String getRsvp() {
        return "our-wedding";
    }
}
