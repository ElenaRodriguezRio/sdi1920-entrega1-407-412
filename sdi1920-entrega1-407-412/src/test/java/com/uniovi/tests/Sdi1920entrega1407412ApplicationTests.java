package com.uniovi.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

import org.junit.runners.MethodSorters;

import org.junit.*;

//Ordenación de pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sdi1920entrega1407412ApplicationTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\maria\\Desktop\\SDI 5\\PL-SDI-Sesion5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1
	// y desactivar las actualizacioens automáticas): //static String PathFirefox65
	// = "/Applications/Firefox.app/Contents/MacOS/firefox-bin"; //static String
	// Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac"; //Común a
	// Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega a la URL home de la aplicación
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	// Tras cada prueba se borran las cookies del navegador
	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba (antes de todas las pruebas)
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba (al finalizarlas todas)
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	//// CASOS DE PRUEBA ////

	// Casos de prueba 1-4: Registro de usuario (SignUp) //

	// PR01. Registro de usuario con datos válidos
	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "antonio@gmail.com", "Antonio", "Antuña", "holas", "holas");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Bienvenido a tu zona privada");
		PO_View.checkElement(driver, "text", "antonio@gmail.com");
	}

	// PR02. Registro de usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos)
	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario, dejando vacío el campo de email
		PO_RegisterView.fillForm(driver, "", "Marta", "González", "holas", "hola");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");

		// Rellenamos el formulario, dejando vacío el campo de nombre
		PO_RegisterView.fillForm(driver, "marta@uniovi.es", "", "González", "holas", "hola");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");

		// Rellenamos el formulario, dejando vacío el campo de apellido
		PO_RegisterView.fillForm(driver, "marta@uniovi.es", "Marta", "", "holas", "hola");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");

	}

	// PR03. Registro de usuario con datos inválidos (repetición de contraseña
	// inválida)
	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "marta@uniovi.es", "Marta", "González", "holas", "hola");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		// Comprobamos el error de contraseñas que no coinciden.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	// PR04. Registro de usuario con datos inválidos (email existente)
	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "pedro99@uniovi.es", "Pedro", "González", "holas", "holas");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		// Comprobamos el error de email repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	// Casos de prueba 5-8: Identificación de usuario (Login) //

	// PR05. Inicio de sesión con datos válidos (administrador)
	@Test
	public void PR05() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Comprobamos que entramos en la vista de listado de todos los usuarios del
		// sistema
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
	}

	// PR06. Inicio de sesión con datos válidos (usuario estándar)
	@Test
	public void PR06() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "123456");
		// Comprobamos que entramos en la vista de listado de todos los usuarios del
		// sistema
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
	}

	// PR07. Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos)
	@Test
	public void PR07() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		// Comprobamos que entramos en la vista de listado de todos los usuarios del
		// sistema
		PO_View.checkElement(driver, "text", "Identifícate");
		PO_View.checkElement(driver, "text", "Login");
	}

	// PR08. Inicio de sesión con datos inválidos (usuario estándar, email existente
	// pero contraseña incorrecta)
	@Test
	public void PR08() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "holas");
		// Comprobamos que entramos en la vista de listado de todos los usuarios del
		// sistema
		PO_View.checkElement(driver, "text", "Identifícate");
		PO_View.checkElement(driver, "text", "Login");
	}

	// Casos de prueba 9-10: Desconexión de usuarios registrados //

	// PR09. Clic en opción salir de sesión, comprobar que se redirige a página de
	// inicio sesión
	@Test
	public void PR09() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "123456");
		// Comprobamos que entramos en la vista de listado de todos los usuarios del
		// sistema
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:");
		//Comprobamos que, dado que el usuario está autenticado, se ve el link de logout
		Assert.assertTrue(PO_View.isVisibleElement(driver, "#logout_link"));
		//Desconectarse
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		//Comprobar que nos encontramos en login
		PO_View.checkElement(driver, "text", "Identifícate");
		//Comprobar que, una vez salidos de sesión, no vemos el link de logout
		Assert.assertFalse(PO_View.isPresentElement(driver, By.id("logout_link")));
	}

	// PR10. Comprobar que el botón inicio de sesión no está visible si el usuario
	// no está autenticado
	@Test
	public void PR10() {
		//Comprobar que, si no estamos autenticados, no vemos el link de logout
		Assert.assertFalse(PO_View.isPresentElement(driver, By.id("logout_link")));
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Seguimos sin ver el link de logout
		Assert.assertFalse(PO_View.isPresentElement(driver, By.id("logout_link")));
	}

	//// FIN DE CASOS DE PRUEBA ////

}
