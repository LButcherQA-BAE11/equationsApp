package com.bae.equationSaverApp.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.equationSaverApp.domain.Equations;
import com.bae.equationSaverApp.service.EquationsService;

@RestController
public class EquationsController {

	private EquationsService service;

	public EquationsController(EquationsService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createEquation")
	public Equations createEquation(@RequestBody Equations equation) {
		return this.service.createEquation(equation);
	}
}
