package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ListUsersView {

	public static void fillSearchForm(WebDriver driver, String searchText) {
		//Text to search
		WebElement searchTextField = driver.findElement(By.name("searchText"));
		searchTextField.click();
		searchTextField.clear();
		searchTextField.sendKeys(searchText);
		//Pulsar botón de login
		By boton = By.id("search_button");
		driver.findElement(boton).click();
	}
	
}
