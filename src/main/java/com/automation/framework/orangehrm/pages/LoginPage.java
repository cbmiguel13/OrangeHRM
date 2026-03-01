package com.automation.framework.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.framework.orangehrm.utils.WebActions;

public class LoginPage {

	// Localizadores
	private static final By INPUT_USERNAME = By.name("username");
	private static final By INPUT_PASSWORD = By.name("password");
	private static final By BUTTON_LOGIN = By.xpath("//button[normalize-space()='Login']");

	// Declarar page de WebActions
	private WebActions actions;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.actions = new WebActions(driver);
	}

	/**
	 * Ingresa los datos del usuario y luego hacer clic en el botón de Login para
	 * realizar el inicio de sesion
	 * 
	 * @param username Nombre del usuario
	 * @param password Contraseña del usuario
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String login(String username, String password) {

		String msgError = actions.waitType(INPUT_USERNAME, username);
		if (msgError != null)
			return "Error en el campo Username: " + msgError;

		msgError = actions.waitType(INPUT_PASSWORD, password);
		if (msgError != null)
			return "Error en el campo Password: " + msgError;

		msgError = actions.waitClick(BUTTON_LOGIN);
		if (msgError != null)
			return "Error en el botón Login: " + msgError;

		return msgError;
	}

}
