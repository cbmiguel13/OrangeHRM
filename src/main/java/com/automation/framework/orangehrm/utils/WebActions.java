package com.automation.framework.orangehrm.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {

	private WebDriver driver;
	WebDriverWait wait;
	private final int DEFAULT_TIMEOUT = 30;

	public WebActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
	}

	/**
	 * Espera hasta que el elemento este visible
	 * 
	 * @param locator Localizador de tipo By del elemento
	 * @return null si se hizo la accion correctamente, un mensjae de error si nose
	 *         pudo hacer la accion
	 */
	public String waitVisibilityElement(By locator) {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return null;
		} catch (Exception e) {
			return "No se encuentra visible el elemento";
		}

	}

	/**
	 * Espera hasta que el elemento este disponible para luego hacer clic
	 * 
	 * @param locator Localizador de tipo By del elemento
	 * @return null si se hizo la accion correctamente, un mensjae de error si nose
	 *         pudo hacer la accion
	 */
	public String waitClick(By locator) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			return null;
		} catch (Exception e) {
			return "No se pudo hacer clic";
		}

	}

	/**
	 * Espera hasta que el elemento este presente en el DOOM para ingresar el texto
	 * 
	 * @param locator Localizador de tipo By del elemento
	 * @param text    Informacion que se ingresara en el elemento Localizador de
	 *                tipo By del elemento
	 * @return null si se hizo la accion correctamente, un mensjae de error si nose
	 *         pudo hacer la accion
	 */

	public String waitType(By locator, String text) {
		try {
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			element.sendKeys(text);
			return null;
		} catch (Exception e) {
			return "No se pudo ingresar el texto [" + text + "] " + e.getMessage();
		}

	}

	/**
	 * Obtiene todas las opciones disponibles del localizador "locatorOptions" y le
	 * hace clic a la opcion de coincide
	 * 
	 * @param locatorOptions Localizador de tipo By de todas las opciones
	 * @param optionToSelect Opcion a seleccionar
	 * 
	 * @return null si se hizo la accion correctamente, un mensjae de error si nose
	 *         pudo hacer la accion
	 */
	public String waitSelectOption(By locatorOptions, String optionToSelect) {

		try {

			List<WebElement> opciones = driver.findElements(locatorOptions);

			for (WebElement opcion : opciones) {
				if (opcion.getText().trim().equalsIgnoreCase(optionToSelect.trim())) {
					opcion.click();
					return null;
				}
			}

			return "No se encontro la opción [" + optionToSelect + "]";

		} catch (Exception e) {
			return "No se pudo seleccionar la opción [" + optionToSelect + "]";
		}

	}

	/**
	 * Espera a que la primera opcion de una lista desplegable cambie y ya no
	 * contenga el texto que viene en el parametro de optionDifferent
	 * 
	 * @param locatorOptions  Localizador de tipo By de todas las opciones
	 * @param optionDifferent El texto que no debe estar presente en la primera
	 *                        opcion
	 * 
	 * @return null si se hizo la accion correctamente, un mensjae de error si nose
	 *         pudo hacer la accion
	 */
	public String waitOptionDifferent(By locatorOptions, String optionDifferent) {

		try {

			wait.until(driver -> {
				List<WebElement> opciones = driver.findElements(locatorOptions);
				String primeraOpcion = opciones.get(0).getText().trim();
				return !primeraOpcion.contains(optionDifferent);
			});

			return null;

		} catch (Exception e) {
			return "El valor de la primera opcion sigue siendo el mismo";
		}

	}

}
