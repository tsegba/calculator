# Exercice 1 – SQL (~30min)
### La base de données d’un site de e-commerce est modélisée de la manière suivante :



	Une table « PRODUITS » représentant la liste des produits au catalogue de la plateforme. A chaque produit est associé un identifiant (clé primaire de la table), un libellé ainsi qu’un prix en euros.

| ID_PRODUIT   | LIBELLE_PRODUIT     | PRIX      |
| ------------- | ------------- | ------------- |
| ed278ced-5e61-4a34-b6e5-ddcd9c150749 | iPhone 13 64 Go Argenté |899.99|


	Une table « PANIERS » représentant les paniers des utilisateurs. À chaque panier est associé un identifiant (clé primaire de la table), l’identifiant du compte associé, une date de création, ainsi qu’une date de commande renseignée uniquement lorsque le processus d’achat est arrivé à son terme.

|ID_PANIER	|ID_UTILISATEUR	|DATE_CREATION	|DATE_COMMANDE|
| ------------- | ------------- | ------------- | ------------- |
|80205f85-826c-4f54-82c8-8cb18528ec16	|zlatan@hotmail.fr	|12/01/2018 09 :45	|12/01/2018 09 :58|
|6b2d7899-5c31-451a-bc7f-ec903c2e18f9	|messi@hotmail.com	|29/03/2018 20:02	|NULL

Le cycle de vie des paniers pour chaque utilisateur est le suivant : lors de la première connexion, un premier panier est créé (cf. deuxième ligne de l’exemple). A la validation de la commande, le champ DATE_COMMANDE de ce panier est renseigné ce qui a pour effet de l’inactiver et un nouveau panier est créé.

	Une table « ARTICLES » représentant chaque article présent dans chaque panier. A chaque article est associé un identifiant (clé primaire de la table), l’identifiant du panier associé (clé étrangère vers PANIERS) ainsi que l’identifiant du produit sélectionné (clé étrangère vers « PRODUITS »).

|ID_ARTICLE	|ID_PANIER	|ID_PRODUIT|
| ------------- | ------------- | ------------- |
|99720b18-eebf-4d27-ba42-35185c34519b	|80205f85-826c-4f54-82c8-8cb18528ec16	|ed278ced-5e61-4a34-b6e5-ddcd9c150749|
|6b2d7899-5c31-451a-bc7f-ec903c2e18f9	|80205f85-826c-4f54-82c8-8cb18528ec16	|ed278ced-5e61-4a34-b6e5-ddcd9c150749|

### Questions:
1)	Que signifie NULL dans l’exemple de la table PANIERS (techniquement et fonctionnellement).
2)	Écrire une requête pour récupérer l’identifiant du panier actif pour un utilisateur.
3)	Quels sont les points à vérifier pour une exécution de la requête en un temps correct ?
4)	Ecrire une requête pour remonter pour chaque article les informations suivantes : (ID_PANIER, LIBELLE_PRODUIT, PRIX)
5)	Écrire une requête pour remonter pour chaque panier créé en 2018 le nombre d’articles associés (ID_PANIER, NOMBRE_ARTICLES)
6)	La requête écrite à la question 5) remonte-t-elle les paniers sans articles ? Si non, adapter la requête pour remonter également les lignes de la forme (ID_PANIER, NOMBRE_ARTICLES = 0) 

#### Réponses:
> 1) La valeur NULL représente une date de commande non renseignée, cela pour marquer un panier de la session de l'utilisateur non encore validé, le panier actif de l’utilisateur

> 2) SELECT ID_UTILISATEUR FROM PANIERS WHERE DATE_COMMANDE IS NULL;

> 3) Vérifier la présence d’index sur les colonnes ID_UTILISATEUR et DATE_COMMANDE

> 4) SELECT a.ID_PANIER, p.LIBELLE_PRODUIT, p.PRIX FROM ARTICLES  a, PRODUIT p WHERE a.ID_PRODUIT = p.ID_PRODUIT

> 5) SELECT a.ID_PANIER, COUNT(NOMBRE_ARTICLES) as NOMBRE_ARTICLES FROM ARTICLES a, PANIERS p WHERE a.ID_PANIER = p.ID_PANIER AND DATE_FORMAT(p.DATE_CREATION, "%Y" )= '2018'  GROUP BY a.ID_PANIER 

> 6) Telle que presentée, les paniers sans articles ne remontent pas.
   Pour corriger cela il faut utiliser une jointure à gauche. 
>> SELECT a.ID_PANIER, COUNT(ID_ARTICLE) as NOMBRE_ARTICLES FROM PANIERS p, LEFT JOIN ARTICLES a ON (a.ID_PANIER = p.ID_PANIER)
>> AND DATE_FORMAT(p.DATE_CREATION, "%Y" )= '2018'  GROUP BY a.ID_PANIER 

# Exercice 2 – Java (~15min)
### Identifier un maximum de problèmes dans le fichier TransfertBancaire.java : syntaxe, conventions, mauvaises pratiques, dysfonctionnements, etc.
#### Réponses:

> Utilisation de methode JDBC non sécurisée pour exécuter les requetes : Utilisation de concaténation sensible à une injection SQL

> Aucun contrôle sur la valeur la somme (montant du transfert) - positive ? dans la limite des transferts si necessaire?

> Utilisation des informations d'accès à la base de données directement dans la methode

> Erreur dans les requêtes update1 et  update2, mauvaise utilisation de la concatenation
> L'exécution de la update1 générera une exception 

> La fonction transfert presente clairmement un problème de nomenclature 

> La fonction tranfert n'est pas clairement comprehensible,\
> Il semble que ce soit un transfert de compte à compte du client
> cependant sans preciser le sens de l'operation si bien qu'une ereur pourrait facilement se glisser 
> comme c'est d'ailleurs où l'on constate que les deux compte sont debités.\
> Seuement en se referant à la dernière exception qu'on se rend compte du type d'opèration ('Probleme de transfert vers compte epargne')

> La requête update2 presente une erreur fonctionnelle dans le calcul du solde du compte epargne

> Mauvais gestion des erreurs 
>> Aucune exception levée en cas de 'Probleme d'identification'\
>> Aucune exception levée en cas de 'Probleme de transfert vers compte epargne'

> Des controles en moins relatifs la disponibilité de somme à transferer du compte de depart (le compte courant)

> Les object compteCourant et compteEpargne ne devrait pas etre initialié à nouveau dans la boucle while

> Muavaise gestion de la connexion à la base 
>> Plusieurs connexions ouvertes à la même base, alors qu'une seule suffirait
>
>> Aucune connexion n'a été fermée et aucune ressource libérée

> Deux requetes pour recpuerer les comptes alors qu'il est preferable de le faire en une fois  



# Exercice 3 – Angular & Spring Boot (~1h15)

>L’objectif de cet exercice est de créer une calculette avec un frontend Angular et un backend Spring Boot :\
Le frontend contiendra une interface web représentant une calculette et permettant à l’utilisateur d’exécuter des opérations mathématiques de base (+,-,×,÷) et de les chainer (exemple : 32-10+48).\
Le backend contiendra une route par opérateur prenant chacune deux nombres en paramètres et retournant le résultat de l’opération.\
Vos serez évalués sur la structure du projet, l’algorithme, la qualité du code, l’esthétique et le fonctionnement end-to-end.\
Pour que notre évaluation soit pertinente, il est donc important que ce soit votre propre code et non pas du copié/collé.\
Astuce : le fonctionnement end-to-end ne représente qu’un des cinq axes d’évaluation, ne consacrez pas trop de temps sur la configuration si ça ne fonctionne pas.

## Réponse 
> Se referer aux sous repertoires
>> calculator-api => API
>
>> calculator => Client web