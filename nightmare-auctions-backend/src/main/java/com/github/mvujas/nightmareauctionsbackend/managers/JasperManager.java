package com.github.mvujas.nightmareauctionsbackend.managers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class JasperManager {

	@Value("${jasper.directory}")
	private String jasperDirectory;
	
	private ConcurrentMap<String, JasperReport> nameToReportMapping;
	
	public JasperManager() {
		nameToReportMapping = new ConcurrentHashMap<>();
	}
	
	public JasperReport loadReport(String name) throws JRException {
		if(name == null) {
			throw new NullPointerException("Jasper report path cannot be null");
		}
		
		JasperReport report;
		if(nameToReportMapping.containsKey(name)) {
			report = nameToReportMapping.get(name);
		}
		else {
			String path = jasperDirectory + name + ".jrxml";
			InputStream reportStream = getClass().getResourceAsStream(path);
			report = JasperCompileManager.compileReport(reportStream);
			
			nameToReportMapping.putIfAbsent(name, report);
		}
		return report;
	}
	
	public JasperPrint fillReport(
			JasperReport report, 
			Map<String, Object> params, 
			Collection<?> collection) throws JRException {
		if(report == null) {
			throw new NullPointerException("Jasper report cannot be null!");
		}
		if(params == null) {
			params = new HashMap<>();
		}
		
		JRDataSource dataSource = (collection == null) ? 
				new JREmptyDataSource() : new JRBeanCollectionDataSource(collection);
		JasperPrint jasperPrint = 
				JasperFillManager.fillReport(report, params, dataSource);
		
		return jasperPrint;
	}
	
	public JasperPrint fillReport(
			JasperReport report,  
			Collection<?> collection) throws JRException {
		return fillReport(report, null, collection);
	}
	
	public JasperPrint fillReport(
			JasperReport report, 
			Map<String, Object> params) throws JRException {
		return fillReport(report, params, null);
	}
	
	public JasperPrint fillReport(JasperReport report) throws JRException {
		return fillReport(report, null, null);
	}
	
	public void packPrintPdfIntoResponse(
			HttpServletResponse response, 
			JasperPrint print, 
			String filename) throws JRException, IOException {
		if(response == null || print == null) {
			throw new NullPointerException("Response and jasper print cannot be null");
		}

		String fullFileName = filename + ".pdf";
		byte[] data = JasperExportManager.exportReportToPdf(print);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + fullFileName);
		response.setContentLength(data.length);
		
		OutputStream out = response.getOutputStream();
		out.write(data);
		out.flush();
	}

}
