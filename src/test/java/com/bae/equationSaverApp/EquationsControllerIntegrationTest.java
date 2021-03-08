package com.bae.equationSaverApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

}