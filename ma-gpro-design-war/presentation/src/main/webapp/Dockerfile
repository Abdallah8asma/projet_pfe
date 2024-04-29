# Stage 1

FROM node:12.18.1 as build-step
MAINTAINER asmaabdallah518@gmail.com
RUN mkdir -p /app
WORKDIR /app
COPY . /app
RUN npm install
COPY . /app
RUN npm run build --production

# Stage 2

FROM nginx:1.23.1-alpine
COPY --from=build-step /app/dist/commercial-industriel/ /usr/share/nginx/html

