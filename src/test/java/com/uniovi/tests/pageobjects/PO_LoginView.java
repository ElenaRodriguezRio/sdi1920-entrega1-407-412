package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_LoginView extends PO_NavView {


	public static void fillForm(WebDriver driver, String emailp, String passwordp) {
		//Email
		WebElement email = driver.findElement(By.name("username"));
		email.click();
		email.clear();
		email.sendKeys(emailp);
		//Password
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		//Pulsar botón de login
		By boton = By.className("btn");
		driver.findElement(boton).click();	
	}
	
	static public void checkTextoFormulario(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("login.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("EmailTag.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("passwordTag.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("loginTag.message", language), getTimeout()); 
	}
	
	static public void checkChangeLanguage(WebDriver driver, String textLanguage1, String textLanguage2, int locale1, int locale2 ) {
		
		PO_LoginView.checkTextoFormulario(driver, locale1);
		//Cambiamos a segundo idioma
		PO_LoginView.changeIdiom(driver,  textLanguage2);
		//Comprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_LoginView.checkTextoFormulario(driver, locale2);
		//Volvemos a Español.
		PO_LoginView.changeIdiom(driver, textLanguage1);
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_LoginView.checkTextoFormulario(driver, locale1); 
	 }
	
}
