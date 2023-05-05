# gla calcul itineraire frontend


![](/img/train_go.png " TrainGo")


Bienvenue sur trainGo votre partenaire de voyage. Notre application
développée en Java / Swing vous permet de vous rendre facilement d'un point A à un point B avec
un certain nombre d'options. Découvrons ensemble toutes les possibilités :

-----

## Execution des tests

L'exécution des tests pour la partie graphiques est moins interessant que notre partie serveur
étant donné qu'un grand nombre de test sont des tests difficilement réalisables pour la partie graphique. 
Cependant, le lancement des tests s'effectue avec **'./gradlew test'**

## Compilation du programme

Pour compiler le projet il est préférable de supprimer le fichier build
à l'aide de la commande **"./gradlew clean"**

Compiler depuis la racine du projet avec **'./gradlew build'**

## Configuration du programme

Il est possible de configurer le fichier configuration pour vous connecter
à votre serveur lancer au préalable situer dans le repertoire
**"src/main/ressources/config/network.json"**

Par defaut le l'ip est **'localhost'** et le port **'12345'**

## Execution du programme

Pour executer le programme, **il faut au préalable avoir lancer la partie serveur.**

- 3 possibilités existent pour executer le programme :
    - Utilisation de gradle avec la commande à la racine './gradlew run'
    - Utilisation du jar fourni à la construction dans le 'build/libs/' avec le jar 
    - À partir de la dernière release disponible.

## Utilisation du programme

Le programme s'utilise de 2 manières possibles :

-Avec le graphique [Voir reference vers graphique](#utilisation-avec-le-graphique)

-Avec le terminal [Voir reference vers terminal](#utilisation-avec-le-terminal)

-----

## Utilisation avec le graphique:

Dès l'ouverture du programme avec un certain nombre de possibilités
d'utilisations.

- Le menu vous permet de naviguer entre chaque menu. Il suffit de 
cliquer dessus (voir image)

- La map à droite permet de naviguer dans la map du monde entier. Elle est interactive et composée de 2 boutons :
  - Bouton **"Placez marqueur"** ce bouton vous permet de placer un marqueur à la position A et un autre à la position B. Cela simplifie l'entrée et le choix de coordonnées.
  - Bouton **"Nettoyez la map"** ce bouton vous permet de nettoyer chaque point de la map

![](/img/screenshot/map_demo.png " Map ")


- Dans les champs d'entrée "Depart" et "Arrive" vous pouvez écrire le nom de vos stations
ou des coordonnées sous le forme (x, y).

![](/img/screenshot/champ_demo.png " Champs d'entree ")


- Les options de recherches sont configurables
    - Type de trajet : Vous avez la possibilité d'avoir un trajet en temps avec un temps assez court ou Distance avec la plus petite distance.
    - Section à pied : limite ou non la marche entre les stations
    - Depart à : Permet de choisir l'horaire de depart de votre station

![](/img/screenshot/option_demo.png "Option")


- Le button valider permet d'envoyer la requête au serveur. La réponse 
sera directement dans le panel ci-dessous (voir image)

![](/img/screenshot/valider_demo.png " Valider button ").


- Le bouton "Voir sur la map toutes les stations" vous permet de voir votre recherche sur la map de manière interactive.

![](/img/screenshot/voirmap_demo.png " VoirMap ").



- Le panel d'horaire vous permet de voir toutes les stations à une heure donnée

![](/img/screenshot/horaire_demo.png " Horaire").


- Le panel d'historique vous permet de voir tous vos trajets recherchés.
il y a également le bouton "Voir sur la map toutes les stations" vous permet de voir votre recherche sur la map de manière interactive.

![](/img/screenshot/historique_demo.png " Historique").



### Utilisation avec le terminal:

Une fois l'application démarrée, une invite de commande est mis à votre disposition vous permettant d'effectuer les commandes suivantes:

#### ROUTE
La commande ```ROUTE``` permet de demander le trajet entre de 2 points, que cela soit des noms de stations ou des coordonnées.
- **ROUTE \<addr1> \<addr2>**
  - Demande un trajet entre deux points
- **ROUTE \<addr1> \<addr2> \<time> TIME**
  -  Demande un trajet entre deux points en spécifiant le trajet le plus rapide
- **ROUTE \<addr1> \<addr2> \<time> DISTANCE**
  -  Demande un trajet entre deux points en spécifiant le trajet le plus court

#### TIME
La commande ```Time``` permet d'obtenir les horaires de passage d'un train à une station à partir d'une certaine heure

- **TIME \<station> \<hh:mm>**

#### SEARCH
La commande ```SEARCH``` permet d'obtenir la liste des stations ainsi que le coorespondance selon d'un préfix

- **SEARCH \<prefix>**

#### Kill
Cette commande ```KILL``` permet d'arrêter le server

- **kill**
