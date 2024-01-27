package fr.hb.ibm.beach.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.File;
import fr.hb.ibm.beach.dao.FileDao;
import fr.hb.ibm.beach.service.FileService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
	
	private final FileDao fileDao;

	@Override
	public File ajouterFile(File file) {
		
		return fileDao.save(file);
	}

	@Override
	public List<File> recupererFiles() {

		return fileDao.findAll();
	}

	@Override
	public File recupererFile(Long id) {

		return fileDao.findById(id).orElse(null);
	}

}
