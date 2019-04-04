package fr.eni.formation.banque;

import fr.eni.formation.banque.dao.ClientDao;
import fr.eni.formation.banque.mem.ClientDaoMem;

public class Main {
	
	public static void main(String[] args) {
	
		String texte = "toto";
		
		System.out.println(texte);
		
		
	}
	
	
	public static void main5(String[] args) {
//		ClientDao daoClient = spring.getClientDao();
		
		ClientDao daoClient = ClientDaoMem.getInstance();
		
		
		if(daoClient.readAll().count() < 5) {
			Client cli1 = daoClient.create("Durand","Marc");
			
			Compte cpt1 = new Compte("67890123","Autre compte");
			Compte cpt2 = new Livret("78901234","Livret B",2.0);
			
			daoClient.addCompte(cli1, cpt1);
			daoClient.addCompte(cli1, cpt2);
		}
		daoClient.readAll().forEach(System.out::println);

	}
	
	/*	
	public static void main4(String[] args) {

		ClientDao daoClient = new ClientDaoImpl();
		CompteDao daoCompte = new CompteDaoImpl();
		
		Client cli1 = daoClient.create("Leblanc","Marc");
		
		Compte cpt1 = new Compte("45678901","Autre compte");
		Compte cpt2 = new Livret("56789012","Livret A",2.0);
		
		daoClient.addCompte(cli1, cpt1);
		daoClient.addCompte(cli1, cpt2);
		
		Operation ope1 = new Credit(LocalDate.now(), "Salaire Renault", 1000.0);
		Operation ope2 = new Debit(LocalDate.now(), "E.Leclerc", 100.0);
		Operation ope3 = new Debit(LocalDate.now(), "Engie", 80.0);
		Operation ope4 = new Debit(LocalDate.now(), "Free Mobile", 20.0);
		
		cpt1.getOperations().add(ope1);
		cpt1.getOperations().add(ope2);
		cpt1.getOperations().add(ope3);
		cpt1.getOperations().add(ope4);
		
		daoCompte.create(cpt1);
		daoCompte.create(cpt2);
		
		daoCompte.virement(cpt1, cpt2, "Placement épargne", 300.0);
		
		
		double solde = cpt1.getOperations().stream()
			.mapToDouble( ope -> ope.getMontantRelatif() )
			.sum();
		System.out.printf("Le solde du compte cpt1 est de %10.2f €\n", solde);
		
		cpt1.majSolde();
		
		System.out.println(cpt1);
		System.out.println(cpt2);
		
		daoCompte.readAll().forEach(System.out::println);
		
		System.out.println(daoCompte.read("45678901"));
		
		
	}
	public static void main3(String[] args) {
		
		ClientDao daoClient = new ClientDaoImpl();
		CompteDao daoCompte = new CompteDaoImpl();
		
		if(daoClient.readAll().count() < 5) {
			Client cli1 = daoClient.create("Troadec", "Nolwenn");
			Client cli2 = daoClient.create("Leblanc", "Marc");
			Client cli5 = daoClient.create("Leblanc", "Marie");
			Client cli3 = daoClient.create("Durand", "Marie");
	
			Compte cpt1 = daoCompte.create("34567890", "Livret B");
			
			daoClient.addCompte(cli1, cpt1);
		}		
		
		double solde = daoCompte.read(1L).getOperations().stream()
			.mapToDouble(ope -> ope.getMontant())
			.sum();
		
		System.out.printf("Le solde du compte est %10.2f.\n", solde);
		
		long nbComptes = daoClient.readAll()
				.flatMap(cli -> cli.getComptes().stream())
				.flatMap(cpt -> cpt.getOperations().stream())
//				.collect(Collectors.toList()) // --> List<Operation>
				.count()
				;		
		System.out.printf("Il y a %d operation dans la banque.\n", nbComptes);
		
		System.out.println(daoCompte.read(1L));
	}
	
	
	
	
	
	
	
	
	public static void main2(String[] args) {
		
//		List<Client> clients = new LinkedList<>();
		
		ClientDao daoClient = new ClientDaoImpl();
		CompteDao daoCompte = new CompteDaoImpl();
		

		Client cli1 = daoClient.create("Troadec", "Nolwenn");
		Client cli2 = daoClient.create("Leblanc", "Marc");
		Client cli5 = daoClient.create("Leblanc", "Marie");
		Client cli3 = daoClient.create("Durand", "Marie");
		
		Client cli4 = daoClient.read(cli1.getIdClient());

		Compte cpt1 = daoCompte.create("23456789", "Livret A");
		
		daoClient.addCompte(cli1, cpt1);
		
		System.out.println(cli1);
		System.out.println(cli2);
		System.out.println(cli3);
		
		System.out.println(cpt1);
		
//		dao.delete(cli1);
//		dao.delete(cli2.getIdClient());
		
		
//		System.out.println(daoClient.read("a").count());
		daoClient.read("a")
			.sorted( (cl1,cl2) -> - cl1.getPrenom().compareToIgnoreCase(cl2.getPrenom()) )
			.forEach( (cli) -> { System.out.println(cli); } );
//			.forEach(System.out::println);

		int nbComptes = daoClient.read("a")
			.mapToInt( cli -> cli.getComptes().size() )
			.sum()
		;
		System.out.printf("Il y a %d comptes.\n", nbComptes);
		
		daoClient.read("a")
			.filter( Main::anonyme )
			.filter( (Client cli) -> {
				return cli.getComptes().size() > 0 ;
			})
			.filter( cli -> cli.getComptes().size() > 0 )
			.findFirst()
			.ifPresent(System.out::println);
//			.forEach(System.out::println);
		
		
//		for (Client cli : ) {
//			System.out.println(cli);
//		}
//		
	
	}
	
	private static boolean anonyme(Client cli) {
		return cli.getComptes().size() > 0;
	}
	
	

	public static void main1(String[] args) {
		System.out.println("Ma première application Java avec JPA.");
		
		// Initialisation du persistence unit : localhost-mysql-banque-jpa
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("localhost-mysql-banque-jpa");

		System.out.println("Création de l'entity manager JPA.");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		Client cli1 = new Client();
		em.persist(cli1);
		cli1.setNom("Troadec");
		
		
		cli1.setPrenom("Nolwenn");
//		em.flush();

		Client cli2 = new Client("Leblanc","Marc");
		em.persist(cli2);
		
		em.getTransaction().commit();
		
		factory.close();
		
		System.out.println(cli1);
//		System.out.println(cli2);
	}
*/
}
