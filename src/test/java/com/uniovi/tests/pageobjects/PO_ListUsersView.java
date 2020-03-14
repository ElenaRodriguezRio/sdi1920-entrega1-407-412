package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_ListUsersView extends PO_NavView{

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
	
	static public void checkTextoLista(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "h2", p.getString("userTitle.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("search.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("usersInSystem.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("emailColumn.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("nameColumn.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("lastNameColumn.message", language), getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("firstPage.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("lastPage.message", language), getTimeout()); 
	}
	
	static public void checkChangeLanguage(WebDriver driver, String textLanguage1, String textLanguage2, int locale1, int locale2 ) {
		PO_ListUsersView.checkTextoLista(driver, locale1);
		//Cambiamos a segundo idioma
		PO_ListUsersView.changeIdiom(driver,  textLanguage2);
		//Comprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_ListUsersView.checkTextoLista(driver, locale2);
		//Volvemos a Español.
		PO_ListUsersView.changeIdiom(driver, textLanguage1);
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_ListUsersView.checkTextoLista(driver, locale1); 
	 }
	
}
