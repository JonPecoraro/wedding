package com.wedding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedding.dao.GuestRepository;

@RestController
public class HealthCheckController {
	private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);
	
	@Autowired GuestRepository guestRepository;
	
	@RequestMapping("/health-check")
	public HealthCheck healthCheck() {
		HealthCheck healthCheck = new HealthCheck();
		healthCheck.setWebsiteHealthy(true);
		try {
			healthCheck.setDatabaseHealthy(guestRepository.count() >= 0);
		} catch(Exception e) {
			healthCheck.setDatabaseHealthy(false);
			logger.error("Health check culd not connect to the database. It might be down.", e);
		}
		
		return healthCheck;
	}
	
	private class HealthCheck {
		private boolean websiteHealthy;
		private boolean databaseHealthy;
		
		/**
		 * @return the websiteHealthy
		 */
		public boolean isWebsiteHealthy() {
			return websiteHealthy;
		}
		
		/**
		 * @param websiteHealthy the websiteHealthy to set
		 */
		public void setWebsiteHealthy(boolean websiteHealthy) {
			this.websiteHealthy = websiteHealthy;
		}
		
		/**
		 * @return the databaseHealthy
		 */
		public boolean isDatabaseHealthy() {
			return databaseHealthy;
		}
		
		/**
		 * @param databaseHealthy the databaseHealthy to set
		 */
		public void setDatabaseHealthy(boolean databaseHealthy) {
			this.databaseHealthy = databaseHealthy;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "HealthCheck [websiteHealthy=" + websiteHealthy + ", databaseHealthy=" + databaseHealthy + "]";
		}
	}
}
