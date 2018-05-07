package com.fonseca.springmodconc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fonseca.springmodconc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7); 
		pagto.setDataVencimento(cal.getTime()); 
	}
}
