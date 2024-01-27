package fr.hb.ibm.beach.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.dto.PaysDto;

public interface PaysService {

	// C(reate)
	Pays ajouterPays(String code, String nomPays);

	Pays enregistrerPays(Pays pays);

	List<Pays> enregistrerPays(List<Pays> pays);

	Pays enregistrerPays(PaysDto paysDto);
	// R(ead)
	Pays recupererPays(String code);

	List<Pays> recupererListePays();

	Page<Pays> recupererListePays(Pageable pageable);
	// U(pdate)

	// D(elete)
	boolean supprimerPays(String code);
}
