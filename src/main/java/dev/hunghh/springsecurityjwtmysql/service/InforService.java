package dev.hunghh.springsecurityjwtmysql.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import dev.hunghh.springsecurityjwtmysql.repository.InforRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class InforService {
	@Autowired
	private InforRepository inforRepository;

//	public String exportReport(String reportFormat) throws FileNotFoundException, JRException, ParseException {
//		String path = "G:\\DownLoad\\";
//		List<StudentInfor> students = new ArrayList<>();
//		List<Tuple> infor = inforRepository.repo();
//		for (Tuple item : infor) {
//			StudentInfor student = new StudentInfor();
//			student.setFullname(item.get("fullname").toString());
//			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//			Date date = formatter.parse(item.get("birthday").toString());
//			student.setBirthday(date);
//			student.setGender(item.get("gender").toString());
//			student.setAddress(item.get("address").toString());
//			student.setPhone(item.get("phone").toString());
//			student.setClass_name(item.get("class_name").toString());
//			students.add(student);
//		}
//
//		File file = ResourceUtils.getFile("classpath:abc.jrxml");
//
//		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
//
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("Cot1", "FULLNAME");
//		parameters.put("createdBy", "ENAO TEAM_ONE");
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//		if (reportFormat.equalsIgnoreCase("html")) {
//			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\Infor.html");
//		}
//		if (reportFormat.equalsIgnoreCase("pdf")) {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Infor.pdf");
//		}
//
//		return "report generated in path : " + path;
//	}
}
