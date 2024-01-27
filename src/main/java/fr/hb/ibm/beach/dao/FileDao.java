package fr.hb.ibm.beach.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.File;

public interface FileDao extends JpaRepository<File, Long> {

}
