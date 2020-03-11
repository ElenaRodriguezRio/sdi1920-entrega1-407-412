package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_View {
	
	protected static PO_Properties p = new PO_Properties("messages");
	protected static int timeout = 2;

	public static int getTimeout() {
		return timeout;
	}

	public static void setTimeout(int timeout) {
		PO_View.timeout = timeout;
	}

	public static PO_Properties getP() {
		return p;
	}

	public static void setP(PO_Properties p) {
		PO_View.p = p;
	}
	
	/**
	 * Espera por la visibilidad de un texto correspondiente a la propiedad key en el idioma locale en la vista actualmente cargandose en driver..
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param key: clave del archivo de propiedades.
	 * @param locale: Retorna el índice correspondient al idioma. 0 p.SPANISH y 1 p.ENGLISH.
	 * @return Se retornará la lista de elementos resultantes de la búsqueda.
	 */
	static public List<WebElement> checkKey(WebDriver driver, String key, int locale) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString(key, locale), getTimeout());
		return elementos;
	}
	/**
	 *  Espera por la visibilidad de un elemento/s en la vista actualmente cargandose en driver..
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param type: 
	 * @param text:
	 * @return Se retornará la lista de elementos resultantes de la búsqueda.
	 */
	static public List<WebElement> checkElement(WebDriver driver, String type, String text) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, type, text, getTimeout());
		return elementos;		
	}
	
	
	/**
	 * Detecta si un elemento está presente o no
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @return Se retorna true si el elemento está presente, y falso en caso contrario
	 */
	static public boolean isPresentElement(WebDriver driver, By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch(NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * Detecta si un elemento está visible o no
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @return Se retorna true si el elemento está visible, y falso en caso contrario
	 */
	static public boolean isVisibleElement(WebDriver driver, String locator) {
		return driver.findElement(By.cssSelector(locator)).isDisplayed();
	}
	
}
