# projet_pfe
# Projet PFE - Application Multi-Services (Spring Boot & Angular)

Ce projet est une application web multi-services développée dans le cadre d'un projet de fin d'études (PFE). Il utilise **Spring Boot** pour les services backend et **Angular** pour les services frontend. Le projet est structuré en plusieurs branches pour gérer les différents aspects du déploiement et de l'intégration continue.

## Architecture de l'application

L'application est composée de **4 services** :

### Services Frontend (Angular) :
1. **`ma-gpro-atelier-war`** :
   - Service frontend dédié à la gestion des ateliers.
   - Interface utilisateur pour interagir avec les fonctionnalités liées aux ateliers.

2. **`ma-gpro-design-war`** :
   - Service frontend dédié à la gestion des designs.
   - Interface utilisateur pour interagir avec les fonctionnalités liées aux designs.

### Services Backend (Spring Boot) :
1. **`ma-gpro-logistique`** :
   - Service backend dédié à la gestion de la logistique.
   - Expose des API REST pour la gestion des données logistiques.

2. **`mt-gpro-commun`** :
   - Service backend commun partagé entre les autres services.
   - Contient des fonctionnalités transversales (par exemple, l'authentification, la gestion des utilisateurs, etc.).

## Structure du projet

Le projet est organisé en plusieurs branches pour faciliter le déploiement et l'intégration continue :

1. **`main` (ou `default`)** :
   - Cette branche contient le code source principal de l'application.
   - Elle est utilisée pour le développement et les tests locaux.

2. **`deploy-docker`** :
   - Cette branche contient la configuration pour déployer l'application en utilisant **Docker**.
   - Elle inclut un `Dockerfile` pour chaque service (frontend et backend) ainsi que les instructions pour construire et exécuter les conteneurs.

3. **`deploy-docker-compose`** :
   - Cette branche contient la configuration pour déployer l'application en utilisant **Docker Compose**.
   - Elle inclut un fichier `docker-compose.yml` qui permet de lancer l'application avec une seule commande, en gérant les dépendances entre les services.

4. **`deploy-k8s`** :
   - Cette branche contient la configuration pour déployer l'application sur un cluster **Kubernetes (k8s)**.
   - Elle inclut des fichiers de configuration YAML pour les déploiements, services, et autres ressources Kubernetes nécessaires.

5. **`nexus_sonarqube`** :
   - Cette branche regroupe la configuration pour **Nexus** (stockage des artefacts) et **SonarQube** (analyse de code).
   - Elle inclut :
     - Les scripts pour pousser les artefacts (fichiers JAR pour Spring Boot et builds Angular) vers un dépôt Nexus.
     - La configuration pour intégrer SonarQube dans le pipeline CI/CD et analyser la qualité du code.

## Prérequis

Avant de pouvoir exécuter ce projet, assurez-vous d'avoir les outils suivants installés sur votre machine :

- **Java JDK** (pour Spring Boot)
- **Node.js** et **npm** (pour Angular)
- **Docker** (pour les déploiements en conteneurs)
- **Docker Compose** (pour les déploiements multi-conteneurs)
- **Kubectl** (pour les déploiements Kubernetes)
- **Nexus** (pour le stockage des artefacts)
- **SonarQube** (pour l'analyse de code)
