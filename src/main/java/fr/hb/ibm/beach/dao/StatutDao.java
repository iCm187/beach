package fr.hb.ibm.beach.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.Statut;

public interface StatutDao extends JpaRepository<Statut, Long> {

	Statut findByNom(String string);

}
