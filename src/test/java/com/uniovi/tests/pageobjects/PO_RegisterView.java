package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_RegisterView  extends PO_NavView {

	static public void fillForm(WebDriver driver, String dnip, String namep, String lastnamep, String passwordp, String passwordconfp) {
		WebElement email = driver.findElement(By.name("email"));
		email.click();
		email.clear();
		email.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("lastName"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));
		passwordConfirm.click();
		passwordConfirm.clear();
		passwordConfirm.sendKeys(passwordconfp);
		//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void checkTextoFormulario(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("registerAsUser.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("EmailTag.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nameTag.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("lastNameTag.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("passwordTag.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("repeatPasswordTag.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("send.message", language), getTimeout()); 
	}
	
	static public void checkChangeLanguage(WebDriver driver, String textLanguage1, String textLanguage2, int locale1, int locale2 ) {
		
		PO_RegisterView.checkTextoFormulario(driver, locale1);
		//Cambiamos a segundo idioma
		PO_RegisterView.changeIdiom(driver,  textLanguage2);
		//Comprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_RegisterView.checkTextoFormulario(driver, locale2);
		//Volvemos a Español.
		PO_RegisterView.changeIdiom(driver, textLanguage1);
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_RegisterView.checkTextoFormulario(driver, locale1); 
	 }
	
}
