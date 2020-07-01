package com.example.demo;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendConfirmation(String to) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	        helper.setTo("boujemaa.wahid@gmail.com");
	        helper.setSubject("Confirmation d'email");
	        LocalDateTime l = LocalDateTime.now();
	        String date = l.getYear() + "-" + l.getMonthValue() + "-" + l.getDayOfMonth() + " " + l.getHour() +":"+l.getMinute();
			ObjectNode node = objectMapper.createObjectNode();
			node.put("email", to);
			node.put("date", date);
			String b64 = new String(Base64.getEncoder().encode(node.toString().getBytes()));
	        String signature = "http://localhost:4200/validation?signature="+b64;
	        String text = "<head>" + 
	        		"<style type=\"text/css\">"
	        		+ "a{" + 
	        		"    text-decoration: none;" + 
	        		"    padding: 1%;" + 
	        		"    background-color: #0275d8;" + 
	        		"    width: -webkit-fill-available;" + 
	        		"    color: white;" + 
	        		"    font-size: large;" + 
	        		"    font-weight: bolder;" + 
	        		"    text-align:center;"
	        		+ "	 cursor:pointer;" + 
	        		"}"
	        		+ "p{" + 
	        		"    color: cornflowerblue;" + 
	        		"    font-weight: bold;" + 
	        		"}"
	        		+ "strong{" + 
	        		"    color: #d9534f;" + 
	        		"    font-weight: bold;" + 
	        		"}" + 
	        		"sm{" + 
	        		"    font-size: small;" + 
	        		"    color: darkblue;" + 
	        		"}" + 
	        		"div{" + 
	        		"	display: inline-grid;" + 
	        		"	text-align:-webkit-center;" + 
	        		"}label{color:white;}"+
	        		"</style>" + 
	        		"</head>"
	        		+ "<div>" + 
	        		"<p>Veuillez confirmer votre email en cliquant sur le boutton ci dessous. " + 
	        		"Le lien sera expiré dans <strong>1 heure</strong> aprés son envoi.<br>" + 
	        		"<sm>Si vous attendez pas cette notification, veuillez ignorer ce message</sm></p>" + 
	        		"<a href='"+signature+"'><label>Confirmer mon email</label></a>"+
	        		"</div>";
	        
	    	helper.setText(text, true);
	        javaMailSender.send(mimeMessage);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
