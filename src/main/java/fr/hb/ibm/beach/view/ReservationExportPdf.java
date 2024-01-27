package fr.hb.ibm.beach.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import fr.hb.ibm.beach.business.Reservation;

import com.lowagie.text.*;

public class ReservationExportPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");

        // Ajouter un en-tête
        HeaderFooter header = new HeaderFooter(new Phrase("En-tête du document"), false);
        document.setHeader(header);

        // Ajouter un pied de page
        HeaderFooter footer = new HeaderFooter(new Phrase("Pied de page du document"), false);
        document.setFooter(footer);

        // ... Le reste de votre code pour générer le contenu du PDF ...

        // Titre de la facture
        Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
        Paragraph title = new Paragraph("Facture de réservation", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

         // Ajout du logo en haut à gauche
        Image logo = Image.getInstance(request.getServletContext().getRealPath("/images/Logo.png"));
        logo.scaleAbsolute(80, 60);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Ajout des coordonnées
        PdfPTable coordTable = new PdfPTable(1);
        coordTable.setWidthPercentage(100);
        coordTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        coordTable.addCell(new PdfPCell(new Phrase("Adresse e-mail: votremail@example.com")));
        coordTable.addCell(new PdfPCell(new Phrase("Numéro de téléphone: +123456789")));
        document.add(coordTable);

        // Espacement
        document.add(Chunk.NEWLINE);
        
        @SuppressWarnings("unchecked")
        List<Reservation> reservations = (List<Reservation>) model.get("reservations");
        
        // Tableau des réservations
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 2, 2, 2});         
        // En-tête du tableau
        PdfPCell cell = new PdfPCell(new Phrase("Détails de la réservation"));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        table.addCell("Client");
        table.addCell("Date de début");
        table.addCell("Date de fin");
        table.addCell("Statut");
        
        for (Reservation reservation : reservations) {
            table.addCell(new PdfPCell(new Phrase(reservation.getClient().getNom() + " " + reservation.getClient().getPrenom())));
            table.addCell(new PdfPCell(new Phrase(reservation.getDateDebut().toString())));
            table.addCell(new PdfPCell(new Phrase(reservation.getDateFin().toString())));
            table.addCell(new PdfPCell(new Phrase(reservation.getStatut().getNom())));
        }
        
        document.add(table);

        //Total
        PdfPTable totalTable = new PdfPTable(2);
        totalTable.setWidthPercentage(50);
        totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        totalTable.addCell("Total:");
        totalTable.addCell("MontantTotal");
        
        document.add(totalTable);

        // Espacement
        document.add(Chunk.NEWLINE);

        // Mentions légales
        Paragraph mentionsLegales = new Paragraph("Mentions légales :\n" +
              "Ce document est une facture de réservation générée par l'application BeachRental.\n" +
              "Toute utilisation abusive ou non autorisée de ce document est strictement interdite.\n" +
              "Pour plus d'informations, veuillez contacter notre service clientèle à l'adresse support@beachrental.com.\n" +
              "Copyright © 2023 BeachRental. Tous droits réservés.", new Font(Font.HELVETICA, 10));
        mentionsLegales.setAlignment(Element.ALIGN_CENTER);
        document.add(mentionsLegales);

        // Ajouter un pied de page
        Phrase footerText = new Phrase("Pied de page personnalisé", new Font(Font.HELVETICA, 8));
        HeaderFooter customFooter = new HeaderFooter(footerText, true);
        document.setFooter(customFooter);
    }
}
