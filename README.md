# README

## Description
Robotix est une plateforme innovante dédiée à la gestion intelligente et collaborative des robots, visant à optimiser les opérations et à enrichir l'expérience des utilisateurs dans le domaine de la robotique. Avec un accent sur la technologie de pointe et la facilité d'utilisation, Robotix offre des fonctionnalités avancées pour enregistrer, surveiller et gérer les robots en temps réel. La plateforme permet aux utilisateurs de suivre les performances de leurs robots, de visualiser des métriques détaillées telles que la position, la vitesse, la consommation de batterie et l'utilisation des ressources matérielles. En favorisant la gestion proactive des composants, Robotix facilite également l'achat et l'intégration de nouvelles pièces pour optimiser les capacités et les fonctionnalités des robots.

## Fonctionnalités
#### Compte
- S'inscire comme utilisateur simple ou comme fournisseur.
- S'authentifier pour accèder à son compte.

#### Profil
- Accèder aux informations du compte.
- Modifier ses informations.

### Pour les utilisateurs :
#### Robots
- Enregistrer un robot.
- Afficher l'état des ses robots.

#### Activités
- Afficher les activités existantes.
- S'inscrire et/ou se désinscrire  d'une activité.

#### Notifications
- Afficher les notifications reçues durant la session.

#### Composants
- Acheter des composants.
- Chercher un fournisseur.
- Chercher une composante.
  
### Pour les fournisseurs :
#### Composantes
- Enregistrer une composante.
- Afficher mes composantes.
- Modifier et/ou supprimer une composante.
  

## Organisation des fichiers
Le projet est organisé selon la structure suivante :
```
/ 
├── Exigences/
│   ├── Rapport du devoir 1
│   └── Diagramme de cas d'utilisation
├── Analyse/
│   ├── Rapport du devoir 2
│   └── Diagrammes d'activités
├── Conception/
│   ├── Diagramme de classes
│   └── Diagrammes de séquence
├── Implémentation/
│   ├── doc/ -- Dossier contentant la documentation de l'application générée avec Javadoc 
│   ├── src/ -- Dossier contentant le code source de l'application
│   ├── test/ -- Dossier contentant les tests unitaires JUnit
│   └── robotix.jar
└── README.md
└── rapport.html
```


## Manuel d'utilisation
Votre première fois sur Robotix? Inscrivez-vous! Sinon connectez-vous! 

### Création d’un compte Utilisateur
Une fois que Robotix est lancé, en dessous de la section "Connexion", cliquez sur :
- Pour un compte Utilisateur : "Première fois sur Robotix? Créez un compte dès maintenant!".
- Pour un compte Fournisseur : "Devenir Fournisseur dès maintenant!".
Ensuite, la page d'inscription apparaîtra avec tous les champs à remplir.

### Connexion
Pour vous connecter à l’application, veuillez entrer votre :
- Identifiant -> l'adresse email avec laquelle vous vous êtes inscrit.
- Mot de passe -> le mot de passe choisi lors de l'inscription.
- Choisir le type de compte que vous avez -> sélectionnez si vous êtes utilisateur ou fournisseur.

### Menu principal Utilisateur
Une fois connecté, le menu principal s'affichera. Vous aurez les options suivantes :
- Profil
- Notifications
- Robots
- Activités
- Composantes
- Déconnexion
- Quittez

#### Profil
Dans l'onglet Profil, vous pouvez consulter toutes vos informations et vous avez la possibilité de les modifier.
- Pour modifier, il suffit de cliquer sur le champ que vous désirez modifier, entrez les nouvelles informations, puis cliquez sur "Enregistrer les modifications".
- Pour annuler les modifications ou pour retourner au menu principal, il suffit de cliquer sur le bouton retour.

#### Notifications
Dans l'onglet Notifications, vous pouvez accéder aux messages du système lors de votre session.
- Vous avez la possibilité de les supprimer à l'aide de la croix à droite du message.

#### Activités
Dans l'onglet Activités, vous pouvez consulter en temps réel les auxquelles auquelles vous êtes inscrit ainsi que les autres activités disponibles.
Pour effectuer une action, appuyez sur le bouton à droite de l'activité. Vous avez la possibilité de: 
- Vous inscrire à une activité si vous ne l'êtes pas.
- Vous désinscrire si vous vous êtes inscrit à une activité.


### Menu principal Fournisseur
Une fois connecté en tant que fournisseur, le menu principal s'affichera. Vous aurez les options suivantes :
- Profil
- Mes Composants
- Enregistrer Composants
- Déconnexion
- Quittez

#### Profil
Dans l'onglet Profil, vous pouvez consulter et modifier vos informations.
- Pour modifier, cliquez sur le champ à modifier, entrez les nouvelles informations, puis cliquez sur "Enregistrer les modifications".
- Pour annuler les modifications ou revenir au menu principal, cliquez sur le bouton retour.

#### Enregistrer Composants
Dans l'onglet Enregistrer Composants, vous pouvez entrer les détails de la composante :
- Nom
- Type
- Description
- Prix
Cliquez sur "Enregistrer" pour enregistrer la composante, puis sur "Retour" pour revenir au menu principal.

#### Mes Composants
Dans l'onglet Mes Composants, vous trouverez une liste déroulante affichant les composants et leurs détails.
- Pour modifier un composant, cliquez dessus, modifiez les champs voulus, puis cliquez sur le bouton "Modifier".
- Pour supprimer un composant, sélectionnez-le, puis cliquez sur le bouton "Supprimer".
- Pour revenir au menu principal, cliquez sur le bouton retour.



