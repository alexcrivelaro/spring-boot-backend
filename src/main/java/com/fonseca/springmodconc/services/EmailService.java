package com.fonseca.springmodconc.services;

import org.springframework.mail.SimpleMailMessage;

import com.fonseca.springmodconc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj); 
	
	void sendEmail(SimpleMailMessage msg);
}
