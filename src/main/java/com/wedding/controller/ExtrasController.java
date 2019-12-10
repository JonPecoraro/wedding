package com.wedding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.wedding.dao.GuestbookRepository;
import com.wedding.model.Guestbook;
import com.wedding.util.S3Util;

@Controller
@RequestMapping(value = {"/extras"}, produces = "application/json")
public class ExtrasController {
	@Autowired GuestbookRepository guestbookRepository;
	
	@GetMapping("")
    public String getExtras(Model model) {
		model.addAttribute("guestbookEntries", guestbookRepository.findAllByPrivateMessageFalseOrderByDateCreatedDesc());
        return "extras";
    }
	
	@GetMapping("/saveGuestbookMessage")
	@ResponseBody
	public Guestbook saveGuestbookMessage(String name, String message, boolean privateMessage) {
		if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(message)) {
			// Ensure the values will fit into the database
			name = trimString(name, 255);
			message = trimString(message, 4095);
			
			// Save the guestbook entry
			Guestbook guestbookEntry = new Guestbook(name, message);
			guestbookEntry.setPrivateMessage(privateMessage);
			guestbookRepository.save(guestbookEntry);
			
			return guestbookEntry;
		}
		
		return null;
	}
	
	@GetMapping("/getPhotoGallery")
	@ResponseBody
	public Map<String, String> getPhotoGallery() {
		Map<String, String> imageUrls = new HashMap<>();
		List<String> bucketList = S3Util.getS3BucketObjectList("img/gallery/thumbnail");
		for (String thumbnailKey : bucketList) {
			String imageKey = thumbnailKey.replace("/thumbnail", "");
			imageUrls.put(S3Util.getS3ImageLink(thumbnailKey), S3Util.getS3ImageLink(imageKey));
		}
		return imageUrls;
	}
	
	/**
	 * Trims the given string to the specified size
	 * 
	 * @param s String to trim
	 * @param size Max size of the string. It can be less if the string is not that long
	 * @return The trimmed string
	 */
	private String trimString(String s, int size) {
		return s.substring(0, Math.min(s.length(), size));
	}
}
