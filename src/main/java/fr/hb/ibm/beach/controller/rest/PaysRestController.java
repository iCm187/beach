package fr.hb.ibm.beach.controller.rest;

import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.hb.ibm.beach.business.Client;
import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.dto.PaysDto;
import fr.hb.ibm.beach.exception.PaysExistantException;
import fr.hb.ibm.beach.service.ClientService;
import fr.hb.ibm.beach.service.PaysService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/")
public class PaysRestController {

	private PaysService paysService;
	private ClientService clientService;
	
	@Operation(description = "Afficher tous les pays")
	@GetMapping("pays")
	public List<Pays> getPays(){
		return paysService.recupererListePays();
	}
	
	//On demande aux consommateurs de placer dans le corps de la requête HTTP le pays à ajouter au format JSON
    @PostMapping("pays")
    @ResponseStatus(code = HttpStatus.CREATED)
    // https://sonarsource.atlassian.net/browse/RSPEC-4684
    public Pays postPays(@RequestBody PaysDto paysDto) {
        return paysService.enregistrerPays(paysDto);
    }
	
	@Operation(description = "Ajouter pays")
	@PostMapping("pays/{code}/{nom}")
    @ResponseStatus(code=HttpStatus.CREATED)
    public Pays postPays(@PathVariable String code, @PathVariable String nom) {

            return paysService.ajouterPays(code, nom);
     }
    
	//Exception
    @ExceptionHandler(PaysExistantException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public String traiterPaysDejaExistant(Exception exception) {
        return exception.getMessage();
    }
    
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleError(ConstraintViolationException e) {
        String erreur = "";
        for(ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            erreur += constraint.getMessage() + "; ";
        }
        return erreur;
    }
	   
    @Operation(description = "Met à jour le nom d'un pays")
    @PatchMapping("pays/{code}/{nouveauNom}")
    public Pays patchPays(@PathVariable String code, @PathVariable String nouveauNom) {
        Pays pays = paysService.recupererPays(code);
        pays.setNom(nouveauNom);
        return paysService.enregistrerPays(pays);
    }
    
    @Operation(description = "Mettre à jour un pays")
    @PutMapping("pays/{code}")
    // https://sonarsource.atlassian.net/browse/RSPEC-4684
    public Pays putPays(@PathVariable String code, @RequestBody Pays pays) {
        return paysService.enregistrerPays(pays);
    }
    
    @Operation(description = "Afficher clients par pays")
    @GetMapping("pays/{code}/clients")
    public List<Client> getClientsParPays(@PathVariable String code) {
        Pays pays = paysService.recupererPays(code);
        return clientService.recupererClientsParPays(pays);
    }
    
    @Operation(description = "Supprimer un pays")
    @DeleteMapping("pays/{code}")
    public boolean deletePays(@PathVariable String code) {
    	return paysService.supprimerPays(code);
    }
}
