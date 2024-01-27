package fr.hb.ibm.beach.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.LienDeParente;

public interface LienDeParenteDao extends JpaRepository<LienDeParente, Long> {

	LienDeParente findByNom(String string);
}
