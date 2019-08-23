# Wedding Website
- Hosted on EC2
- Uses CodeBuild to output artifacts to S3
- Environment variables defined on the EC2 instance: DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD, OKTA_APP_CLIENT_ID, OKTA_APP_CLIENT_SECRET, OKTA_API_TOKEN, SNS_TOPIC_ARN
- Developed using Java Spring Boot
- References a PostgreSQL database hosted on RDS
