package com.fonseca.springmodconc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="trabalhos")
public class TrabalhoResource {

	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "REST trabalho esá funcionando!+";
	}
}
