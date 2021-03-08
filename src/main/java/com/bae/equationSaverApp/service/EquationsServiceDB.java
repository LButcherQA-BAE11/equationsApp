package com.bae.equationSaverApp.service;

import com.bae.equationSaverApp.domain.Equations;
import com.bae.equationSaverApp.repo.EquationsRepo;

public class EquationsServiceDB implements EquationsService {

	private EquationsRepo repo;

	public EquationsServiceDB(EquationsRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Equations createEquation(Equations equation) {

		return this.repo.save(equation);
	}

}
