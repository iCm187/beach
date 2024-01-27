package fr.hb.ibm.beach.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hb.ibm.beach.business.Utilisateur;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);

	Utilisateur findByEmail(String email);

}
