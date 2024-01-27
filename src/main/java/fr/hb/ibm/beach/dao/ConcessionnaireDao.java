package fr.hb.ibm.beach.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.Concessionnaire;

public interface ConcessionnaireDao extends JpaRepository<Concessionnaire, Long> {

	Concessionnaire findByEmail(String string);

}
