package fr.hb.ibm.beach.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import fr.hb.ibm.beach.business.Reservation;

public class ReservationsExportExcel extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment; filename=Reservations.xls");

		@SuppressWarnings("unchecked")
		List<Reservation> reservations = (List<Reservation>) model.get("reservations");
		Sheet sheet = workbook.createSheet("Réservations");

		// Créez une ligne d'en-tête
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Nom Client");
		header.createCell(2).setCellValue("Date Début");
		header.createCell(3).setCellValue("Date Fin");

		// Remplissez le contenu du fichier Excel avec les données de réservation
		int rowNum = 1;
		for (Reservation reservation : reservations) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(reservation.getId());
			row.createCell(1).setCellValue(reservation.getClient().getNom());
			row.createCell(2).setCellValue(reservation.getDateDebut().toString());
			row.createCell(3).setCellValue(reservation.getDateFin().toString());
		}
	}
}
