# Stage 1: Build the Angular application
FROM node:18.20.2 as build

MAINTAINER asmaabdallah518@gmail.com

# Définition du répertoire de travail
WORKDIR /app

# Copie de tout le code source de l'application
COPY . /app/

# Construction de l'application Angular
RUN npm install -g npm@10.6.0 @angular/cli && \
    ng build --prod

# Stage 2: Serve the Angular application using Nginx
FROM nginx:1.23.1-alpine

# Copie du fichier de configuration Nginx
COPY nginx.conf /etc/nginx/nginx.conf

# Copie des fichiers construits de l'étape précédente vers le répertoire d'hébergement de Nginx
COPY --from=build /app/dist/ /usr/share/nginx/html

# Exposition du port 80
EXPOSE 80

# Commande pour démarrer Nginx une fois le conteneur démarré
CMD ["nginx", "-g", "daemon off;"]

