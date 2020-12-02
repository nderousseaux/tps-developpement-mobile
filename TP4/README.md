# TP4

On va étendre l'application développée au TP3.
On souhaite rajouter les fonctionnalités suivantes :

* les résultats issues de la recherche s'affichent dans une liste déroulante
* la sélection d'un résultat dans la liste permet de remplacer le fond d'écran du téléphone par l'image correspondante

## Contraintes techniques :
* L'application sera composée d'une activité principale et d'un fragment pour l'affiche de la liste de résultats.
* La vue de l'activité sera composée d'un champ d'édition de texte, d'un bouton et d'un fragment agencés dans un LinearLayout.
* Le fragment affichera une `RecyclerView`. Cette vue, scrollable, affichera pour chaque résultat de la requête : la description, le nom de l'auteur ainsi que la photo.
* Le click sur un élément de la liste laissera le choix à l'utilisateur d'utiliser cette image comme fond d'écran sur son appareil.

## Étapes :

* Créer l'activité et le layout correspondant.
* Créer le fragment et le layout correspondant (vous pourrez utiliser le template `FragmentList` d'Android Studio) (documentation sur les `RecyclerView`: [https://developer.android.com/guide/topics/ui/layout/recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview).
* Rendre la liste "cliquable"
* Mise à jour du fond d'écran :  [WallPaperManager](https://developer.android.com/reference/android/app/WallpaperManager).
  