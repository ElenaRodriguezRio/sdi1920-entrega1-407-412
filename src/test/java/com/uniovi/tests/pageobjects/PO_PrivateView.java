package com.uniovi.tests.pageobjects;


import org.openqa.selenium.WebDriver;


public class PO_PrivateView extends PO_NavView {
	
	static public void loginGeneral(WebDriver driver, String email, String password) {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, email , password );
		PO_View.checkKey(driver, "usersInSystem.message", PO_Properties.getSPANISH());
	}
	
	static public void checkTextoHomePage(WebDriver driver, String email) {
		PO_View.checkKey(driver, "welcome.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "privatePartWeb.message", PO_Properties.getSPANISH());
		PO_View.checkElement(driver, "text", email);
	}
	
	
}