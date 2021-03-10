package com.bae.equationSaverApp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bae.equationSaverApp.domain.Equations;
import com.bae.equationSaverApp.repo.EquationsRepo;

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

	@Test
	void testUpdate() {
		Long id = 1L;

		Equations newEquation = new Equations("Newton", "g-hc", "Second law", "Physics");
		Optional<Equations> optionalEquation = Optional.of(new Equations(id, "Newton", "F=ma", "First law", "Physics"));
		Equations updatedEquation = new Equations(id, newEquation.getEquationName(), newEquation.getEquation(),
				newEquation.getDescription(), newEquation.getSubject());

		Mockito.when(this.repo.findById(id)).thenReturn(optionalEquation);
		Mockito.when(this.repo.save(updatedEquation)).thenReturn(updatedEquation);

		assertThat(this.service.updateEquation(id, newEquation)).isEqualTo(updatedEquation);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedEquation);

	}

	@Test
	void testDelete() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);

		assertThat(this.service.removeEquation(id)).isEqualTo(false);

	}

	@Test
	void testDeleteInvalid() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.removeEquation(id)).isEqualTo(true);

	}

}
