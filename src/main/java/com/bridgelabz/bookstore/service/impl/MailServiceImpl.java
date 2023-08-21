package com.bridgelabz.bookstore.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.MailDTO;
import com.bridgelabz.bookstore.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
    JavaMailSender mailSender;
 
	@Override
    public void sendMail(MailDTO mail) {
		 SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom(mail.getMailFrom());
	        message.setTo(mail.getMailTo()); 
	        message.setSubject("Book Store OTP"); 
	        message.setText(mail.getMailContent());
	        mailSender.send(message);
    }
}
