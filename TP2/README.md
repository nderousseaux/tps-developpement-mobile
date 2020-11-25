# TP2 :  Fragments

Pour plus de détails lire la documentation officielle : https://developer.android.com/guide/components/fragments.html

Un fragment est un composant réutilisable qui est rattaché à une activité. Une activité peut contenir de multiples fragments. Un fragment peut contenir une vue  ; pour être visible, le fragment doit être inséré dans le layout de l'activité courante. Le fragment possède un cycle de vie qui lui est propre, mais qui est contraint par le cycle de vie de l'activité hôte.

Un fragment peut être attaché à une activité de manière statique, dans le gabarit XML de l'activité (balise *fragment*), ou dynamiquement, dans le code Java.
C'est cette dernière option qui est la plus intéressante.
Pour créer et insérer dynamiquement un fragment au sein de la vue d'une activité, il faut s'appuyer sur le manager de fragments ou  `FragmentManager`.
Si un fragment possède une vue, sa description peut être définie  dans un fichier XML.

## Utilisation des fragments

Depuis 2018, Google préconise de s'appuyer uniquement sur les fragments pour l'affichage des différents écrans de l'application.
Une application Android est composée dans ce cas d'une seule activité, point d'entrée de l'application, et de multiples fragments, chaque fragment représentant un écran de l'application. L'activité sera utilisée pour héberger et afficher les différents fragments.
Une portion de la vue de l'activité sera dédiée à l'affichage du fragment courant.
La navigation entre les fragments peut se faire de différentes manières.
Dans ce TP, nous allons voir comment naviguer entre les fragments à partir de l'activité principale.


### Exercice

- Créer un nouveau projet qui contient :
  - Une activité principale
  - Deux fragments
  
- La vue de l'activité, définie dans le fichier XML correspondant, contient :
  - une zone de texte pour le titre
  - deux boutons alignés : bouton1, bouton2
  - une vue qui affichera le fragment actif : un `FrameLayout` pourvu d'un identifiant 
  
- Les vues des fragments, définies dans les fichiers XML correspondants, contiennent :
  
  - Un texte avec le titre du fragment : "Je suis le fragment1" - "Je suis le fragment2"
  - Une zone d'édition de texte
  - Une image
  - Un bouton

- La  méthode `onCreateView` devrait ressembler à ça :

```java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	super.onCreateView(inflater, container, savedInstanceState);
	return inflater.inflate(R.layout.fragment,container,false);
}
```

- Dans cet exemple, la vue du fragment est décrite dans le fichier XML `res/layout/fragment.xml`, « déployée » (*inflated*) dans le fragment puis retournée par la méthode.

- Quand le bouton 1 est cliqué, le fragment 1 est créé et ajouté dynamiquement au layout principal, dans le `FrameLayout` prévu à cet effet.
- De la même manière, quand le bouton 2 est cliqué, le fragment 2 est créé puis affiché

La gestion dynamique des fragments passe par le manager de fragments. On récupère le manager en appelant dans l'activité la méthode `getSupportFragmentManager()` puis on effectue une transaction à partir du manager avec la classe `FragmentTransaction`. 
On peut ajouter une animation en utilisant la méthode `setTransition()`. À la fin de la transaction, on valide la transaction par un `commit`.

Pour ajouter un nouveau fragment au layout principal, on écrit donc :

```java
fragment = new MyFragment(); // MyFragment est une sous-classe de Fragment
FragmentManager manager = getSupportFragmentManager();
FragmentTransaction transaction = manager.beginTransaction();
transaction.replace(R.id.myLayout, fragment);
transaction.commit();
```

- Si on appuie sur le bouton "Back", l'activité est stoppée et on revient à l'écran d'accueil. Pour éviter ce comportement et faire en sorte que le bouton "Back" fasse revenir à l'ancien fragment, on ajoute `addToBackStack(String s)` à la transaction pour ajouter le fragment à la pile de navigation :

```java
fragment = new MyFragment(); // MyFragment est une sous-classe de Fragment
FragmentManager manager = getSupportFragmentManager();
FragmentTransaction transaction = manager.beginTransaction();
transaction.replace(R.id.myLayout, fragment).addToBackStack(null);
transaction.commit();
```

- Tester le nouveau comportement du bouton "Back".
  
- On veut maintenant envoyer un message du Fragment 1 vers le Fragment 2 et vice-versa : quand le bouton du Fragment 1 est cliqué, le texte de la zone d'édition est envoyé et affiché dans le Fragment 2 en passant par la MainActivity et vice-versa. Dans ce but, implémenter un *callback* du fragment vers l'activité. Le fragment notifie l'activité, qui récupère le texte passé en paramètre du callback et affiche l'autre fragment avec la zone de texte mise à jour.

