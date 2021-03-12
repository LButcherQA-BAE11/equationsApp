package com.bae.equationSaverApp.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class equationsSeleniumTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
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

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
