# Stage 1
FROM node:12.18.1 as build-step
MAINTAINER asmaabdallah518@gmail.com

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json separately
COPY package*.json /app/

# Install dependencies
RUN npm install

# Copy the rest of the application
COPY . /app/

# Build the application
RUN npm run build --production

# Stage 2
FROM nginx:1.23.1-alpine

# Copy built files from Stage 1
COPY --from=build-step /app/dist/commercial-industriel/ /usr/share/nginx/html


