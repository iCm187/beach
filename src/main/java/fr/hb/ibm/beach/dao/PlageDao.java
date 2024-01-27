package fr.hb.ibm.beach.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.Plage;

public interface PlageDao extends JpaRepository<Plage, Long> {

}
