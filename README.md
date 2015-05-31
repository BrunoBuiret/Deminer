Projet personnel encadré
========================
Réalisation d'un démineur en Java en appliquant le modèle de conception MVC et
la librairie graphique Swing.

## Interactions vue / modèle
* Lorsque l'utilisateur effectue un clic gauche sur une case
  * Si la case est __vide__ alors
    * Si la case est une mine, le jeu est terminée et l'utilisateur a perdu
    * Si la case n'est pas une mine et qu'il y a des mines auteur, on écrit le nombre de mines adjacentes
    * Si la case n'est pas une mine et qu'il n'y a pas de mines autour, alors on
propage l'écriture du nombre de mines adjacentes jusqu'à ce que toutes les cases
vides atteignables par adjacence ait été testées
* Lorsque l'utilisateur effectue un clic droit sur une case
  * Si la case est __vide__ alors on bascule le drapeau
  * Si la case n'est __pas vide__ alors on ne fait rien

## Crédits
* Icônes extraits de la version 3.5.2 de [Fugue Icons](http://p.yusukekamiyamane.com/) et
de [IconFinder](https://www.iconfinder.com/icons/1232/bomb_explosive_icon#size=128)