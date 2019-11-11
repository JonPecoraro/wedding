package com.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wedding.dao.GuestRepository;
import com.wedding.dao.GuestbookRepository;
import com.wedding.model.Guest;
import com.wedding.model.Guestbook;

@Controller
@RequestMapping(value = "/admin", produces = "application/json")
public class AdminController {
	@Autowired GuestRepository guestRepository;
	@Autowired GuestbookRepository guestbookRepository;
	
	@GetMapping("")
    public String index() {
		return "admin/index";
    }
	
	@GetMapping("/guest-list")
	public String showGuestRsvpList(Model model) {
		model.addAttribute("guests", guestRepository.findAll());
		return "admin/guest-list";
	}
	
	@GetMapping("/guestbook")
	public String showGuestbookEntries(Model model) {
		model.addAttribute("guestbook", guestbookRepository.findAll());
		return "admin/guestbook";
	}
	
	@PostMapping("/edit-guest/{id}")
	public String editGuest(String firstName, String lastName, String suffix, String email, String rsvpResponse, String foodChoice, String plansToUseRoomBlock, @PathVariable int id) {
		guestRepository.findById(id)
			.map(guest -> {
				guest.setEmail(email);
				guest.setFirstName(firstName);
				guest.setLastName(lastName);
				guest.setFoodChoice(foodChoice);
				guest.setPlansToUseRoomBlock(plansToUseRoomBlock);
				guest.setRsvpResponse(rsvpResponse);
				guest.setSuffix(suffix);
				return guestRepository.save(guest);
			})
			.orElseGet(() -> {
				return null;
			});
		
		return "redirect:/admin/guest-list";
	}
	
	@PostMapping("/edit-guestbook-entry/{id}")
	public String editGuestbookEntry(String name, String message, @PathVariable int id) {
		guestbookRepository.findById(id)
			.map(entry -> {
				entry.setMessage(message);
				entry.setName(name);
				return guestbookRepository.save(entry);
			})
			.orElseGet(() -> {
				return null;
			});
		
		return "redirect:/admin/guestbook";
	}
	
	@PostMapping("/delete-guest/{id}")
	public String deleteGuest(@PathVariable int id) {
		guestRepository.deleteById(id);
		return "redirect:/admin/guest-list";
	}
	
	@PostMapping("/delete-guestbook-entry/{id}")
	public String deleteGuestbookEntry(@PathVariable int id) {
		guestbookRepository.deleteById(id);
		return "redirect:/admin/guestbook";
	}
}
