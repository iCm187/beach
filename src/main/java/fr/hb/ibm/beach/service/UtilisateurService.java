package fr.hb.ibm.beach.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ibm.beach.business.Utilisateur;

public interface UtilisateurService {

	 // C
    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);

    // R
    Page<Utilisateur> recupererUtilisateur(Pageable pageable);

    Utilisateur recupererUtilisateur(Long id);

    //U
    Utilisateur mettreAJourUtilisateur(Long id,String nom,String prenom,String email);
    //D
    boolean supprimerUtilisateur(Long id);

	Utilisateur recupererUtilisateur(String email, String motDePasse);

	Utilisateur recupererUtilisateur(String username);

}
