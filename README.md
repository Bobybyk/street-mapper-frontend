# gla calcul itineraire frontend


[center]![TrainGo Logo](/img/train_go.png " TrainGo").[/center]


Bienvenue sur trainGo votre partenaire de voyage. Notre application
développée en Java / Swing vous permet de vous rendre facilement d'un point A à un point B avec
un certain nombre d'options. Découvrons ensemble toutes les possibilités :

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
    - Avec le graphique (#voir reference vers graphique)
    - Avec le terminal (#voir reference vers terminal)


## Utilisation avec le graphique :

Dès l'ouverture du programme avec un certain nombre de possibilités
d'utilisations.

- Le menu vous permet de naviguer entre chaque menu. Il suffit de 
cliquer dessus (voir image)

- La map à droite permet de naviguer dans la map du monde entier. Elle est interactive et composée de 2 boutons :
  - Bouton **"Placez marqueur"** ce bouton vous permet de placer un marqueur à la position A et un autre à la position B. Cela simplifie l'entrée et le choix de coordonnées.
  - Bouton **"Nettoyez la map"** ce bouton vous permet de nettoyer chaque point de la map

[center]![Map](/img/screenshot/map_demo.png " Map ").[/center]


- Dans les champs d'entrée "Depart" et "Arrive" vous pouvez écrire le nom de vos stations
ou des coordonnées sous le forme (x, y).

[center]![Champs](/img/screenshot/champ_demo.png " Champs d'entree ").[/center]



- Les options de recherches sont configurables
    - Type de trajet : Vous avez la possibilité d'avoir un trajet en temps avec un corut temps ou Distance avec la plus petite distance.
    - Section à pied : limite ou non la marche entre les stations
    - Depart à : Permet de choisir l'horaire de depart de votre station

[center]![option](/img/screenshot/option_demo.png "Option").[/center]



- Le button valider permet d'envoyer la requête au serveur. La réponse 
sera directement dans le panel ci-dessous (voir image)

[center]![Validerbutton](/img/screenshot/valider_demo.png " Valider button ").[/center]



- Le bouton "Voir sur la map toutes les stations" vous permet de voir votre recherche sur la map de manière interactive.

[center]![VoirMap](/img/screenshot/voirmap_demo.png " VoirMap ").[/center]



- Le panel d'horaire vous permet de voir toutes les stations à une heure donnée
    - Le champs 'Depart' est
[center] ![Horaire](/img/screenshot/horaire_demo.png " Horaire").[/center]


- Le panel d'historique vous permet de voir tous vos trajets recherchés.
il y a également le bouton "Voir sur la map toutes les stations" vous permet de voir votre recherche sur la map de manière interactive.

[center] ![Historique](/img/screenshot/historique_demo.png " Historique"). [/center]




## Utilisation avec le terminal:

