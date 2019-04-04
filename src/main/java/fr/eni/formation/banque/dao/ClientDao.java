package fr.eni.formation.banque.dao;

import java.util.List;
import java.util.stream.Stream;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;

public interface ClientDao {
	
	Client create(String nom, String prenom);
	
	Stream<Client> readAll();

	Client read(long id);
	Stream<Client> read(String nom);
	
	void delete(Client client);
	void delete(long id);
	
	void addCompte(Client titulaire, Compte compte);

}
