package com.automation.framework.orangehrm.tests;

import com.automation.framework.orangehrm.base.BaseTest;
import com.automation.framework.orangehrm.pages.LoginPage;
import com.automation.framework.orangehrm.pages.RegisterEmployeePage;
import com.automation.framework.orangehrm.pages.SearchDirectoryPage;
import com.automation.framework.orangehrm.utils.ReadProperties;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {

	@Test
	public void testEmployee() {

		test = extent.createTest("Iniciar sesion, registrar y consultar empleado", "Realizar el inicio de sesion,"
				+ " ingresar al modulo de PIM para registrar un empleado con informacion basica"
				+ " y por ultimo en el modulo de Directory realizar la busqueda del mismo para confirmar sus datos");

		// Login
		LoginPage loginPage = new LoginPage(driver);
		String msgError = loginPage.login(ReadProperties.get("username"), ReadProperties.get("password"));

		if (msgError != null) {
			test.fail(msgError);
		} else {
			test.pass("Login exitoso");
		}

		Assert.assertNull(msgError, "Fallo el login");

		// Registrar Empleado
		RegisterEmployeePage registerEmployeePage = new RegisterEmployeePage(driver);
		msgError = registerEmployeePage.registrerEmployee(ReadProperties.get("firstName"),
				ReadProperties.get("middleName"), ReadProperties.get("lastName"), ReadProperties.get("imagePath"));

		if (msgError != null) {
			test.fail(msgError);
		} else {
			test.pass("Empleado registrado exitosamente");
		}

		Assert.assertNull(msgError, "Fallo el registro del empleado");

		// Consultar Empleado
		SearchDirectoryPage searchDirectoryPage = new SearchDirectoryPage(driver);
		msgError = searchDirectoryPage.searchEmployeeInformation(ReadProperties.get("firstName"),
				ReadProperties.get("middleName"), ReadProperties.get("lastName"));

		if (msgError != null) {
			test.fail(msgError);
		} else {
			test.pass("Consulta realizada exitosamente");
		}

		Assert.assertNull(msgError, "Fallo la consulta del empleado");
	}

}
