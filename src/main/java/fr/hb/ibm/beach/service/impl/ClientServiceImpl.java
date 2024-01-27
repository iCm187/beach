package fr.hb.ibm.beach.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.hb.ibm.beach.business.Client;
import fr.hb.ibm.beach.business.LienDeParente;
import fr.hb.ibm.beach.business.Pays;
import fr.hb.ibm.beach.dao.ClientDao;
import fr.hb.ibm.beach.dto.ClientDto;
import fr.hb.ibm.beach.mapper.ClientMapper;
import fr.hb.ibm.beach.service.ClientService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientDao clientDao;
	private final ClientMapper clientMapper;

	@Override
	public Client ajouterClient(Client client) {
		return clientDao.save(client);
	}

	@Override
	public Client recupererClient(Long id) {
		return clientDao.findById(id).orElse(null);
	}

	@Override
	public List<Client> recupererClients() {
		return clientDao.findAll();
	}
	
	@Override
    public List<Client> recupererClientsParPays(Pays pays) {
        return clientDao.findByPays(pays);
    }

	@Override
	public Page<Client> recupererClients(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	@Override
	public List<Client> recupererTousLesClients() {

		return clientDao.findAll();
	}

	@Override
	public List<Client> recupererClientsParLienDeParente(LienDeParente lienDeParente) {

		return clientDao.findByLienDeParente(lienDeParente);
	}

	@Override
	public Client enregistrerClient(ClientDto clientDto) {

		return clientDao.save(clientMapper.toEntity(clientDto)) ;
	}

	@Override
	public boolean supprimerClient(Long id) {
		Client client = recupererClient(id);
		if (client != null) {
			clientDao.delete(client);
			return true;
		}
		return false;
	}
}