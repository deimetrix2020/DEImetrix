package com.java.app.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.java.app.beans.InviteUsersDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.beans.trans.TransUserDetailsDO;

@Controller
public class SendingMail 
{
	private static final Logger logger = Logger.getLogger(SendingMail.class);

	/**
	 * Register Sending mail
	 * 
	 * @param configDO
	 * @param trans_user_assign 
	 * @param verification_url 
	 */
	public boolean sendingMail(ServerConfigDO configDO, TransUserDetailsDO trans_user_assign, String verification_url)
	{
		boolean bStatus = false;
		try 
		{
			String html = "<html>"
							+ "<body style='font-family: Calibri,Candara,Segoe,Segoe UI,Optima,Arial,sans-serif;'>" 
							+ "Dear "+trans_user_assign.getsFirstName()+","
							+ "<p>You are almost ready to take the DEImetrix&trade; assessment. Please verify your email address by clicking on the link below.</p>"
							+ "<p><a href='"+verification_url+"' target='_blank'>Click to Verify Email Address</a></p>";
				html += "<p>Thank you,</p>"+
						"<p>Your DEImetrix&trade; Team<p>";
				html += "</body></html>";
			
			String subject_txt = "DEImetrix Email Confirmation";
			logger.info("Mail Verification URL : " + verification_url);
			bStatus = common_mail_structre(configDO, html, trans_user_assign.getsEmailId(), subject_txt);
		} catch (Exception e) 
		{
			bStatus = false;
			logger.info("Error sending mail : " + e.getMessage());
		}
		return bStatus;
	}

	/**
	 * Bulk Invite send mail
	 * 
	 * @param configDO
	 * @param usersDO
	 * @param verification_url
	 * @return
	 */
	public boolean invite_sendingMail(ServerConfigDO configDO, InviteUsersDO usersDO, String verification_url) 
	{
		boolean bStatus = false;
		try 
		{
			String html = "<html>"
							+ "<body style='font-family: Calibri,Candara,Segoe,Segoe UI,Optima,Arial,sans-serif;'>" 
							+ "Dear "+usersDO.getsFirstName()+","
							+ "<p>DEImetrix&trade; is designed to assess your beliefs, attitudes and preferences toward Diversity, Equity & Inclusion based on your daily interactions in the workplace.</p>"
							+ "<p>Thank you for taking the time to complete this assessment tool. Your assessment answers and results will be completely confidential.</p>"
							+ "<p>In order for the results to reflect your behaviors as accurately as possible and for you to get the most out of this assessment process, please set aside approximately 15-20 minutes of uninterrupted time to complete the assessment.</p>"
							+ "<p>In order to access the DEImetrix&trade; assessment, please click <a href='"+verification_url+"' target='_blank'>Take the survey.</a></p>"
							+ "<p>Upon survey completion, your assessment report will be generated that highlights your overall DEI score.</p>";
			html += "<p>Sincerely,</p>"+
					"<p>Your DEImetrix&trade; Team<p>";
			html += "</body></html>";
			
			String subject_txt = "DEImetrix Survey Invitation !!";
			logger.info("Mail Verification URL : " + verification_url);
			bStatus = common_mail_structre(configDO, html, usersDO.getsEmailId(), subject_txt);
		} catch (Exception e) 
		{
			bStatus = false;
			logger.info("Error sending mail : " + e.getMessage());
		}
		return bStatus;
	}
	
	/**
	 * Send ail fuction common
	 * 
	 * @param configDO
	 * @param html
	 * @param toEmailid
	 * @param subject_txt 
	 * @return
	 */
	private boolean common_mail_structre(ServerConfigDO configDO, String html, String toEmailid, String subject_txt)
	{
		InternetAddress[] to = null;
		boolean bStatus = false;
		try 
		{
			final String from_mail_id = configDO.getsFromMailId();
			final String mail_password = configDO.getsFromMailPassword();
			Properties props = new Properties();
			props.put("mail.smtp.auth", configDO.getsSMTPAuth());
			props.put("mail.debug", configDO.getsSMTPDebug());
			props.put("mail.smtp.starttls.enable", configDO.getsSMTPStartTls());
	        props.put("mail.smtp.host", configDO.getsSMTPHostName());
	        props.put("mail.smtp.port", configDO.getsSMTPHostNumber());
	        props.put("mail.transport.protocol", "smtp");
	        
	        logger.info("Auth : " + configDO.getsSMTPAuth());
	        logger.info("Debug : " + configDO.getsSMTPDebug());
	        logger.info("starttls : " + configDO.getsSMTPStartTls());
	        logger.info("user : " + configDO.getsFromMailId());
	        logger.info("password : " + configDO.getsFromMailPassword());
	        logger.info("host : " + configDO.getsSMTPHostName());
	        logger.info("port : " + configDO.getsSMTPHostNumber());
	        Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() 
				{
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from_mail_id, mail_password);
				}
			  });
			logger.info("User Name and Password validation is Succces...");
			
			to = InternetAddress.parse(toEmailid.trim() , true);
			logger.info("to mail : " + to);
			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			MimeBodyPart body = new MimeBodyPart(headers, html.getBytes("UTF-8"));
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from_mail_id));
			message.addRecipients(Message.RecipientType.TO, to);
			message.setSubject(subject_txt, "text/html; charset=utf-8");
			message.setSentDate(new Date());
			logger.info("Setting MimeBodypart is Success..");
			Multipart multipart = new MimeMultipart("related");  
		    multipart.addBodyPart(body);
		    logger.info("Setting multipart is Success..");
		    message.setContent(html, "text/html; charset=utf-8");
		    message.setContent(multipart);
			logger.info("Sending mail..");
			Transport.send(message);
			logger.info("Sent mail successfully.");
			bStatus = true;
		} catch (Exception e) 
		{
			bStatus = false;
			logger.info("Error sending mail : " + e.getMessage());
		}
		return bStatus;
	}
}
