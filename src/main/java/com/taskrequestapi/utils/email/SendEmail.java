package com.taskrequestapi.utils.email;

import java.io.IOException;
import java.util.Date;

import com.sendgrid.*;

public class SendEmail {
	public static void sendEmail(String bodyEmail, String toEmail) throws IOException {
		Email from = new Email(System.getenv("EMAIL_SENDGRID"));
		String subject = "Request erro";
		Email to = new Email(toEmail);
		Content content = new Content("text/plain", new Date() + " Request erro: " + bodyEmail);
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_APIKEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
}
