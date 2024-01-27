package fr.hb.ibm.beach.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ibm.beach.business.Parasol;
import fr.hb.ibm.beach.business.Reservation;
import fr.hb.ibm.beach.service.ClientService;
import fr.hb.ibm.beach.service.ParasolService;
import fr.hb.ibm.beach.service.ReservationService;
import fr.hb.ibm.beach.view.ReservationExportPdf;
import fr.hb.ibm.beach.view.ReservationsExportExcel;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ReservationController {

	// Dependences
	private final ReservationService reservationService;
	private final ClientService clientService;
	private final ParasolService parasolService;

	/*
	 * @GetMapping(value = { "reservation" }) public ModelAndView getReservation() {
	 * ModelAndView mav = new ModelAndView(); mav.setViewName("reservation"); return
	 * mav; }
	 */

	@GetMapping("reservations")
	public ModelAndView getreservations(@PageableDefault(size = 10, sort = { "id" }) Pageable pageable,
			@RequestParam(name = "ID_RESERVATION", required = false) Long idReservation) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservations");
		mav.addObject("pageDeReservations", reservationService.recupererReservation(pageable));

		Iterator<Order> iterator = pageable.getSort().iterator();
		StringBuilder sort = new StringBuilder();
		while (iterator.hasNext()) {
			sort.append(iterator.next().getProperty());
			if (iterator.hasNext()) {
				sort.append(",");
			}
		}
		mav.addObject("sort", sort.toString());
		return mav;

	}

	@GetMapping("reservationPDF")
	public ModelAndView getReservationPdf(@RequestParam(name = "ID_RESERVATION") Long idReservation) {
		ModelAndView mav = new ModelAndView(new ReservationExportPdf());
		Reservation reservation = reservationService.recupererReservation(idReservation);
		mav.addObject("reservations", Arrays.asList(reservation));
		return mav;
	}

	@GetMapping("/exportExcel")
	public ModelAndView exportExcel() {

		List<Reservation> reservations = reservationService.recupererReservationsDeLaSemaineEnCours();

		ModelAndView modelAndView = new ModelAndView(new ReservationsExportExcel());
		modelAndView.addObject("reservations", reservations);

		return modelAndView;
	}

	@GetMapping(value = { "reservation" })
	public ModelAndView getReservation(@RequestParam(name = "ID_CLIENT", required = false) Long idClient,
			@RequestParam(name = "ID_RESERVATION", required = false) Long idReservation,
			@RequestParam(name = "NB_PARASOLS", required = false) Integer nbParasols) {

		ModelAndView mav = new ModelAndView("reservation");
		Reservation reservation = null;

		if (idReservation != null) {
			reservation = reservationService.recupererReservation(idReservation);
		} else {
			reservation = new Reservation();
			if (idClient != null) {
				reservation.setClient(clientService.recupererClient(idClient));
			}
			if (nbParasols != null) {
				List<Parasol> parasols = new ArrayList<>();
				for (int i = 0; i < nbParasols; i++) {
					parasols.add(parasolService.recupererParasol(1L));
				}
				reservation.setParasols(parasols);
			}
		}

		mav.addObject("reservation", reservation);
		mav.addObject("parasols", parasolService.recupererParasols());
		mav.addObject("clients", clientService.recupererTousLesClients());

		return mav;
	}

	@PostMapping("reservation")
	public ModelAndView postReservation(@Valid @ModelAttribute Reservation reservation, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			ModelAndView mav = getReservation(reservation.getId(), reservation.getClient().getId(),
					reservation.getParasols().size());
			mav.addObject("reservation", reservation);
			return mav;
		}
		reservationService.enregistrerReservation(reservation);
		return new ModelAndView("redirect:/reservations");
	}
}
