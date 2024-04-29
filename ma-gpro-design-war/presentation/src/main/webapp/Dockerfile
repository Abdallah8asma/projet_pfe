# Stage 1: Build the AngularJS application
FROM node:18.20.2 as build

MAINTAINER asmaabdallah518@gmail.com

# Définition du répertoire de travail
WORKDIR /app

# Copie de tout le code source de l'application
COPY . /app/

# Installation des dépendances
RUN npm install

# Construction de l'application AngularJS (remplacez cette étape par vos propres commandes de construction AngularJS)
RUN npm run build

# Stage 2: Serve the AngularJS application using Nginx
FROM nginx:1.23.1-alpine

# Copie du fichier de configuration Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copie des fichiers construits de l'étape précédente vers le répertoire d'hébergement de Nginx
COPY --from=build /app/dist/ /usr/share/nginx/html

# Exposition du port 80
EXPOSE 80

# Commande pour démarrer Nginx une fois le conteneur démarré
CMD ["nginx", "-g", "daemon off;"]
