package fr.hb.ibm.beach.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ibm.beach.business.Reservation;

public interface ReservationService {

	Reservation enregistrerReservation(Reservation reservation);

	List<Reservation> recupererReservation();

	Reservation recupererReservation(Long id);

	Page<Reservation> recupererReservation(Pageable pageable);

	List<Reservation> recupererReservationsDeLaSemaineEnCours();

}
