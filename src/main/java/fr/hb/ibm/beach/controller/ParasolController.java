package fr.hb.ibm.beach.controller;


import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ibm.beach.business.Parasol;
import fr.hb.ibm.beach.service.FileService;
import fr.hb.ibm.beach.service.ParasolService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ParasolController {

	// Le controlleur a trois dependences
	
	private final ParasolService parasolService;
	private final FileService fileService;


	/**
     * 
     * Cette méthode traite une requête HTTP sur l'URL parasol utilisant la méthode GET
     * 
     * 
     * @param parasol - qui sera envoyé a la view
     * @param idParasol - l'Id du parasol que l'on souhaite modifier (paramètre non obligatoire)
     * @return
     */
    
    // La méthode sera invoquée lorsque une requête sur l'URL parasol est reçu
    @GetMapping("parasol")
    public ModelAndView getParasol(
            @ModelAttribute Parasol parasol,
            @RequestParam(required = false, name = "ID_PARASOL") Long idParasol) {
        ModelAndView mav = new ModelAndView();
        
        //On définit la View (JSP)
        mav.setViewName("parasol");
        
        //mav.addObject("parasol", new Parasol());
        
        // On ajoute dans le compartiement a petite bille (danette) la liste des files
        mav.addObject("files", fileService.recupererFiles());
        if (idParasol != null) {
            Parasol parasolAModifier = parasolService.recupererParasol(idParasol);
            mav.addObject("parasol", parasolAModifier);
        }
        
        return mav;
    }

	@PostMapping("parasol")
	public ModelAndView postParasol(@Valid @ModelAttribute Parasol parasol, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return getParasol(parasol, parasol.getId());
		}

		parasolService.enregistrerParasol(parasol);
		return new ModelAndView("redirect:/parasols");
	}

	@GetMapping("parasols")
	public ModelAndView getParasols(
			@PageableDefault(size = 8, sort = { "file.numero", "numEmplacement" }) Pageable pageable,
			@RequestParam(name = "ID_FILE", required = false) Long idFile) {

		ModelAndView mav = new ModelAndView("parasols");

		if (idFile == null || idFile == 0) {

			// Si on n'a pas d'ID de file, on renvoie tous les parasols
			mav.addObject("pageDeParasols", parasolService.recupererParasols(pageable));
		} else {
			// Si on a un ID de file, on filtre par ce file
			mav.addObject("pageDeParasols", parasolService.recupererParasolsParFiles(idFile, pageable));
		}

		mav.addObject("files", fileService.recupererFiles());
		mav.addObject("idFile", idFile);
		
		return mav;
	}
}
