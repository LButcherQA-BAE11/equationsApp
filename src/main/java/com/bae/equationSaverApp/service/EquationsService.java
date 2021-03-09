package com.bae.equationSaverApp.service;

import java.util.List;

import com.bae.equationSaverApp.domain.Equations;

public interface EquationsService {

	Equations createEquation(Equations equation);

	List<Equations> getAllEquations();
}
