package fr.hb.ibm.beach.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.Pays;

public interface PaysDao extends JpaRepository<Pays, String> {

	Pays findByCode(String code);

}
