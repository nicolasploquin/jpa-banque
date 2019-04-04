package fr.eni.formation.banque.mem;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.ClientDao;

public class ClientDaoMem implements ClientDao {
	
	
	private List<Client> clients = new LinkedList<>();

	private static ClientDaoMem instance = null;

	public ClientDaoMem() {
		
	}
	
	public static ClientDaoMem getInstance() {
		
		if(instance == null) {
			instance = new ClientDaoMem();
		}
		return instance;
		
	}
	
	

	@Override
	public Client create(String nom, String prenom) {
		Client client = new Client(nom, prenom);
		clients.add(client);
		return client;
	}

	@Override
	public Stream<Client> readAll() {
		return clients.stream();
	}

	@Override
	public Client read(long id) {
		return clients.stream()
				.filter(cli -> cli.getIdClient() == id)
				.findFirst()
				.orElse(null);
	}

	@Override
	public Stream<Client> read(String nom) {
		// TODO Auto-generated method stub
		return clients.stream()
				.filter(cli -> cli.getNom().equals(nom));
	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCompte(Client titulaire, Compte compte) {
		titulaire.getComptes().add(compte);

	}

}
