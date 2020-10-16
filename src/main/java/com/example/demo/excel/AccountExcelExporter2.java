package com.example.demo.excel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AccountExcelExporter2 {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Tuple> listAcc;

	public AccountExcelExporter2(List<Tuple> listAcc) {

		this.listAcc = listAcc;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("ListOfStudent");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		
		font.setFontHeight(20);
		font.setBold(true);
		style.setFont(font);
		

		createCell(row, 0, "FullName", style);
		createCell(row, 1, "Birthday", style);
		createCell(row, 2, "Gender", style);
		createCell(row, 3, "Address", style);
		createCell(row, 4, "Phone", style);
		createCell(row, 5, "Username", style);
//		createCell(row, 6, "Class Name", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date)value);
		} else {
			cell.setCellStyle(style);
		}
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Tuple acc : listAcc) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, acc.get("fullname").toString(), style);
			createCell(row, columnCount++, acc.get("birthday").toString(), style);
			createCell(row, columnCount++, acc.get("gender").toString(), style);
			createCell(row, columnCount++, acc.get("address").toString(), style);
			createCell(row, columnCount++, acc.get("phone").toString(), style);
//			createCell(row, columnCount++, acc.get("username").toString(), style);
//			createCell(row, columnCount++, acc.get("class_name").toString(), style);
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
