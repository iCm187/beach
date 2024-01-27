package fr.hb.ibm.beach.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import fr.hb.ibm.beach.dto.ClientDto;
import fr.hb.ibm.beach.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/")
public class ClientRestController {

	private ClientService clientService;

	@GetMapping("clients")
	public Page<Client> getClients(@PageableDefault(page = 0, size = 10, sort = "email") Pageable pageable) {
		return clientService.recupererClients(pageable);
	}

	@Operation(description = "Renvoie le client dont l'id est donné en paramètre")
	@GetMapping("clients/{id}")
	public Client getClient(@PathVariable Long id) {
		return clientService.recupererClient(id);
	}

	// a ne surtout pas faire car engendre problème de sécuriter nipour put ni pour
	// post !
	@PostMapping("client")
	@ResponseStatus(code = HttpStatus.CREATED)
	// https://sonarsource.atlassian.net/browse/RSPEC-4684
	public Client postPays(@RequestBody ClientDto clientDto) {
		return clientService.enregistrerClient(clientDto);
	}

	@PatchMapping("client/{id}")
	public Client patchClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
		Client client = clientService.recupererClient(id);

		if (clientDto.getNom() != null) {
			client.setNom(clientDto.getNom());
		}

		return clientService.enregistrerClient(clientDto);
	}

	@PutMapping("client/{id}")
	public Client putClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
		Client client = clientService.recupererClient(id);

		client.setNom(clientDto.getNom());

		return clientService.enregistrerClient(clientDto);
	}

	@DeleteMapping("client/{id}")
	// Si pas de ResponseStatus = erreur 200 -> SUCCESS
	public boolean deleteClient(@PathVariable Long id) {
		return clientService.supprimerClient(id);
	}
}
