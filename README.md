# LP3 Android - LP CDAD

## README pour le TP 34 noté

Vous trouverez mon tp dans le dossier `/tps/tp34`.

### TUTO

L'application est simple d'utilisation :

On entre un mot dans la barre de recherche, en haut de l'écran. Ensuite on clique sur go. La liste des images s'affiche alors. On clique sur une image et elle devient imédiatement notre fond d'écran. On peut changer de page grâce aux bouttons dédiés.

### Explication du code

La pagination est gérée par une variable, calculé à chaque fois que l'utilisateur clique sur les bouttons "prev"/"next". Lorsqu'ils sont cliqués, on renvoie une requête à l'api en changeant la query string pour avoir la page suivante.

Pour le recyclerView, celui çi s'initialise dès la première réponse de l'api. La classe PhotoAdapter s'occupe de le gérer. Il va gérer les objets (les afficher, nottement l'image avec le package **picasso**). Cette classe va aussi définir leurs comportements (changer le fond d'écran au clic par exemple). Cette classe demande simplement une ArrayList de l'objet photo pour fonctionner.

