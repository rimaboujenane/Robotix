# README

## Description
Robotix est une plateforme innovante dédiée à la gestion intelligente et collaborative des robots, visant à optimiser les opérations et à enrichir l'expérience des utilisateurs dans le domaine de la robotique. Avec un accent sur la technologie de pointe et la facilité d'utilisation, Robotix offre des fonctionnalités avancées pour enregistrer, surveiller et gérer les robots en temps réel. La plateforme permet aux utilisateurs de suivre les performances de leurs robots, de visualiser des métriques détaillées telles que la position, la vitesse, la consommation de batterie et l'utilisation des ressources matérielles. En favorisant la gestion proactive des composants, Robotix facilite également l'achat et l'intégration de nouvelles pièces pour optimiser les capacités et les fonctionnalités des robots.

## Fonctionnalités
### Pour les utilisateurs :
- S'inscrire comme utilisateur et s'authentifier :
  - Créer un compte utilisateur avec les informations suivantes : nom, prénom, pseudo (unique), mot de passe, adresse courriel, téléphone, nom de la compagnie (optionnel), liste d'intérêts (jusqu'à 10)
- Se connecter à son compte
- Modifier son profil
- Suivre un utilisateur
- Accumuler des points en participant à des activités et voir ses points et son classement parmi les autres utilisateurs
- S'inscrire ou se désinscrire à un intérêt
- Sélectionner et mettre à jour une liste d'intérêts 
- Recevoir et consulter des notifications pour diverses actions et événements (nouveaux suiveurs, nouvelles activités, problèmes des robots, etc.)
- Trouver un fournisseur

#### Robots et flotte
- Enregistrer un robot
- Acheter et enregistrer des composantes
- Afficher l'état des ses robots
- Afficher les métriques de sa flotte

#### Activités
- Créer des activités
  - Définir des activités en spécifiant les tâches à accomplir, les robots impliqués, et les objectifs à atteindre
- Ajouter, modifier et/ou supprimer des activités
- Afficher les activités qu'il maintient
- S'inscrire à une activité créée par d'autres utilisateurs

#### Tâches
- Créer des tâches
- Ajouter, modifier et/ou supprimer des tâches

#### Actions
- Créer des actions
- Ajouter, modifier et/ou supprimer des actions

                   
### Pour les fournisseurs :
- S'inscrire comme fournisseur et s'authentifier :
  - Créer un compte utilisateur avec les informations suivantes : nom (unique), mot de passe, adresse, email, téléphone et capacité de fabrication
- Modifier son profil
- Gérer ses composantes
- Enregistrer une composante
- Recevoir et consulter des notifications concernant l'achat de composantes
  

## Organisation des fichiers
Le projet est origanisé selon la structure suivante
```
/ 
├── Exigences/
│   ├── Rapport du devoir1
│   └── Diagramme de cas d'utilisation
├── Analyse/
│   ├── Rapport du devoir2
│   └── Diagrammes d'activités
├── Conception/
│   ├── Diagramme de classes
│   └── Diagrammes de séquence
├── Implémentation/
│   ├── Code source de l'application/ -- code source
└── README.md
```

## Manuel d'utilisation
Étant un prototype, le jeu de donnée est dynamique, ceci veut dire nous n'avons pas une base de donnée prédéfinie, à chaque connexion et inscription le système enregistre les noms de fournisseurs, d'utilisateur, ce qui forment notre base de données. Il faudra donc créer des comptes Fournisseurs en premier avant de tester quelques sections dans le compte Utilisateur. 


### Connexion
Pour se connecter à l’application, vous pouvez créer un compte utilisateur ou fournisseur, vous pouvez choisir l'une des options suivantes en tapant le chiffre correspondant.
- [1] Utilisateur
- [2] Fournisseur

### Création d’un compte Utilisateur
- Entrez votre nom.
  - Votre compte est donc créé, votre username et ID sont affichés.
- Entrer au moins 10 intérêts.


### Menu principal Utilisateur
À partir du menu principal, dans le rôle d’utilisateur, vous pouvez choisir l'une des options suivantes en tapant le chiffre correspondant.
- [1] -> Voir inventaire.
- [2] -> Voir base de données de fournisseurs et composantes.
- [3] -> Définir mouvement.
- [4] -> Définir opération.
- [5] -> Définir tâche.
- [6] -> Construire un robot.
- [7] -> Acheter composante.
- [8] -> Consulter utilisateurs.
- [9] -> Voir notifications.
- [10] -> Gérer opérations.
- [11] -> Gérer tâches.
- [12] -> Quitter.

  
#### Voir inventaire
Dans cette section, vous pouvez accéder à votre inventaire et vérifier la quantité existante de chaque composant.
- CPU
- Roues 
- Bras 
- Helices 
- Cameras 
- Haut-Parleurs 
- Micros 
- Ecrans 

#### Voir base de données de fournisseurs et composantes
Dans cette section, vous pouvez accéder à la base de données des fournisseurs et les composantes qu’ils fournissent.


------- TO DO ----------
#### Définir mouvement
#### Définir opération
#### Définir tâche.
#### Construire un robot.


#### Acheter composante.
Dans cette section, vous pouvez acheter une composante. 
Vous devez entrer le nom du fournisseur de la composante que vous voulez acheter, il faut que ce fournisseur se trouve dans la base de données. 
Vous devez ensuite entrer le nom de la composante que vous voulez acheter, il faut que le fournisseur que vous avez choisit ait cette composante dans son inventaire.

#### Consulter utilisateurs.
Dans cette section, vous pouvez consulter des profils d’utilisateurs Robotix. Entrez le numéro correspondant pour voir un profil. Vous pouvez alors suivre l’utilisateur ou retourner au menu principal.

#### Voir notifications.
Dans cette section, vous pouvez consulter vos notifications. Vous trouverez des exemples de notifications d’utilisateurs qui ont commencé à vous suivre.

#### Gérer opérations/actions.
Dans cette section, vous pouvez consulter les opérations/actions que vous avez crées, il faut en avoir créer pour pouvoir consulter cette section.
- Vous devez entrer le numéro correspondant pour voir une opération/action en détail. Ceci affichera tous les détails de cette opération/action.
- Vous pouvez ensuite soit supprimer l'opération/action ou la modifier selon le chiffre que vous tapez.

#### Gérer tâches.
Dans cette section, vous pouvez consulter les tâches que vous avez crées, il faut en avoir créer pour pouvoir consulter cette section.
- Vous devez entrer le numéro correspondant pour voir une tâche en détail. Ceci affichera tous les détails de cette tâche.
- Vous pouvez ensuite soit supprimer la tâche ou la modifier selon le chiffre que vous tapez.

#### Quitter.
Vous quittez l’application dans cette section.



### Création d’un compte Fournisseur
Entrez le nom d'entreprise.
Votre compte est donc créé, votre username et ID sont affichés.
Vous pouvez choisir l'une des options suivantes en tapant le chiffre correspondant.
[1] -> Voir base de données de fournisseurs et composantes.
[2] -> Fournir composante.
[3] -> Quitter.

#### Voir base de données de fournisseurs et composantes
Dans cette section, vous pouvez accéder à la base de données des fournisseurs et les composantes qu’ils fournissent.

#### Fournir composante
Dans cette section, il faut entrer le nom de la composante que vous voulez fournir.

#### Quitter
Vous quittez l’application dans cette section.
