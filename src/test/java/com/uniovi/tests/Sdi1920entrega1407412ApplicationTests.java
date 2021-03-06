package com.uniovi.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_CreatePostView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_IndexView;
import com.uniovi.tests.pageobjects.PO_ListUsersView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.*;

//Ordenación de pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sdi1920entrega1407412ApplicationTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "./PL-SDI-material/geckodriver024win64.exe";
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
		// Comprobamos que entramos en la vista de listado de usuarios
		PO_ListUsersView.checkKey(driver, "usersInSystem.message", PO_Properties.getSPANISH());
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
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Rellenamos el formulario, dejando vacío el campo de nombre
		PO_RegisterView.fillForm(driver, "marta@uniovi.es", "", "González", "holas", "hola");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Rellenamos el formulario, dejando vacío el campo de apellido
		PO_RegisterView.fillForm(driver, "marta@uniovi.es", "Marta", "", "holas", "hola");
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

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
		PO_ListUsersView.checkKey(driver, "usersInSystem.message", PO_Properties.getSPANISH());
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
		PO_ListUsersView.checkKey(driver, "usersInSystem.message", PO_Properties.getSPANISH());
	}

	// PR07. Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos)
	@Test
	public void PR07() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		PO_LoginView.checkKey(driver, "Error.login.incorrectData", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "identifyYourself.message", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "loginTag.message", PO_Properties.getSPANISH());
	}

	// PR08. Inicio de sesión con datos inválidos (usuario estándar, email existente
	// pero contraseña incorrecta)
	@Test
	public void PR08() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "holas");
		PO_LoginView.checkKey(driver, "Error.login.incorrectData", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "identifyYourself.message", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "loginTag.message", PO_Properties.getSPANISH());
	}

	// Casos de prueba 9-10: Desconexión de usuarios registrados //

	// PR09. Clic en opción salir de sesión, comprobar que se redirige a página de
	// inicio sesión
	@Test
	public void PR09() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
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
	
	// Caso de prueba 11: Comprobaciones de sistema de paginación de listado de usuarios //
	
	// PR11a: Comprobación de sistema de paginación de listado de usuarios, autentificándose como usuario administrador
	// En el sistema hay siete usuarios estándar + 1 que añadimos en previas validaciones y uno administrador, por lo que habrá dos páginas, una de ellas llena y la otra con 1 usuario
	// Se visualizarán todos los usuarios estándar del sistema
	@Test
	public void PR11a() {
		PO_PrivateView.loginGeneral(driver, "admin@email.com", "admin");
		//Contamos el número de filas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 5);
		//Comprobamos que tenemos sistema de paginación
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		//Nos vamos a la última página (2)
		elementos.get(2).click();
		//Comprobamos que hay dos usuarios
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 3);
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	// PR11b: Comprobación de sistema de paginación de listado de usuarios, autentificándose como usuario estándar
	// En el sistema hay siete usuarios estándar + 1 que añadimos en previas validaciones, y uno administrador, por lo que habrá dos páginas, una de ellas llena y la otra con 1 usuario
	// Se visualizarán todos los usuarios estándar del sistema menos el usuario autenticado en estos momentos
	@Test
	public void PR11b() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Contamos el número de filas de notas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 5);
		//Comprobamos que tenemos sistema de paginación
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		//Nos vamos a la última página (2)
		elementos.get(2).click();
		//Comprobamos que hay un solo usuario
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 2);
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	// Casos de prueba 12-14: Pruebas de búsqueda en listado de usuarios //
	
	// PR12. Búsqueda con campo vacío -> listado completo
	@Test
	public void PR12() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Introducimos un texto vacío en el formulario de búsqueda
		PO_ListUsersView.fillSearchForm(driver, "");
		//Comprobamos que se visualiza la lista completa de usuarios del sistema
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 5);
		//Comprobamos que tenemos sistema de paginación
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		//Nos vamos a la última página (2)
		elementos.get(2).click();
		//Comprobamos que hay un solo usuario
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 2);
		
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	// PR13. Búsqueda con texto no existente -> listado vacío
	@Test
	public void PR13() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Introducimos un texto no existente en el formulario de búsqueda
		PO_ListUsersView.fillSearchForm(driver, "agjdg");
		//Comprobamos que se visualiza una lista vacía
		Assert.assertFalse(PO_View.isPresentElement(driver, By.className("tr")));

		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	// PR14. Búsqueda con texto específico (sí existe) -> listado usuarios en que el texto especificado es parte del nombre/apellidos/email
	// Extra: realizar comprobaciones para nombre, apellido, email
	@Test
	public void PR14() {
		//Nos logueamos como usuario estándar
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Introducimos un texto válido en el formulario de búsqueda: nombre Marta -> la otra Marta es administradora, solo nos va a aparecer la que
		//es standard user!!
		PO_ListUsersView.fillSearchForm(driver, "Marta");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 1);
		//Introducimos un texto válido en el formulario de búsqueda: searchString Mar
		PO_ListUsersView.fillSearchForm(driver, "Mar");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 1);
		
		//Introducimos un texto válido en el formulario de búsqueda: apellido Antuña
		PO_ListUsersView.fillSearchForm(driver, "Antuña");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 2);
		//Introducimos un texto válido en el formulario de búsqueda: searchString Ant
		PO_ListUsersView.fillSearchForm(driver, "Ant");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 2);
		
		//Introducimos un texto válido en el formulario de búsqueda: email completo carlos@uniovi.es
		PO_ListUsersView.fillSearchForm(driver, "carlos@uniovi.es");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 1);
		//Introducimos un texto válido en el formulario de búsqueda: trozo email oo
		PO_ListUsersView.fillSearchForm(driver, "oo");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 1);
		//Introducimos un texto válido en el formulario de búsqueda: searchString uniovi.es
		PO_ListUsersView.fillSearchForm(driver, "uniovi.es");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 5);
		//Comprobamos que tenemos sistema de paginación
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		//Nos vamos a la última página (2)
		elementos.get(2).click();
		//Comprobamos que hay un solo usuario
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 2);
		
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	//[Prueba15] Desde el listado de usuarios de la aplicación, enviar una invitación de amistad a un usuario. 
	//Comprobar que la solicitud de amistad aparece en el listado de invitaciones (punto siguiente).
	
	@Test
	public void PR15() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario como alejandro
		PO_LoginView.fillForm(driver, "alejandroo@uniovi.es", "123456");
		//vamos a las peticiones de amistad
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Peticiones de Amistad").get(0).click();
		//PO_NavView.clickOption(driver, "user/requestList", "id", "requests");
		//comprobamos que pedro no nos ha mandado peticion
		try {
			PO_View.checkElement(driver,"text", "Pedro");
			fail();
		} catch(Exception e) {
		}
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario como pedro
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "123456");
		//vamos a la lista de usuarios
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Usuarios").get(0).click();
		//mandar peticion a alejandro
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		elementos.get(2).findElement(By.id("send")).click();
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario como alejandro
		PO_LoginView.fillForm(driver, "alejandroo@uniovi.es", "123456");
		//vamos a la lista de peticiones
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Peticiones de Amistad").get(0).click();
		//comprovamos que pedro no ha mandado una
		PO_View.checkElement(driver, "text", "Pedro");
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	
	
	//[Prueba16] Desde el listado de usuarios de la aplicación, enviar una invitación de amistad a un usuario 
	//al que ya le habíamos enviado la invitación previamente. No debería dejarnos enviar la invitación, 
	//se podría ocultar el botón de enviar invitación o notificar que ya había sido enviada previamente.
	
	@Test
	public void PR16() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "carlos@uniovi.es", "123456");
		//vamos a la lista de usuarios
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Usuarios").get(0).click();
		//mandar peticion a alejandro
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		elementos.get(3).findElement(By.id("send")).click();
		//mandar otra peticion a alejandro
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		elementos.get(3).findElement(By.id("send")).click();
		//comprovamos que es la pagina de error correspondiente
		PO_View.checkElement(driver, "text", "La peticion ya ha sido mandada");
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	
	//[Prueba17] Mostrar el listado de invitaciones de amistad recibidas. 
	//Comprobar con un listado que contenga varias invitaciones recibidas.
	
	@Test
	public void PR17() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario como alejandro
		PO_LoginView.fillForm(driver, "alejandroo@uniovi.es", "123456");
		//vamos a la lista de usuarios
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Usuarios").get(0).click();
		//mandar peticion a irene
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		elementos.get(4).findElement(By.id("send")).click();
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario como pedro
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "123456");
		//vamos a la lista de usuarios
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Usuarios").get(0).click();
		//mandar peticion a irene
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		elementos.get(4).findElement(By.id("send")).click();
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario como irene
		PO_LoginView.fillForm(driver, "irene@uniovi.es", "123456");
		//vamos a la lista de peticiones
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Peticiones de Amistad").get(0).click();
		//comprovamos que pedro ha mandado una
		PO_View.checkElement(driver, "text", "Pedro");
		//comprovamos que alejandro ha mandado una
		PO_View.checkElement(driver, "text", "Alejandro");
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	//[Prueba18] Sobre el listado de invitaciones recibidas. Hacer click en el botón/enlace de una de ellas
	//y comprobar que dicha solicitud desaparece del listado de invitaciones.
	
	@Test
	public void PR18() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario como irene
		PO_LoginView.fillForm(driver, "irene@uniovi.es", "123456");
		//vamos a la lista de peticiones
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Peticiones de Amistad").get(0).click();
		//comprovamos que pedro ha mandado una
		PO_View.checkElement(driver, "text", "Pedro");
		//comprovamos que alejandro ha mandado una
		PO_View.checkElement(driver, "text", "Alejandro");
		//aceptamos la de alejandro
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		elementos.get(1).findElement(By.id("accept")).click();
		//comprobamos que la de pedro esta
		PO_View.checkElement(driver, "text", "Pedro");
		//comprobamos que la de alejandro no está
		try {
			PO_View.checkElement(driver,"text", "Alejandro");
			fail();
		} catch(Exception e) {
		}
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	//[Prueba19] Mostrar el listado de amigos de un usuario. Comprobar que el listado contiene los amigos que deben ser.
	
	@Test
	public void PR19() {
		// Vamos al formulario de login.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario como irene
		PO_LoginView.fillForm(driver, "irene@uniovi.es", "123456");
		//vamos a la lista de amigos
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Amigos").get(0).click();
		//comprovamos que alejandro es nuestro amigo
		PO_View.checkElement(driver, "text", "alejandroo@uniovi.es");
		PO_View.checkElement(driver, "text", "Alejandro");
		PO_View.checkElement(driver, "text", "González");
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario como alejandro
		PO_LoginView.fillForm(driver, "alejandroo@uniovi.es", "123456");
		//vamos a la lista de amigos
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Amigos").get(0).click();
		//comprovamos que irene es nuestra amiga
		PO_View.checkElement(driver, "text", "irene@uniovi.es");
		PO_View.checkElement(driver, "text", "Irene");
		PO_View.checkElement(driver, "text", "Rodríguez");
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Rellenamos el formulario como pedro
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "123456");
		//vamos a la lista de amigos
		PO_View.checkElement(driver,"text", "Gestión de usuarios").get(0).click();
		PO_View.checkElement(driver,"text", "Ver Amigos").get(0).click();
		//comprovamos que marta es nuestra amiga
		PO_View.checkElement(driver, "text", "marta@uniovi.es");
		PO_View.checkElement(driver, "text", "Marta");
		PO_View.checkElement(driver, "text", "Díaz");
		//comprovamos que jose es nuestra amigo
		PO_View.checkElement(driver, "text", "jose@uniovi.es");
		PO_View.checkElement(driver, "text", "José");
		PO_View.checkElement(driver, "text", "Fernández");
	}
	
	//PR20. Visualización de páginas en español e inglés (español-inglés-español), comprobación de cambio de etiquetas
	//Páginas testeadas: página principal, listado usuarios, formulario registro, identificación
	@Test
	public void PR20() {
		//Comprobamos el cambio de idioma en la página de inicio
		PO_IndexView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		//Vamos a la vista para registrarnos en la aplicación
		PO_IndexView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Comprobamos el cambio de idioma en la página de registro de usuario
		PO_RegisterView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		//Vamos a la vista para identificarnos (login)
		PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
		//Comprobamos el cambio de idioma en la página de login
		PO_LoginView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		// Rellenamos el formulario para poder acceder a la lista de usuarios
		PO_LoginView.fillForm(driver, "pedro99@uniovi.es", "123456");
		//Pinchamos en las opciones de usuarios para ir a la lista de usuarios
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();
		//Comprobamos el cambio de idioma en la página de listado de usuarios
		PO_ListUsersView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
	}
	
	//PR21. Intento (fallido) de acceso a listado de usuarios sin estar autenticado
	@Test
	public void PR21() {
		//Intentamos navegar al listado de usuarios
		driver.navigate().to(URL + "/user/list");
		//Comprobamos que se nos ha redirigido a la página de login
		PO_LoginView.checkTextoFormulario(driver, PO_Properties.getSPANISH());
	}
	
	//PR22. Intento (fallido) de acceso a listado de publicaciones sin estar autenticado
	@Test
	public void PR22() {
		//Intentamos navegar al listado de publicaciones
		driver.navigate().to(URL + "/publicacion/list");
		//Comprobamos que se nos ha redirigido a la página de login
		PO_LoginView.checkTextoFormulario(driver, PO_Properties.getSPANISH());
	}
	
	//PR23. Intento (fallido) de acceso a opción de usuario administrador siendo usuario estándar
	@Test
	public void PR23() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Intentamos navegar al listado de publicaciones
		driver.navigate().to(URL + "/user/listAdmin");
		//Comprobamos que se nos ha dirigido a una página de error que muestra un Forbidden
		PO_View.checkElement(driver,"h1","HTTP Status 403 – Forbidden");
	}
	
	//PR24. Creación de una nueva publicación con datos válidos, comprobación de aparición de la misma en el listado de publicaciones del usuario
	@Test
	public void PR24() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Pinchamos en las opciones de usuarios para ir a la vista de creación de publicaciones
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'posts-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'publicacion/add')]");
		elementos.get(0).click();
		//Comprobación de que nos hallamos en la vista para crear publicaciones
		PO_CreatePostView.checkKey(driver, "createPost.message", PO_Properties.getSPANISH());
		PO_CreatePostView.fillForm(driver, "Mi primera publicación", "Mi primera publicación");
		//Vamos a la lista de mis publicaciones a comprobar que se ha creado correctamente
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'posts-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'publicacion/list')]");
		elementos.get(0).click();
		//Comprobamos que hay sistema de paginación
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		//Nos vamos a la última página (mediante el botón Última)
		elementos.get(elementos.size()-1).click();
		//Comprobamos que hay una nueva publicación con el título "Mi primera publicación"
		PO_View.checkElement(driver, "text", "Mi primera publicación");
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	//PR25. Creación de una nueva publicación con datos inválidos (campo título vacío)
	@Test
	public void PR25() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Pinchamos en las opciones de usuarios para ir a la vista de creación de publicaciones
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'posts-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'publicacion/add')]");
		elementos.get(0).click();
		//Comprobación de que nos hallamos en la vista para crear publicaciones
		PO_CreatePostView.checkKey(driver, "createPost.message", PO_Properties.getSPANISH());
		PO_CreatePostView.fillForm(driver, "", "Mi primera publicación");
		PO_CreatePostView.checkKey(driver, "createPost.message", PO_Properties.getSPANISH());
		// Comprobamos el error de campo vacío
		PO_CreatePostView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	//PR25b. Creación de una nueva publicación con datos inválidos (campo texto vacío)
	//Es de suponer que la publicación tendrá que tener asociado un texto de manera obligatoria
	@Test
	public void PR25b() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Pinchamos en las opciones de usuarios para ir a la lista de usuarios en modo administrador
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'posts-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'publicacion/add')]");
		elementos.get(0).click();
		//Comprobación de que nos hallamos en la vista para crear publicaciones
		PO_CreatePostView.checkKey(driver, "createPost.message", PO_Properties.getSPANISH());
		PO_CreatePostView.fillForm(driver, "Mi primera publicación", "");
		PO_CreatePostView.checkKey(driver, "createPost.message", PO_Properties.getSPANISH());
		// Comprobamos el error de campo vacío
		PO_CreatePostView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	
	//PR26. Listado de todas mis publicaciones
	@Test
	public void PR26() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		//Pinchamos en las opciones de usuarios para ir a la lista de usuarios en modo administrador
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'posts-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'publicacion/list')]");
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 1);
		
	}
	
	// PR31. Listado completo de usuarios - Modo administrador
	@Test
	public void PR31() {
		PO_PrivateView.loginGeneral(driver, "admin@email.com", "admin");
		//Pinchamos en las opciones de usuarios para ir a la lista de usuarios en modo administrador
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/listAdmin')]");
		elementos.get(0).click();
		//Comprobamos que se visualiza la lista completa de usuarios del sistema
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 5);
		//Comprobamos que tenemos sistema de paginación
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		//Nos vamos a la última página (2)
		elementos.get(2).click();
		//Comprobamos que hay tres usuarios
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 3);
		//Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	//PR35. Visualización de home correcta
	@Test
	public void PR35() {
		PO_PrivateView.loginGeneral(driver, "pedro99@uniovi.es", "123456");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/home')]");
		elementos.get(0).click();
		PO_PrivateView.checkTextoHomePage(driver, "pedro99@uniovi.es");
	}

	//// FIN DE CASOS DE PRUEBA ////

}
