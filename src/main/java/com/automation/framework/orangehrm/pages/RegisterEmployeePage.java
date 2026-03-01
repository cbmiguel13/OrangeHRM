package com.automation.framework.orangehrm.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.framework.orangehrm.utils.WebActions;

public class RegisterEmployeePage {

	// Localizadores
	private static final By MENU_OPTION_PIM = By.xpath("//span[text()='PIM']");
	private static final By BUTTON_ADD_EMPLOYEE = By.xpath("//button[normalize-space()='Add']");
	private static final By INPUT_FIRST_NAME = By.name("firstName");
	private static final By INPUT_MIDDLE_NAME = By.name("middleName");
	private static final By INPUT_LAST_NAME = By.name("lastName");
	private static final By INPUT_FILE = By.xpath("//input[@type='file']");
	private static final By BUTTON_SAVE = By.xpath("//button[normalize-space()='Save']");
	private static final By TEXT_SUCCESSFULLY = By.xpath("//p[normalize-space()='Successfully Saved']");

	// Declarar page de WebActions
	private WebActions actions;

	// Constructor
	public RegisterEmployeePage(WebDriver driver) {
		this.actions = new WebActions(driver);
	}

	/**
	 * Ingresa a la opción del menú "PIM" para luego hacer clic en el botón de "Add"
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String navigateToAddEmployee() {

		String msgError = actions.waitClick(MENU_OPTION_PIM);
		if (msgError != null)
			return "Error en la opción del menú PIM: " + msgError;

		msgError = actions.waitClick(BUTTON_ADD_EMPLOYEE);
		if (msgError != null)
			return "Error en el botón de Add: " + msgError;

		return msgError;

	}

	/**
	 * Llena el formulario con los datos del empleado
	 * 
	 * @param firstName      Primer nombre del empleado
	 * @param middleName     Segundo nombre del empleado
	 * @param lastName       Apellido del empleado
	 * @param rutaFotoPerfil Ruta de la imagen que se va a ingresar como foto de
	 *                       perfil
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String fillForm(String firstName, String middleName, String lastName, String rutaFotoPerfil) {

		String newRutaFotoPerfil = System.getProperty("user.dir") + File.separator + rutaFotoPerfil;
		
		File archivo = new File(newRutaFotoPerfil);
		if (!archivo.exists()) {
			return "La ruta la imagen que se va a ingresar como foto de perfil no existe";
		}

		String msgError = actions.waitType(INPUT_FIRST_NAME, firstName);
		if (msgError != null)
			return "Error en el campo First Name: " + msgError;

		msgError = actions.waitType(INPUT_MIDDLE_NAME, middleName);
		if (msgError != null)
			return "Error en el campo Middle Name: " + msgError;

		msgError = actions.waitType(INPUT_LAST_NAME, lastName);
		if (msgError != null)
			return "Error en el campo Last Name: " + msgError;

		msgError = actions.waitType(INPUT_FILE, newRutaFotoPerfil);
		if (msgError != null)
			return "Error en ingresar la foto de perfil: " + msgError;

		return msgError;

	}

	/**
	 * Hace clic en el botón de Save para guardar la información del empleado
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String saveEmployee() {

		String msgError = actions.waitClick(BUTTON_SAVE);
		if (msgError != null)
			return "Error en el botón Save: " + msgError;

		msgError = actions.waitVisibilityElement(TEXT_SUCCESSFULLY);
		if (msgError != null)
			return "Error en el mensaje de Successfully Saved";

		return msgError;

	}

	/**
	 * Metodo que se encarga de hacer el flujo completo para regsitrar un empleado
	 * 
	 * @param firstName      Primer nombre del empleado
	 * @param middleName     Segundo nombre del empleado
	 * @param lastName       Apellido del empleado
	 * @param rutaFotoPerfil Ruta de la imagen que se va a ingresar como foto de
	 *                       perfil
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String registrerEmployee(String firstName, String middleName, String lastName, String rutaFotoPerfil) {

		String msgError = navigateToAddEmployee();
		if (msgError != null) {
			return msgError;
		}

		msgError = fillForm(firstName, middleName, lastName, rutaFotoPerfil);
		if (msgError != null) {
			return msgError;
		}

		msgError = saveEmployee();
		if (msgError != null) {
			return msgError;
		}

		return msgError;

	}

}
