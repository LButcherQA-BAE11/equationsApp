package com.bae.equationSaverApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.equationSaverApp.domain.Equations;
import com.bae.equationSaverApp.repo.EquationsRepo;

@Service
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

	@Override
	public List<Equations> getAllEquations() {
		return this.repo.findAll();
	}

}
