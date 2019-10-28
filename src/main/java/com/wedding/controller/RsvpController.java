package com.wedding.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wedding.dao.GuestRepository;
import com.wedding.model.Guest;
import com.wedding.util.SesUtil;
import com.wedding.util.SmsUtil;

@Controller
@RequestMapping(value = {"/rsvp"}, produces = "application/json")
public class RsvpController {
	private static final Logger logger = LoggerFactory.getLogger(RsvpController.class);
	private final String YES_RESPONSE = "Yes";
	private final String NO_RESPONSE = "No";
	
	@Autowired GuestRepository guestRepository;
	
	@GetMapping("")
    public String getRsvp(Model model) {
		model.addAttribute("guest", new Guest());
		model.addAttribute("suffixOptions", new String[] {"", "Sr.", "Jr.", "III", "IV", "V"});
        return "rsvp";
    }
	
	@GetMapping("/get-guest-list")
	@ResponseBody
	public Iterable<Guest> getUsers() {		
		return guestRepository.findAll();
	}
	
	@PostMapping("/add-guest")
	public String addUser(@Valid @ModelAttribute Guest guest, @RequestParam String sendVerificationEmail, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "rsvp";
		}
		
		guestRepository.save(guest);
		redirectAttributes.addFlashAttribute("success", guest);
		
		String smsMessage = guest.getFirstName() + " " + guest.getLastName() + " " +
				(guest.getSuffix().length() > 0 ? guest.getSuffix() + " " : "") +
				"(" + guest.getEmail() + ") " +
				"RSVP'd " + guest.getRsvpResponse() + ". " +
				"Meal selection: " + (guest.getFoodChoice() != null ? guest.getFoodChoice() : "None") + ". " +
				"Plans to use room block: " + (guest.getPlansToUseRoomBlock() != null ? guest.getPlansToUseRoomBlock() : "No response") + ".";
		SmsUtil.sendMessage(smsMessage);
		
		if (sendVerificationEmail != null && sendVerificationEmail.equalsIgnoreCase(YES_RESPONSE)) {
			String emailMessage = "";
			if (guest.getRsvpResponse().equalsIgnoreCase(NO_RESPONSE)) {
				emailMessage = "We're sorry you won't be able to make it, but we understand. We'll miss you at the wedding.";
			} else {
				emailMessage = "We look forward to seeing you at the wedding.";
				if (guest.getFoodChoice() != null) {
					emailMessage += " We have your meal selection as " + guest.getFoodChoice() + ".";
				}
				if (guest.getPlansToUseRoomBlock() != null) {
					if (guest.getPlansToUseRoomBlock().equalsIgnoreCase(YES_RESPONSE)) {
						emailMessage += " We appreciate your interest in the room block. See you at the resort!";
					} else {
						emailMessage += " We see that you're not planning to use the room block. That's okay. There's a ton of great places to stay in the area. See you at the wedding!";
					}
				}
			}
			try
			{
				SesUtil.sendEmail(guest.getEmail(), emailMessage);
			} catch(Exception e) {
				logger.error("SES message failed to send: {}", e);
				redirectAttributes.addFlashAttribute("sesFailure", guest);
			}
		}
		
		return "redirect:/rsvp";
	}
}
