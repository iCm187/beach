package fr.hb.ibm.beach.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.dao.PaysDao;
import fr.hb.ibm.beach.dto.PaysDto;
import fr.hb.ibm.beach.exception.PaysExistantException;
import fr.hb.ibm.beach.mapper.PaysMapper;
import fr.hb.ibm.beach.service.PaysService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements PaysService {

	
	private final PaysDao paysDao;
	private final PaysMapper paysMapper;
	
	@Override
    public Pays ajouterPays(String code, String nom) {
        if (paysDao.findByCode(code)==null) {
            return paysDao.save(new Pays(code, nom));            
        }
        else {
            throw new PaysExistantException("Ce pays existe déjà");
        }
    }

	@Override
	public Pays enregistrerPays(Pays pays) {
		return paysDao.save(pays);
	}

	@Override
	public List<Pays> enregistrerPays(List<Pays> pays) {
		return paysDao.saveAll(pays);
	}

	@Override
	public Pays recupererPays(String code) {
		return paysDao.findById(code).orElse(null);
	}

	@Override
	public List<Pays> recupererListePays() {
		return paysDao.findAll();
	}

	@Override
	public Page<Pays> recupererListePays(Pageable pageable) {
		return paysDao.findAll(pageable);
	}

	@Override
	public boolean supprimerPays(String code) {
		Pays pays = recupererPays(code);
		if (pays != null) {
			paysDao.delete(pays);
			return true;
		}
		return false;
	}

	@Override
	public Pays enregistrerPays(PaysDto paysDto) {
		return paysDao.save(paysMapper.toEntity(paysDto));
	}
}
