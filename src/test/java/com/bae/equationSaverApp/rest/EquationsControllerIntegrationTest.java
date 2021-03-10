package com.bae.equationSaverApp.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.equationSaverApp.domain.Equations;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:equations-schema.sql",
		"classpath:equations-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class EquationsControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {

		Equations newEquation = new Equations("Newton", "g-hc", "Second law", "Physics");
		String newEquationAsJSON = this.mapper.writeValueAsString(newEquation);

		RequestBuilder mockRequest = post("/createEquation").contentType(MediaType.APPLICATION_JSON)
				.content(newEquationAsJSON);

		Equations savedEquation = new Equations(2L, "Newton", "g-hc", "Second law", "Physics");
		String savedEquationAsJSON = this.mapper.writeValueAsString(savedEquation);

		ResultMatcher matchStatus = status().isOk();

		ResultMatcher matchBody = content().json(savedEquationAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void testRead() throws Exception {
		Equations testEquation = new Equations(1L, "First law", "F=ma", "Newton", "Physics");
		List<Equations> testListEquation = List.of(testEquation);
		String testEquationAsJSON = this.mapper.writeValueAsString(testListEquation);

		RequestBuilder mockRequest = get("/getAllEquations");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(testEquationAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {

		Equations updateEquation = new Equations("Feymann", "g-hc", "Second law", "Chemistry");
		String updateEquationAsJSON = this.mapper.writeValueAsString(updateEquation);

		RequestBuilder mockRequest = put("/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(updateEquationAsJSON);

		Equations updatedEquation = new Equations(1L, "Feymann", "g-hc", "Second law", "Chemistry");
		String updatedEquationAsJSON = this.mapper.writeValueAsString(updatedEquation);

		ResultMatcher matchStatus = status().isOk();

		ResultMatcher matchObject = content().json(updatedEquationAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchObject);
	}

	@Test
	void testDelete() throws Exception {

		RequestBuilder mockRequest = delete("/delete/1");

		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().string("true");

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

}