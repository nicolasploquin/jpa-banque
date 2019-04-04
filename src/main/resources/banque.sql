
INSERT INTO banque.client (nom,prenom) VALUES 
	('Troadec','Nolwenn'),
	('Leblanc','Marc'),
	('Martin','Leandre'),
	('Lenoir','Sophie'),
	('Legac','Erwann'),
	('Lebreton','Solenn'),
	('Meyer','Nils'),
	('Durand','Marie');


INSERT INTO banque.compte (numero,intitule,idClient) VALUES ('12345678','Compte Courant',1);

INSERT INTO banque.operation (date,libelle,montant,type,idCompte) VALUES 
	('2011-04-01','Salaire Renault',1300.00,'Credit',1),
	('2011-04-02','SFR',54.00,'Debit',1),
	('2011-04-03','EDF',78.00,'Debit',1),
	('2011-04-04','E.Leclerc',65.00,'Debit',1),
	('2011-04-05','CPAM',22.00,'Credit',1),
	('2011-04-06','TAN Avril',48.00,'Debit',1);
