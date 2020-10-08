package com.example.demo.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class AccountPDFExporter {
	private List<Tuple> listAcc;

	public AccountPDFExporter(List<Tuple> listAcc2) {

		this.listAcc = listAcc2;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setPadding(7);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("FullName", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Birthday", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Phone", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Username", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Class Name", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		for (Tuple ac : listAcc) {
			table.addCell(ac.get("fullname").toString());
			table.addCell(ac.get("birthday").toString());
			table.addCell(ac.get("gender").toString());
			table.addCell(ac.get("address").toString());
			table.addCell(ac.get("phone").toString());
			table.addCell(ac.get("username").toString());
			table.addCell(ac.get("class_name").toString());
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(30);

		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph("List of Student", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {6.0f,5.0f, 3.0f, 3.0f, 3.5f, 3.5f, 3.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
