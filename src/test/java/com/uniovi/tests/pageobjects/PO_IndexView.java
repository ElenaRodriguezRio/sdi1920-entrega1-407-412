package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_IndexView extends PO_NavView {

	
	static public void checkWelcome(WebDriver driver, int language) {
		//Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout()); 
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("appName.message", language), getTimeout()); 
	}
	
	static public void checkChangeLanguage(WebDriver driver, String textLanguage1, String textLanguage2, int locale1, int locale2 ) {
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_IndexView.checkWelcome(driver, locale1);
		//Cambiamos a segundo idioma
		PO_IndexView.changeIdiom(driver,  textLanguage2);
		//COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_IndexView.checkWelcome(driver, locale2);
		//Volvemos a Español.
		PO_IndexView.changeIdiom(driver, textLanguage1);
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_IndexView.checkWelcome(driver, locale1); 
	 }
}
