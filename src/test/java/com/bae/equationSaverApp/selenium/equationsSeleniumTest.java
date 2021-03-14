package com.bae.equationSaverApp.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:equations-schema.sql",
		"classpath:equations-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class equationsSeleniumTest {

	@LocalServerPort
	private int port;

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().maximize();
	}

	@Test
	void testOpen() throws InterruptedException {
		this.driver.get("http://127.0.0.1:5501/index.html?");

		Thread.sleep(2000);

		WebElement title = this.driver.findElement(By.xpath("//*[@id=\"myHeader\"]/h2"));

		assertThat(title.getText()).contains("Equation Notes");

	}

	@Test
	void testCreate() {
		this.driver.get("http://127.0.0.1:5501/index.html?");

		WebElement addSubject = this.driver.findElement(By.xpath("//*[@id=\"equationSubject\"]"));

		addSubject.sendKeys("Physics");

		WebElement addEquation = this.driver.findElement(By.xpath("//*[@id=\"equation\"]"));

		addEquation.sendKeys("KE = 0.5mv^2");

		WebElement addEquationName = this.driver.findElement(By.xpath("//*[@id=\"equationName\"]"));

		addEquationName.sendKeys("Kinetic Energy");

		WebElement addNotes = this.driver.findElement(By.xpath("//*[@id=\"equationDesc\"]"));

		addNotes.sendKeys("Where m is the mass and v is the velocity.");

		WebElement addButton = this.driver.findElement(By.xpath("//*[@id=\"addBtn\"]"));

		addButton.click();

		WebElement succesfulAdd = this.driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[1]"));

		Assertions.assertTrue(succesfulAdd.getText().contains("Added!"));

	}

	@Test
	void testDelete() {
		this.driver.get("http://127.0.0.1:5501/index.html?");

		WebElement deleteButton = this.driver.findElement(By.xpath("//*[@id=\"deleteBtn\"]"));

		deleteButton.click();

		WebElement deletedCard = this.driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[1]"));

		Assertions.assertTrue(deletedCard.getText().contains("Equation card deleted!"));
	}

	@Test
	void testUpdate() {
		this.driver.get("http://127.0.0.1:5501/index.html?");

		WebElement updateButton = this.driver.findElement(By.xpath("//*[@id=\"updateBtn\"]"));

		updateButton.click();

		WebElement modalInput = this.driver.findElement(By.xpath("//*[@id=\"modalEquationSubject\"]"));

		modalInput.clear();

		modalInput.sendKeys("Maths");

		WebElement modalUpdateButton = this.driver.findElement(By.xpath("//*[@id=\"modalUpdateBtn\"]"));

		modalUpdateButton.click();

		WebElement deletedCard = this.driver.findElement(By.xpath("//*[@id=\"myHeader\"]/div[1]"));

		Assertions.assertTrue(deletedCard.getText().contains("Updated"));

	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
