package com.wedding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wedding.util.S3Util;

@Controller
@RequestMapping(value = {"/our-wedding"}, produces = "application/json")
public class OurWeddingController {
	@GetMapping("")
    public String getRsvp() {
        return "our-wedding";
    }
	
	@GetMapping("/getWeddingParty")
	@ResponseBody
	public Map<String, String> getPhotoGallery() {
		Map<String, String> imageUrls = new HashMap<>();
		List<String> bucketList = S3Util.getS3BucketObjectList("img/wedding-party");
		for (String imageKey : bucketList) {
			if (imageKey.endsWith("-scaled.jpg")) {
				imageUrls.put(imageKey, S3Util.getS3ImageLink(imageKey));
			}
		}
		return imageUrls;
	}
}
