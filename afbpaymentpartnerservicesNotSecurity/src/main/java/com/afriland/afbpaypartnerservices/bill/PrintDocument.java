package com.afriland.afbpaypartnerservices.bill;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrintDocument {

	public final static String workPathDir = System.getProperty("user.dir");

	public static JasperReport getCompileReport(String reportName) throws Exception {
		return JasperCompileManager.compileReport(reportName);
	}

	private static JasperPrint printReport(String reportName, HashMap<String, Object> map, Collection<?> maCollection) throws Exception {
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(maCollection);
		return reportName.endsWith(".jasper") ? JasperFillManager.fillReport(reportName, map, (JRDataSource)dataSource) : JasperFillManager.fillReport(getCompileReport(reportName), map, (JRDataSource)dataSource);
	}

	public static void exportReportToPDFFile(String reportName, HashMap<String, Object> map, Collection<?> maCollection, String fileName) throws Exception {
		JasperPrint jp = printReport(reportName, map, maCollection);
		JasperExportManager.exportReportToPdfFile(jp, fileName);
	}

	public static byte[] exportReportToPDF(String reportName, HashMap<String, Object> map, Collection<?> maCollection, String fileName) throws Exception {
		JasperPrint jp = printReport(reportName, map, maCollection);
		return JasperExportManager.exportReportToPdf(jp);
	}

	public static byte[] loadAndExportReportIntoPDF(String reportName, HashMap<String, Object> map, Collection<?> ds, String fileName) throws Exception {
		return exportReportToPDF(reportName, map, ds, fileName);
	}

	public static void loadAndExportReportIntoPDFFile(String reportName, HashMap<String, Object> map, Collection<?> ds, String fileName) throws Exception {
		exportReportToPDFFile(reportName, map, ds, fileName);
	}

	public static byte[] generateBill(BillModel bill, String jasperFileName, String pdfFileName) throws Exception {
		String reportsDir = workPathDir.concat(File.separator).concat("billModel").concat(File.separator);
		HashMap<String, Object> map = new HashMap<>();
		map.put("logo", reportsDir.concat("logo_afb1_4.png"));
		map.put("logoDangote", reportsDir.concat("logo_sabc3.png"));
		map.put("img_contacter", reportsDir.concat("img_contacter.png"));
		map.put("codeBar", bill.getCodeBar());
		List<BillModel> list = new ArrayList<>();
		list.add(bill);
		byte[] data = loadAndExportReportIntoPDF(reportsDir.concat(jasperFileName), map, list, reportsDir.concat(pdfFileName));
		return data;
	}
}
