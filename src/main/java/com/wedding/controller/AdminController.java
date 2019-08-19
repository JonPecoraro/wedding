package com.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedding.dao.GuestRepository;
import com.wedding.dao.GuestbookRepository;

@Controller
@RequestMapping(value = "/admin", produces = "application/json")
public class AdminController {
	@Autowired GuestRepository guestRepository;
	@Autowired GuestbookRepository guestbookRepository;
	
	@GetMapping("")
	@PreAuthorize("hasAuthority('Wedding Admins')")
    public String index() {
		return "admin/index";
    }
	
	@GetMapping("/guest-list")
	@PreAuthorize("hasAuthority('Wedding Admins')")
	public String showGuestRsvpList(Model model) {
		model.addAttribute("guests", guestRepository.findAll());
		return "admin/guest-list";
	}
	
	@GetMapping("/guestbook")
	@PreAuthorize("hasAuthority('Wedding Admins')")
	public String showGuestbookEntries(Model model) {
		model.addAttribute("guestbook", guestbookRepository.findAll());
		return "admin/guestbook";
	}
}
