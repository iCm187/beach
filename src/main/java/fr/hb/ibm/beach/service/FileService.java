package fr.hb.ibm.beach.service;

import java.util.List;

import fr.hb.ibm.beach.business.File;

public interface FileService {

	File ajouterFile(File file);

	List<File> recupererFiles();

	File recupererFile(Long id);
}
