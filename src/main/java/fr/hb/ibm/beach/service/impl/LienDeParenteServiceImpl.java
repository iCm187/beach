package fr.hb.ibm.beach.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.dao.LienDeParenteDao;
import fr.hb.ibm.beach.dto.LienDeParenteDto;
import fr.hb.ibm.beach.mapper.LienDeParenteMapper;
import fr.hb.ibm.beach.service.LienDeParenteService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LienDeParenteServiceImpl implements LienDeParenteService {

	private final LienDeParenteDao lienDeParenteDao;
	private final LienDeParenteMapper lienDeParenteMapper;

	@Override
	public LienDeParente ajouterLienDeParente(LienDeParente lienDeParente) {
		return lienDeParenteDao.save(lienDeParente);
	}
	
    @Override
    public LienDeParente ajouterLienDeParente(String nom, float coefficient) {
        LienDeParente lienDeParente = new LienDeParente();
        lienDeParente.setNom(nom);
        lienDeParente.setCoefficient(coefficient);
        return enregistrerLienDeParente(lienDeParente);
    }

    public LienDeParente enregistrerLienDeParente(LienDeParente lienDeParente) {
        return lienDeParenteDao.save(lienDeParente);
    }

	@Override
	public LienDeParente recupererLienDeParente(Long id) {
		return lienDeParenteDao.findById(id).orElse(null);
	}

	@Override
	public List<LienDeParente> recupererLiensDeParente() {
		return lienDeParenteDao.findAll();
	}

	@Override
	public Page<LienDeParente> recupererLiensDeParente(Pageable pageable) {
		return lienDeParenteDao.findAll(pageable);
	}

	@Override
	public boolean supprimerLienDeParente(LienDeParente lienDeParente) {
        if (lienDeParente == null) {
            return false;
        }
        lienDeParenteDao.delete(lienDeParente);
        return true;
    }

	@Override
	public LienDeParente enregistrerLienDeParente(LienDeParenteDto lienDeParenteDto) {

		return lienDeParenteDao.save(lienDeParenteMapper.toEntity(lienDeParenteDto));
	}

}
