package com.bae.equationSaverApp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bae.equationSaverApp.domain.Equations;
import com.bae.equationSaverApp.repo.EquationsRepo;
import com.bae.equationSaverApp.service.EquationsServiceDB;

@SpringBootTest
public class EquationsServiceDBUnitTest {

	@Autowired
	private EquationsServiceDB service;

	@MockBean
	private EquationsRepo repo;

	@Test
	void testCreate() {

		Equations newEquation = new Equations("Newton", "g-hc", "Second law", "Physics");
		Equations savedEquations = new Equations(1L, "Newton", "F=ma", "First law", "Physics");

		Mockito.when(this.repo.save(newEquation)).thenReturn(savedEquations);

		assertThat(this.service.createEquation(newEquation)).isEqualTo(savedEquations);

		Mockito.verify(this.repo, Mockito.times(1)).save(newEquation);
	}
	
	@Test 
	void testRead() {
		
		Equations savedEquations = new Equations(1L, "Newton", "F=ma", "First law", "Physics");
		List<Equations> allEquations = List.of(savedEquations);
		
		Mockito.when(this.repo.findAll()).thenReturn(allEquations);
		
		assertThat(this.service.getAllEquations()).isEqualTo(allEquations);
	}
}
