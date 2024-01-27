package fr.hb.ibm.beach.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.dto.LienDeParenteDto;

public interface LienDeParenteService {

	// C(reate)
	LienDeParente ajouterLienDeParente(LienDeParente lienDeParente);

	LienDeParente ajouterLienDeParente(String nom, float coefficient);
	
	LienDeParente enregistrerLienDeParente(LienDeParenteDto lienDeParenteDto);
	// R(ead)
	LienDeParente recupererLienDeParente(Long id);

	List<LienDeParente> recupererLiensDeParente();

	Page<LienDeParente> recupererLiensDeParente(Pageable pageable);


	// U(pdate)
	LienDeParente enregistrerLienDeParente(LienDeParente lienDeParente);

	// D(elete)
	boolean supprimerLienDeParente(LienDeParente lienDeParente);


}
