package fr.hb.ibm.beach.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.Parasol;

public interface ParasolDao extends JpaRepository<Parasol, Long> {

	Page<Parasol> findByFileId(Long idFile, Pageable pageable);

}
