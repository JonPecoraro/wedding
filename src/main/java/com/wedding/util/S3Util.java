package com.wedding.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Component
public class S3Util
{
	private static final Logger logger = LoggerFactory.getLogger(S3Util.class);
	private static final String bucketName = "jons-wedding-website";
	
	public static String getS3ImageLink(String relativeUrl) {
		String objectKey = relativeUrl;
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
		
		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
        
		GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
			.withMethod(HttpMethod.GET)
			.withExpiration(expiration);
		
		URL url = s3Client.generatePresignedUrl(urlRequest);
		
		logger.debug("Calculated S3 URL to be {}", url.toString());
		
		return url.toString();
	}
	
	public static List<String> getS3BucketObjectList(String relativeUrl) {
		List<String> objectList = new ArrayList<>();
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
		
		try {
			ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(relativeUrl);
			ListObjectsV2Result result = s3Client.listObjectsV2(req);
			for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
				objectList.add(objectSummary.getKey());
			}
		} catch (Exception e) {
			logger.error("Error retrieving S3 bucket object list", e);
		}
		
		return objectList;
	}
}
