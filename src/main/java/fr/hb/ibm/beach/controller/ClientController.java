package fr.hb.ibm.beach.controller;

import java.util.Iterator;


import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.hb.ibm.beach.business.Client;
import fr.hb.ibm.beach.service.ClientService;
import fr.hb.ibm.beach.service.LienDeParenteService;
import fr.hb.ibm.beach.service.PaysService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ClientController {

	private final ClientService clientService;
	private final PaysService paysService;
	private final LienDeParenteService lienDeParenteService;


	/**
	 * 
	 * @param client
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/client", method = RequestMethod.GET)
    public ModelAndView getClient(@ModelAttribute Client client, 
    		@RequestParam(name = "ID_CLIENT", required = false)Long idClient) {
    	
    	ModelAndView mav = new ModelAndView("client");
    	
    	if(idClient != null ) {
    		mav.addObject("client", clientService.recupererClient(idClient));
    	}
    
        // Si le @ModelAttribute n'est pas present:
        // mav.addObject("client", new Client());
        mav.addObject("pays", paysService.recupererListePays());
        mav.addObject("liensDeParente", lienDeParenteService.recupererLiensDeParente());
        return mav;
    }
    
    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ModelAndView postClient(@Valid @ModelAttribute Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println("[ERROR] Client non ajouté:");
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("\t- " + error.getDefaultMessage());
            }
            return getClient(client, client.getId());
        }
            clientService.ajouterClient(client);
            System.out.println("Client ajouté!");
            ModelAndView mav = new ModelAndView("redirect:/clients");
            return mav;
    }
    
    @GetMapping("clients")
    public ModelAndView getClients(
            @PageableDefault(size = 8, sort = "nom") Pageable pageable,
            @RequestParam(name = "ID_LIEN", required = false) Long idLien) {

        ModelAndView mav = new ModelAndView("clients");
        
        //On transmet la demande de page au service qui la transmet au DAO
        mav.addObject("pageDeClients", clientService.recupererClients(pageable));
        mav.addObject("liensDeParente", lienDeParenteService.recupererLiensDeParente());
        mav.addObject("idLien", idLien);
        
        // On récupère l'itérateur qui contient les attributs de tri
        Iterator<Order> iterator = pageable.getSort().iterator();
        
        // On utilise le patron Builder "StringBuilder" pour construire une chaine de caractères qui sera envoyé a la vue.
        StringBuilder sort = new StringBuilder();
        while (iterator.hasNext()) {
            // On ajoute au StringBuilder l'attribut de tri en cours de parcours en appelant le "iterator.next()"
            sort.append(iterator.next().getProperty());
            if (iterator.hasNext()) {
                sort.append(",");
            }
        }
        mav.addObject("sort", sort.toString());

        return mav;
    }

}
