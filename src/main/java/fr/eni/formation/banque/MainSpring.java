package fr.eni.formation.banque;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.eni.formation.banque.dao.ClientDao;

public class MainSpring {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
//		String texte = new String("toto");
		String texte = (String)context.getBean("message");
		
		System.out.println(texte);
		
		
		Client unClient = context.getBean(Client.class);
		System.out.println(unClient);
		
//		Client cli2 = (Client)context.getBean("cli2");
//		System.out.println(cli2);
		
//		ClientDao daoClient = (ClientDao) context.getBean("clientDao");
		ClientDao daoClient = context.getBean(ClientDao.class);
		
		if(daoClient.readAll().count() < 5) {
			Client cli1 = daoClient.create("Durand","Marc");
			
			Compte cpt1 = new Compte("67890123","Autre compte");
			Compte cpt2 = new Livret("78901234","Livret B",2.0);
			
			daoClient.addCompte(cli1, cpt1);
			daoClient.addCompte(cli1, cpt2);
		}
		daoClient.readAll().forEach(System.out::println);
		
	}
	
	
}
