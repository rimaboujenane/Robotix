# Logiciel Robotix

# Incrément 1: Exigences + Analyse
## Préambule
Tout au long du trimestre, vous allez développer le logiciel Robotix pour gérer les robots en suivant les techniques apprises en classe. Le développement suivra le modèle du processus unifié et sera divisé en trois livrables. Chaque livrable touche à tous les workflows, mais l'emphase est mise sur un workflow en particulier à chaque incrément. Vous êtes responsable de fournir une solution complète du projet à la livraison finale. Notez qu'à chaque incrément, le cadre du projet sera étendu. Il faut donc prévoir des changements dans les besoins.

Ce devoir se concentre sur le workflow des exigences. Le but est de mettre en pratique la collecte des besoins, la spécification des exigences et la construction de diagrammes de cas d'utilisation UML. De ce fait, les devoirs à venir se baseront sur celui-ci. Bien qu'essentiel dans un vrai projet commercial, vous n'avez pas besoin de produire un modèle d'affaire (business case) ni un cahier des charges.

## Présentation du contexte
Dans un monde où la robotique est un domaine en constante évolution, les robots sont de plus en plus utilisés dans divers secteurs tels que l’industrie, la santé, l’agriculture et bien d’autres encore.

Avec l’augmentation de l’utilisation des robots dans les industries et même les résidences, la nécessité d’un système de gestion efficace pour ces machines devient de plus en plus pressante.

## Spécifications pour le livrable 1
Nous pensons qu'une meilleure gestion des activités de contrôle et monitoring des actions effectuées par les robots et des métriques accessibles à tous réduiront la distance entre les acteurs. Ainsi nous proposons la création d'un outil, “l'outil Robotix” qui permettra à tout utilisateur de:

- Contrôler un ou plusieurs robots
- Allouer des tâches à un ou plusieurs robots
- Visualiser l'efficacité énergétique et l'utilisation de ressources des robots
- Signaler un problème à un robot
- Participer aux activités
- Trouver un fournisseur de composantes pour les robots
- Étant limité en temps, nous ne vous demandons pas plus qu'une implémentation où l'interaction est en ligne de commande.

### Liste de souhaits
Une fois connecté à Robotix, un utilisateur peut effectuer diverses opérations pour faire le suivi des actions, de l'utilisation de ressources, participer à des activités et entrer en contact avec des fournisseurs pour acquérir des robots et des composantes.

Pour le fonctionnement efficace de cet outil, nous assumons la présence d'un mécanisme d'inventorisation des robots, tel que l'utilisation de capteurs. En utilisant les données recueillies par les capteurs de chaque robot, nous pouvons formuler des métriques pour refléter la performance des robots et utiliser ces données pour générer des recommandations pour améliorer leur efficacité.

Pour faciliter le traitement électronique des robots, ceux-ci seront identifiés par un numéro de série fourni par le fournisseur. Ce numéro de série doit être unique à travers tous les composantes du système au complet. Il peut être un numéro séquentiel ou un UUID.

### S'inscrire comme utilisateur
Pour s'inscrire comme utilisateur, il faut fournir les informations suivantes:

- Profil: nom, prénom, pseudo (unique), adresse courriel, téléphone
- Nom de la compagnie: permet de l'associer à une organisation
Enregistrer un robot
Chaque utilisateur est habituellement responsable d'un ou de plusieurs robots dans sa flotte. En tout temps, il devrait pouvoir enregistrer et suivre un nouveau robot. Pour enregistrer un nouveau robot, il faut entrer le numéro de série du robot fourni par le fournisseur et l'identifier par un nom et un type. Ces deux propriétés sont définies au choix de l'utilisateur.

### Acheter des composantes
Pour construire un robot, un utilisateur doit avoir les composantes qu'il a acheté chez un ou plusieurs fournisseurs. Chaque robot doit avoir au minimum un CPU et une composante autre que le CPU.
Les types de composantes disponibles sont:

- CPU (obligatoire)
- Roues
- Bras
- Hélice
- Caméra
- Haut-parleur
- Micro
- Écran (texte seulement ou interface graphique)
Chaque composante doit avoir un nom, un type, une description et un prix.
Nous pouvons assumer que les fils pour connecter les composantes entre elles sont fournies par le fournisseur, et que le CPU prend en charge les connexions Wi-Fi et Bluetooth pour permettre la connexion entre les robots.
Contrôle des mouvements
Le système doit pouvoir contrôler les mouvements des robots, notamment en leur donnant des ordres de déplacement (vitesse et direction) et de stationnement. Un robot doit avoir les composantes nécessaires (roues ou hélices) pour se déplacer.

### Création des actions
Le système doit permettre la création d'actions des robots, dont les mouvements (décrits ci-dessus), diffuser des sons, parler, écouter et afficher du texte ou des graphiques.
Un robot doit avoir les composantes nécessaires pour exécuter ces actions (e.g., un robot doit avoir un haut-parleur pour émettre des sons, un micro pour écouter, un écran pour afficher, etc.)
Les actions sont créées indépendamment des robots et peuvent être assignés aux tâches.

### Gestion des tâches
Le système doit permettre la création de tâches pour les robots et leur affectation à des robots spécifiques en fonction de leur disponibilité et de leurs compétences. Un utilisateur peut également planifier ces tâches pour qu'un robot puisse les exécuter à un horaire spécifique ou avec une action déclencheur sans leur intervention.
Une tâche est définie comme une séquence d'actions (e.g., se déplacer, diffuser des sons, parler, écouter, afficher du texte ou des graphiques) d'un robot.
Les tâches peuvent être assignées directement aux robots ou à travers les activités.
Vous pouvez assumer que les actions seront exécutées dans l'ordre qu'elles apparaissent dans la liste d'actions. Chaque action dans une tâche aura une propriété de transition (après le précédent ou avec le précédent) pour indiquer si elle sera exécutée en parallèle ou en séquence.

### Afficher l'état des mes robots
L'état d'un robot présente sa position, sa vitesse, son niveau de batterie, sa consommation CPU et mémoire. L'usage fait référence à l'évolution (périodique) des données émises par les capteurs, offrant ainsi une trace (historique).

### Afficher les métriques de ma flotte
Les métriques (indicateurs, ratios agrégés par période) sont réparties entre celles liées à l'utilisation de chaque robot individuel et celles liées à la flotte (e.g., nombre de robots disponibles, état général, utilisation globale). Ces métriques devraient être facilement interprétables par tout utilisateur et significatives (pertinentes) pour mesurer correctement les activités des robots de sa flotte.

### Voir les activités que je maintiens
Les utilisateurs utilisent les robots pour des activités diverses, donc les jeux, l'éducation et la création. Ils peuvent également joindre à une communauté d'utilisateurs qui partagent les mêmes utilisations. Chaque activité est définie comme une série de tâches qui peut être assigné à n'importe quel robot pour atteindre un objectif spécifique et doit avoir une date de début et une date de fin. Un robot peut être assigné plusieurs tâches dans une même activité.

### Gestion de problèmes
Le système doit pouvoir détecter et gérer les erreurs et les anomalies qui surviennent pendant l'utilisation des robots, notamment en fournissant des alertes et des messages d'erreur. Avec ces alertes, le système peut proposer et exécuter les actions à prendre qui requièrent, dans certains cas, l'intervention de l'utilisateur.

### Trouver un fournisseur
Un utilisateur peut naviguer à travers la liste des fournisseurs enregistrés en utilisant un mécanisme de recherche et de filtre facilitant la localisation de fournisseurs spécifiques.

### S'inscrire comme fournisseur
Pour s'inscrire comme fournisseur, il faut d'abord avoir une compagnie (prérequis) et fournir:

- Profil: nom (unique), adresse, email, téléphone
- Capacité de fabrication
Activités
Pour enrichir leur expérience, les utilisateurs peuvent participer à diverses activités avec leur flotte de robots, individuelle ou collaborative. Les possibilités sont infinies et dépendent de la créativité des utilisateurs et des capacités des robots. Voici quelques exemples de catégories d'activités que les utilisateurs peuvent participer à:

### Jeux
Jouer à des jeux et des activités ludiques avec les utilisateurs, tels que des quiz, des énigmes, des défis ou des jeux de société, offrant des heures de divertissement et de plaisir.

### Éducation
Enseigner différentes compétences, telles que l'apprentissage d'une langue, la programmation informatique, les mathématiques, les sciences et l'histoire, peu importe l'âge des utilisateurs.

### Création
Créer des objets réels ou virtuels en utilisant des outils numériques tels que des logiciels de conception assistée par ordinateur (CAO). L'utilisateur peut également générer les objets créés avec l'impression 3D.

## Tâches
### Tâche 1 : Glossaire
Après s'être familiarisé avec le domaine d'affaires de Robotix, produisez un glossaire composé d'une description brève des termes clés, tel qu'illustré dans la Figure 11.3 du livre (p. 322).

Chaque terme avec son explication vaut 1 point. Chaque explication ne devra pas dépasser 50 mots. Vous aurez tous les points si vous produisez au moins 10 termes avec explications.

### Tâche 2 : Diagramme de cas d'utilisation UML
Identifiez les différents cas d'utilisation du système à construire. Vous devez les représenter sous forme d'un diagramme de cas d'utilisation UML en utilisant l'outil Visual Paradigm (VPP). Vous serez également évalué sur l'utilisation appropriée des éléments UML. Vous devez avoir entre 10 et 20 cas d'utilisation dans votre diagramme.

### Tâche 3 : Cas d'utilisation
Rédigez la description de chaque cas d'utilisation directement dans le rapport.html en utilisant le format suivant. Vous pouvez copier le code source HTML de l'exemple ci-dessous.

Cas d'utilisation : nom du CU en expression verbale.
But : mise en contexte, description brève, préconditions nécessaires.
Préconditions : préconditions nécessaires avant d'entammer le scéanrio principal.
Acteurs : Acteur 1 (principal), Acteur 2 (secondaire).
Scénario principal : description étape par étape, ordonnée numériquement
1. Étape 1
 1.1. Étape 1.1
2. Étape 2
3. Étape 3
Scénarios alternatifs : ramifications du déroulement principal, alternatives possibles à certaines étapes
1a.1. remplace étape 1 du scenario principal
1b.1. autre remplacement de la même étape
1b.2. suit 1b.1 indépendamment du scénario principal
Postconditions : État du système lorsque le scénario principal achève avec succès.

La tâche de description des cas d'utilisation doit être distribuée équitablement entre les membres de l'équipe : chaque membre de l'équipe doit être assigné à peu près le même nombre de cas d'utilisation à rédiger. Dans vos descriptions, vous devez avoir au minimum un cas d'utilisation par rôle (utilisateur et fournisseur). Vous devez rédiger la description pour 10 cas d'utilisation qui figurent dans votre diagramme valant 5 points chacun.

### Tâche 4 : Risques
Identifiez 5 risques potentiels du projet et classez-les par ordre de sévérité (voir la page 28 dans les diapositives des exigences (Chapitre 7) des notes de cours et page 90 du livre)

### Tâche 5 : Exigences non-fonctionnelles
Identifiez 5 besoins ou contraintes non-fonctionnelles du système.

### Tâche Bonus : Prototype en Java
Implémentez un premier prototype en Java de telle sorte qu'un minimum de 10 fonctionnalités identifiées dans vos cas d'utilisation soient prises en charge. Vous aurez 1 point par cas d'utilisation fonctionnel. C'est un prototype, donc le logiciel doit avoir « l'air » de fonctionner: il n'est pas nécessaire de faire persister les données. Il suffit que le prototype fonctionne pour un utilisateur et un fournisseur. C'est-à-dire que ces données peuvent être codées en dur (« hard coded »). Ce n'est pas important si ça ne fonctionne pas pour d'autres données.

Assurez-vous que le programme soit compilé en un fichier JAR. Vous pouvez voir les instructions pour le faire dans Eclipse ou IntelliJ ici.

Entendez-vous sur les normes de programmation à utiliser. Vous formez une même équipe, donc le code de toute l'application doit être uniforme. N'oubliez pas d'insérer des commentaires dans le code là où nécessaire. La tâche d'implémentation doit être distribuée équitablement entre les membres de l'équipe : chaque membre de l'équipe doit être assigné à peu près le même nombre de classes à implémenter. La communication entre vous est très importante, en particulier communiquez avec la personne responsable d'un module avec lequel votre module interagit.

Notez que vous n'êtes pas tenus de fournir une interface utilisateur graphique. Un menu par ligne de commande est suffisant.

### Collaboration
À la fin du rapport, affichez une capture d'écran des statistiques de votre dépôt GitHub. Vous aurez accès à ces statistiques sur l'onglet Insights de votre dépôt.

Assurez-vous de communiquer régulièrement au sein de votre équipe. Utilisez l'application Discord pour discuter et échanger. De ce fait, vous conservez une trace de vos décisions pour les itérations ultérieures. Pour faciliter la collaboration en équipe, vous devez utiliser le système de contrôle de révision GitHub. Faites régulièrement des soumissions GitHub (commit/push) de tous vos fichiers. Vous serez évalués sur la bonne utilisation de votre dépôt.

Les auxiliaires du cours sont les représentants du client. Si vous avez besoin de clarifications et de précisions, communiquez avec eux directement par Discord ou en personne.

## Barème
- Glossaire [10%]
- Diagramme de cas d'utilisation [25%]
- Description des cas d'utilisation [50%]
- Risques [5%]
- Besoins non-fonctionnels [5%]
- Bonne utilisation de GitHub et statistiques [5%]
- Bonus: Application Java (fichier JAR, code source) [10%]. Soumettre un programme qui ne compile pas aboutira à une pénalité de 5 points.
Téléversez toutes les images, fichiers source, et fichiers de données, et y faire référence dans le rapport. Vous devez inclure le dossier .git dans votre remise sur StudiUM pour sauver aux auxiliaires l'étape de devoir cloner votre repo à partir de GitHub. Votre repo GitHub et votre remise doivent être structurés de cette façon:

- Exigences/
  - Rapport du devoir 1
  - Diagramme de cas d'utilisation
- Implémentation/
  - Code source du prototype
## Ressources supplémentaires
Vous devez utiliser Visual Paradigm pour tous les diagrammes UML. Vous pouvez le télécharger, l'installer et l'activer à partir du lien disponible sur StudiUM.

Pour GitHub, vous pouvez utiliser le client Git intégré à votre IDE (IntelliJ ou Eclipse), ou le client Fork.

Pour communiquer en équipe vous pouvez utiliser Discord.

## Informations pratiques
Le devoir vaut 15% de la note finale.

Voir la date de remise sur StudiUM à 23h55. Tout retard engendrera une pénalité de 5% par jour pour un maximum de deux jours.

Le devoir est à faire en équipe de trois ou quatre. Aucun devoir effectué seul ne sera accepté. Pour former votre équipe, utilisez le module Formation des équipes pour les devoirs sur StudiUM, si ce n'est pas déjà fait.

La remise du devoir est un fichier ZIP qui comprend un fichier HTML simple (rapport.html) ainsi que tous les fichiers additionnels nécessaires (.jpg, .java, .txt, tous les autres fichiers que vous voulez remettre). Le rapport doit avoir un lien explicite à tous les fichiers et afficher toutes les images directement sur la page. Vous devez inscrire dans l'entête du rapport: le nom de tous les membres de votre équipe, vos matricules, vos courriels et le temps mis par chaque membre sur le devoir (pour des raisons statistiques uniquement). Votre solution doit être incluse en entier dans le body du rapport. Inscrivez toutes vos hypothèses. Puis décrivez votre solution pour chaque tâche sous forme de rapport.

De plus, le rapport doit inclure une section Distribution des tâches. Cette section doit énumérer toutes les tâches accomplies et le pourcentage de contribution de chaque membre par tâche. Si les pourcentages ne sont pas plus ou moins égaux, la note peut différer d'un membre à l'autre. Vous pouvez trouver un exemple du rapport ici.

Une seule personne par équipe remet le devoir complet sur StudiUM. La remise est pour le groupe au complet. Indiquez la personne qui soumet le devoir complet et le nom d'équipe.

Omettre le rapport engendrera une pénalité de 5%.
