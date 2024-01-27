package fr.hb.ibm.beach.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.Utilisateur;
import fr.hb.ibm.beach.dao.UtilisateurDao;
import fr.hb.ibm.beach.service.UtilisateurService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {
	
	private final UtilisateurDao utilisateurDao;
    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurDao.save(utilisateur);
    }

    @Override
    public Page<Utilisateur> recupererUtilisateur(Pageable pageable) {
        return utilisateurDao.findAll(pageable);
    }

    @Override
    public Utilisateur recupererUtilisateur(Long id) {
        return utilisateurDao.findById(id).orElse(null);
    }


    @Override
    public Utilisateur mettreAJourUtilisateur(Long id,String nom,String prenom,String email) {
        Utilisateur utilisateurMJ = recupererUtilisateur(id);
        utilisateurMJ.setNom(nom);
        utilisateurMJ.setPrenom(prenom);
        utilisateurMJ.setEmail(email);
        return utilisateurDao.save(utilisateurMJ);
    }

    @Override
    public boolean supprimerUtilisateur(Long id) {
        Utilisateur utilisateurD = recupererUtilisateur(id);
        if (utilisateurD == null) {
            return false;
        }
        utilisateurDao.delete(utilisateurD);
        return true;
    }

	@Override
	public Utilisateur recupererUtilisateur(String email, String motDePasse) {

		return utilisateurDao.findByEmailAndMotDePasse(email, motDePasse);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Utilisateur utilisateur = utilisateurDao.findByEmail(email);
	    
	    List<GrantedAuthority> roles = new ArrayList<>();
	    
	    if (utilisateur.getClass().getSimpleName().equals("Concessionnaire")) {
	        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    } else {
	        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
	    }
	    
	    return new org.springframework.security.core.userdetails.User(
	        utilisateur.getEmail(),
	        utilisateur.getMotDePasse(),
	        roles);
	}

	@Override
    public Utilisateur recupererUtilisateur(String email) {

        return utilisateurDao.findByEmail(email);
    }
}
