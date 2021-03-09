package com.bae.equationSaverApp.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Equations getEquationById(Long id) {
		Optional<Equations> optEquations = this.repo.findById(id);
		return optEquations.orElse(null);
	}

	@Override
	public Equations updateEquation(Long id, Equations newEquation) {
		Equations existing = this.getEquationById(id);

		existing.setEquationName(newEquation.getEquationName());
		existing.setEquation(newEquation.getEquation());
		existing.setDescription(newEquation.getDescription());
		existing.setSubject(newEquation.getSubject());

		return this.repo.save(existing);
	}

	@Override
	public boolean removeEquation(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
