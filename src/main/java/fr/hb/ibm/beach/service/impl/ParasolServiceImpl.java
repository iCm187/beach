package fr.hb.ibm.beach.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.Parasol;
import fr.hb.ibm.beach.dao.ParasolDao;
import fr.hb.ibm.beach.service.ParasolService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParasolServiceImpl implements ParasolService {
	
	private final ParasolDao parasolDao;

	@Override
	public Parasol enregistrerParasol(Parasol parasol) {

		return parasolDao.save(parasol);
	}

	@Override
	public Page<Parasol> recupererParasols(Pageable pageable) {

		return parasolDao.findAll(pageable);
	}

	@Override
	public Page<Parasol> recupererParasolsParFiles(Long idFile, Pageable pageable) {

		return parasolDao.findByFileId(idFile, pageable);
	}

	@Override
	public Parasol recupererParasol(Long id) {

		return parasolDao.findById(id).orElse(null);
	}

	@Override
	public List<Parasol> recupererParasols() {
		
		return parasolDao.findAll();
	}
}

