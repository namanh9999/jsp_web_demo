package Util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailProcess {
	public void sendEmail(String emailRecipient, String contents, String subject) {

		// lean more at sendEmail Project
		final String email = "tvu183663@gmail.com";
		final String pass = "zvfmiaiajmmedxeo";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, pass);
			}

		};
		Session session = Session.getDefaultInstance(prop, auth);

		final String to = emailRecipient;
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setFrom(email);
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject(subject);
			msg.setContent(contents, "text/html");
			Transport.send(msg);
			System.out.print(" send Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
