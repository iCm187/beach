package fr.hb.ibm.beach.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.hb.ibm.beach.business.Client;
import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.business.Pays;

public interface ClientDao extends JpaRepository<Client, Long> {

	@Query("""
            FROM Client
            WHERE pays=:lePays
            """)
    List<Client> findByPays(@Param("lePays") Pays pays);

	List<Client> findByLienDeParente(LienDeParente lienDeParente);

}
