package fr.hb.ibm.beach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ibm.beach.business.Utilisateur;
import fr.hb.ibm.beach.service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@AllArgsConstructor
@Log4j2
public class UtilisateurController {

	private final UtilisateurService utilisateurService;
	protected static final String DOSSIER_IMAGES = "src/main/webapp/images/";

	//une methode du controller peux prendre en charge plusieur url
	//Dans le cas de cette methode les url prise en charge sont / et index
    @GetMapping(value = { "/", "index"})
    public ModelAndView getConnexion() {        
        //System.out.println(new Date() + " dans getConnexion()");
        log.warn(new Date() + " dans getConnexion()");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index"); // Ligne à compléter        
        return mav;
    }

	@GetMapping("televerserAvatar")
	public ModelAndView getAvatarUtilisateur(@RequestParam(name = "ID_UTILISATEUR") Long idUtilisateur) {
		ModelAndView mav = new ModelAndView("televersementAvatar");
		mav.addObject("utilisateur", utilisateurService.recupererUtilisateur(idUtilisateur));
		return mav;
	}

	@PostMapping("televersementAvatar")
	public ModelAndView postAvatarUtilisateur(@RequestParam(name = "ID_UTILISATEUR") Long idUtilisateur,
			@RequestParam("FICHIER") MultipartFile multipartFile) {
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(idUtilisateur);

		try {
			enregisterFichier(utilisateur.getId() + ".jpg", multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView("redirect:/clients");
		return mav;
	}

	protected static void enregisterFichier(String nom, MultipartFile multipartFile) throws IOException {
		Path chemin = Paths.get(DOSSIER_IMAGES);

		if (!Files.exists(chemin)) {
			Files.createDirectories(chemin);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path cheminFichier = chemin.resolve(nom);
			Files.copy(inputStream, cheminFichier, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Erreur d'écriture : " + nom, ioe);
		}
	}

}
