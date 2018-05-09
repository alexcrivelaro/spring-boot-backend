package com.fonseca.springmodconc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.fonseca.springmodconc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj); 
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
