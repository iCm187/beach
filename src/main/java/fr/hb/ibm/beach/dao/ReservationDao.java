package fr.hb.ibm.beach.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.hb.ibm.beach.business.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    
	@Query("""
            FROM Reservation
            WHERE WEEK(dateDebut) = WEEK(current_date())
            """)
    List<Reservation> findBySemaine();

}
