package fr.hb.ibm.beach.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.hb.ibm.beach.business.Client;
import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.dto.ClientDto;

public interface ClientService {

	// C(reate)
	Client ajouterClient(Client client);

	Client enregistrerClient(ClientDto clientDto);
	
	// R(ead)
	Client recupererClient(Long id);

	List<Client> recupererClients();

	List<Client> recupererClientsParPays(Pays pays);
	
	Page<Client> recupererClients(Pageable pageable);
	
	List<Client> recupererClientsParLienDeParente(LienDeParente lienDeParente);
	
	List<Client> recupererTousLesClients();
	// U(pdate)

	// D(elete)
	boolean supprimerClient(Long id);

}
