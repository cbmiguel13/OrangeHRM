package com.automation.framework.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("reports/TestReport.html");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Reporte de Ejecución - OrangeHRM");
			spark.config().setReportName("Automatización OrangeHRM");
			spark.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Aplicación", "OrangeHRM");
			extent.setSystemInfo("Tester", "Luis Castañeda");
		}
		return extent;
	}

}
