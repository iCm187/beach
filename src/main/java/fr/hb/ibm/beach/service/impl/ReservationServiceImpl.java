package fr.hb.ibm.beach.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.Reservation;
import fr.hb.ibm.beach.dao.ReservationDao;
import fr.hb.ibm.beach.service.ReservationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private ReservationDao reservationDao;

	@Override
	public Reservation enregistrerReservation(Reservation reservation) {
		return reservationDao.save(reservation);
	}

	@Override
	public Page<Reservation> recupererReservation(Pageable pageable) {
		return reservationDao.findAll(pageable);
	}

	@Override
	public List<Reservation> recupererReservation() {
		return reservationDao.findAll();
	}

	@Override
	public Reservation recupererReservation(Long id) {
		return reservationDao.findById(id).orElse(null);
	}

	@Override
	public List<Reservation> recupererReservationsDeLaSemaineEnCours() {
		
		return reservationDao.findBySemaine();
	}
}
