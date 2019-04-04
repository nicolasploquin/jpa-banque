package fr.eni.formation.banque.jpa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.ClientDao;

public class ClientDaoImpl implements ClientDao {
	
	
	@PersistenceContext(name="emfactory")
	private EntityManager em; // = JpaUtil.getEM();
	
	private static ClientDaoImpl instance = null;

	private ClientDaoImpl() {
		
	}
	
	public static ClientDaoImpl getInstance() {		
		if(instance == null) {
			instance = new ClientDaoImpl();
		}
		return instance;
	}

	@Override
	public Client create(String nom, String prenom) {
				
		em.getTransaction().begin();
		
		Client client = new Client(nom,prenom);
		em.persist(client);
		
		em.getTransaction().commit();
		
		return client;
	}

	@Override
	public Stream<Client> readAll() {
		return em.createQuery("from Client").getResultStream();
	}
	
	@Override
	public Client read(long id) {		
		return em.find(Client.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Stream<Client> read(String nom) {
		
		List<Client> clients = Collections.emptyList();

		String jpql = "from Client as cli where cli.nom like :nom";
		Query query = em.createQuery(jpql);
		query.setParameter("nom", "%"+nom+"%");
//		clients = query.getResultList(); // getSingleResult
//		return clients;
		
		return query.getResultStream();
	}

	@Override
	public void delete(Client client) {
		em.getTransaction().begin();

		em.remove(client);

		em.getTransaction().commit();
	}

	@Override
	public void delete(long id) {
//		EntityTransaction tx = em.getTransacti	²on();
//		if(!tx.isActive()) tx.begin();
		
		em.getTransaction().begin();

		em.remove(read(id));
		
		em.getTransaction().commit();
	}
	
	@Override
	public void addCompte(Client titulaire, Compte compte) {
		em.getTransaction().begin();
		
//		titulaire.getComptes().add(compte);
		compte.setTitulaire(titulaire);
//		em.merge(compte);
		
		em.getTransaction().commit();

		em.refresh(titulaire);

	}

}
