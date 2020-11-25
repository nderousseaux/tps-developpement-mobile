# TP3 : AsyncTask, Volley

## AsyncTask

https://developer.android.com/reference/android/os/AsyncTask.html

Classe générique permettant la gestion d'une tâche asynchrone, du traitement en tâche de fond à la mise à jour de l'interface utilisateur.

### Exercices

1. Écrire une application Android qui effectue une requête sur l'API Unsplash https://unsplash.com/developers à partir d'une recherche saisie par l'utilisateur dans un champ `EditText`. La réponse au format JSon de la requête sera affichée de manière non-structurée dans un `TextView` sur la vue principale de l'application.

2. Étendre l'application précédente de manière à traiter la réponse JSon. Afficher :
	* le nombre total d'items récupérés par la requête
	* le nombre total de pages de la réponse
	* pour le premier item seulement : la description de la photo, le nom de l'auteur et la photo.

## Librairie Volley

https://developer.android.com/training/volley/

Librairie dédiée aux requêtes asynchrones de type HTTP.

### Exercice

Réécrire l'application précédente en utilisant la librairie Volley.
