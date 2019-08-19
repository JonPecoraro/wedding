package com.wedding.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class SesUtil {
	private static final Logger logger = LoggerFactory.getLogger(SesUtil.class);

	public static void sendEmail(String toAddress, String message) {
		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().build();
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(toAddress))
					.withMessage(
							new Message()
									.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(message))
											.withText(new Content().withCharset("UTF-8").withData(message)))
									.withSubject(
											new Content().withCharset("UTF-8").withData("Thank you for your RSVP")))
					.withSource("JonPecoraro@gmail.com");
			client.sendEmail(request);
			logger.info("SES message sent: {}", message);
		} catch (Exception e) {
			logger.error("SES message failed to send: {}", e);
		}
	}
}
