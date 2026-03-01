package com.automation.framework.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.framework.orangehrm.utils.WebActions;

public class SearchDirectoryPage {

	// Localizadores
	private static final By MENU_OPTION_DIRECTORY = By.xpath("//span[text()='Directory']");
	private static final By INPUT_EMPLOYEE_NAME = By.xpath("//input[contains(@placeholder,'Type for hints')]");
	private static final By DIV_LISTBOX = By.xpath("//div[@role='listbox']");
	private static final By DIV_LISTBOX_OPTIONS = By.xpath("//div[@role='listbox']//div");
	private static final By BUTTON_SEARCH = By.xpath("//button[normalize-space()='Search']");

	private By getParagraphLocatorByText(String text) {
		return By.xpath("//p[normalize-space()='" + text + "']");
	}

	// Declarar page de WebActions
	private WebActions actions;

	// Constructor
	public SearchDirectoryPage(WebDriver driver) {
		this.actions = new WebActions(driver);
	}

	/**
	 * Ingresa a la opción del menú "Directory"
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String navigateToDirectory() {

		String msgError = actions.waitClick(MENU_OPTION_DIRECTORY);
		if (msgError != null)
			return "Error en la opción del menú Directory: " + msgError;

		return msgError;

	}

	/**
	 * Ingresar el primer nombre del empleado, espera a que aparezca la lista
	 * desplegable de usuaios que coinciden para hacerle clic a esa opcion
	 * 
	 * @param firstName  Primer nombre del empleado
	 * @param middleName Segundo nombre del empleado
	 * @param lastName   Apellido del empleado
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String typeNameEmployee(String firstName, String middleName, String lastName) {

		String fullName = firstName + " " + middleName + " " + lastName;

		String msgError = actions.waitType(INPUT_EMPLOYEE_NAME, firstName);
		if (msgError != null)
			return "Error en el campo Employee Name: " + msgError;

		msgError = actions.waitVisibilityElement(DIV_LISTBOX);
		if (msgError != null)
			return "Error en la lista desplegable Usuarios: " + msgError;

		msgError = actions.waitOptionDifferent(DIV_LISTBOX_OPTIONS, "Searching");
		if (msgError != null)
			return "Error en esperar a que la primera opcion de la lista desplegable Usuarios no sea [Searching]: " + msgError;

		msgError = actions.waitSelectOption(DIV_LISTBOX_OPTIONS, fullName);
		if (msgError != null)
			return "Error en la lista desplegable de Usuarios: " + msgError;

		return msgError;

	}

	/**
	 * Hace clic en el botón de Search para buscar al empleado
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String searchEmployee() {

		String msgError = actions.waitClick(BUTTON_SEARCH);
		if (msgError != null) {
			return "Error en el botón de Search: " + msgError;
		}

		return msgError;

	}

	/**
	 * Realiza la validacion de que el elemento que contiene el registro del nombre
	 * del empleado este visible o que exista
	 * 
	 * @param firstName  Primer nombre del empleado
	 * @param middleName Segundo nombre del empleado
	 * @param lastName   Apellido del empleado
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String confirmEmployeeInformation(String firstName, String middleName, String lastName) {

		String fullName = firstName + " " + middleName + " " + lastName;

		String msgError = actions.waitVisibilityElement(getParagraphLocatorByText(fullName));
		if (msgError != null)
			return "Error en el registro " + fullName + " del resultado de busqueda: " + msgError;

		return msgError;

	}

	/**
	 * Metodo que se encarga de hacer el flujo completo para buscar un empleado
	 * desde el menú de "Directory" y confirmar que los resultados de busqueda
	 * coincidan con los filtros aplicados
	 * 
	 * @param firstName  Primer nombre del empleado
	 * @param middleName Segundo nombre del empleado
	 * @param lastName   Apellido del empleado
	 * 
	 * @return null si se lleno el formulario correctamente, de lo contrario retorna
	 *         un mensaje de error
	 */
	public String searchEmployeeInformation(String firstName, String middleName, String lastName) {

		String msgError = navigateToDirectory();
		if (msgError != null) {
			return msgError;
		}

		msgError = typeNameEmployee(firstName, middleName, lastName);
		if (msgError != null) {
			return msgError;
		}

		msgError = searchEmployee();
		if (msgError != null) {
			return msgError;
		}

		msgError = confirmEmployeeInformation(firstName, middleName, lastName);
		if (msgError != null) {
			return msgError;
		}

		return msgError;

	}

}
