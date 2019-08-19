package com.wedding.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;

@RestController
@RequestMapping(value = "/developer", produces = "application/json")
public class DeveloperController {
	private Client client;
	
	public DeveloperController(@Value("${okta.client.orgUrl}") String oktaUrl, @Value("${okta.client.token}") String apiToken) {
		client = Clients.builder()
				.setOrgUrl(oktaUrl)
				.setClientCredentials(new TokenClientCredentials(apiToken))
				.build();
	}
	
	@GetMapping("")
	@PreAuthorize("hasAuthority('Developers')")
    public String index() {
		return "Called with developer authority";
    }
	
	@GetMapping("/getUsers")
	@PreAuthorize("hasAuthority('SCOPE_email')")
	public String getUsers() {		
		return client.listUsers().toString();
	}
	
	@GetMapping("/getAuth")
	public String getAuth() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.toString();
	}
}
