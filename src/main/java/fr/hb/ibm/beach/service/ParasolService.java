package fr.hb.ibm.beach.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ibm.beach.business.Parasol;

public interface ParasolService {

	Parasol enregistrerParasol(Parasol parasol);

	Page<Parasol> recupererParasols(Pageable pageable);
	
	Page<Parasol> recupererParasolsParFiles(Long idFile, Pageable pageable);

	Parasol recupererParasol(Long id);

	List<Parasol> recupererParasols();
}

