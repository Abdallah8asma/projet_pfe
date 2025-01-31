# Branche `deploy_docker` - Déploiement avec Docker

Cette branche est dédiée au déploiement de l'application en utilisant **Docker**. Elle contient les configurations nécessaires pour construire des images Docker, exécuter des conteneurs, et pousser les images vers DockerHub. Un pipeline Jenkins est également configuré pour automatiser ce processus.

---

## Description du Pipeline Jenkins

Le pipeline Jenkins est divisé en plusieurs étapes pour automatiser le déploiement avec Docker. Voici une description détaillée de chaque étape :

### 1. **Clone du dépôt**
   - Cette étape clone le dépôt Git à partir de la branche `deploy_docker`.
   - **Commande utilisée** :
     ```bash
     git branch: 'deploy_docker', credentialsId: 'Gitlab', url: 'git@github.com:Abdallah8asma/projet_pfe.git'
     ```

### 2. **Build des projets**
   - Cette étape compile et construit tous les projets (backend et frontend) en utilisant Maven.
   - **Projets concernés** :
     - `socle`
     - `socle-j2ee`
     - `socle-j2ee-tiers`
     - `scole-j2ee-mt`
     - `mt-socle`
     - `mt-commun`
     - `mt-gpro-commun`
     - `ma-gpro-logistique`
     - `ma-gpro-design-war`
     - `ma-gpro-atelier-war`
   - **Commande utilisée** :
     ```bash
     mvn clean install
     ```

### 3. **Notification Slack**
   - Cette étape envoie une notification sur Slack pour informer de l'état du pipeline (succès ou échec).
   - **Informations envoyées** :
     - Statut du pipeline
     - Nom du job
     - Numéro du build
     - URL du build
   - **Commande utilisée** :
     ```groovy
     slackSend(channel: '#devops', message: "Pipeline Status: ${buildStatus}\nJob Name: ${env.JOB_NAME}\nBuild Number: ${env.BUILD_NUMBER}\nBuild URL: ${env.BUILD_URL}")
     ```

### 4. **Nettoyage des conteneurs et images Docker**
   - Cette étape arrête et supprime tous les conteneurs et images Docker existants pour éviter les conflits.
   - **Commandes utilisées** :
     ```bash
     docker stop $(docker ps -aq) || true
     docker rm $(docker ps -aq) || true
     docker rmi -f $(docker image ls -q) || true
     ```

### 5. **Construction des images Docker**
   - Cette étape construit les images Docker pour chaque service :
     - **Frontend** : `asmaabdallah518329/front`
     - **Backend Logistique** : `asmaabdallah518329/ma-gpro-logistique-rest`
     - **Backend Commun** : `asmaabdallah518329/mt-gpro-commun-rest`
     - **Base de données** : `asmaabdallah518329/data`
   - **Commandes utilisées** :
     ```bash
     docker build -t asmaabdallah518329/front -f dockerfile-front .
     docker build -t asmaabdallah518329/ma-gpro-logistique-rest -f dockerfile-logistique .
     docker build -t asmaabdallah518329/mt-gpro-commun-rest -f dockerfile-commun .
     docker build -t asmaabdallah518329/data -f dockerfile-data .
     ```

### 6. **Exécution des conteneurs**
   - Cette étape exécute les conteneurs à partir des images Docker construites.
   - **Conteneurs lancés** :
     - **Frontend** : `frontc`
     - **Backend Logistique** : `backl`
     - **Backend Commun** : `backc`
     - **Base de données** : `datac` (avec un volume nommé `pgdata`)
   - **Commandes utilisées** :
     ```bash
     docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT
     docker run -d --name backl $DOCKER_IMAGE_NAME_BACK_LOGISTIQUE
     docker run -d --name backc $DOCKER_IMAGE_NAME_BACK_COMMUN
     docker volume create --name pgdata
     docker run -d -v pgdata:/pgdata --name datac $DOCKER_IMAGE_NAME_DATA
     ```

### 7. **Push des images vers DockerHub**
   - Cette étape tagge et pousse les images Docker vers DockerHub.
   - **Images poussées** :
     - `asmaabdallah518329/front:latest`
     - `asmaabdallah518329/ma-gpro-logistique-rest:latest`
     - `asmaabdallah518329/mt-gpro-commun-rest:latest`
     - `asmaabdallah518329/data:latest`
   - **Commandes utilisées** :
     ```bash
     docker tag asmaabdallah518329/front asmaabdallah518329/front:latest
     docker push asmaabdallah518329/front:latest
     docker tag asmaabdallah518329/ma-gpro-logistique-rest asmaabdallah518329/ma-gpro-logistique-rest:latest
     docker push asmaabdallah518329/ma-gpro-logistique-rest:latest
     docker tag asmaabdallah518329/mt-gpro-commun-rest asmaabdallah518329/mt-gpro-commun-rest:latest
     docker push asmaabdallah518329/mt-gpro-commun-rest:latest
     docker tag asmaabdallah518329/data asmaabdallah518329/data:latest
     docker push asmaabdallah518329/data:latest
     ```

---

## Structure de la branche

La branche `deploy_docker` contient les fichiers suivants :

1. **Dockerfiles** :
   - `dockerfile-front` : Pour construire l'image Docker du frontend.
   - `dockerfile-logistique` : Pour construire l'image Docker du backend logistique.
   - `dockerfile-commun` : Pour construire l'image Docker du backend commun.
   - `dockerfile-data` : Pour construire l'image Docker de la base de données.

2. **Jenkinsfile** :
   - Le fichier `Jenkinsfile` contient le pipeline Jenkins décrit ci-dessus.

3. **Code source** :
   - Le code source des services frontend et backend est inclus dans cette branche.

---

## Prérequis

Pour exécuter ce pipeline, assurez-vous d'avoir les outils suivants installés :

- **Docker** : Pour construire et exécuter les conteneurs.
- **Jenkins** : Pour exécuter le pipeline.
- **Maven** : Pour construire les projets Java.
- **Slack** : Pour recevoir les notifications (optionnel).
- **DockerHub** : Pour pousser les images Docker.

---

## Exécution du Pipeline

1. **Configurer Jenkins** :
   - Assurez-vous que Jenkins est configuré avec les plugins nécessaires (Docker, Git, Slack, etc.).
   - Ajoutez les identifiants DockerHub et Git dans Jenkins.

2. **Lancer le pipeline** :
   - Créez un nouveau job Jenkins de type "Pipeline".
   - Spécifiez le chemin vers le `Jenkinsfile` dans la branche `deploy_docker`.
   - Exécutez le job.

---

## Auteurs

- Asma(https://github.com/votre-utilisateur)

---

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.
