package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_CreatePostView extends PO_View{

	public static void fillForm(WebDriver driver, String titulop, String textop) {
		//Titulo
		WebElement titulo = driver.findElement(By.name("titulo"));
		titulo.click();
		titulo.clear();
		titulo.sendKeys(titulop);
		//Texto
		WebElement texto = driver.findElement(By.name("texto"));
		texto.click();
		texto.clear();
		texto.sendKeys(textop);
		//Pulsar botón de submit
		By boton = By.className("btn");
		driver.findElement(boton).click();	
	}
}
