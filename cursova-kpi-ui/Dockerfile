#STAGE 1
FROM node:16.15-alpine AS build
ENV NODE_ENV=production
WORKDIR /usr/src/app
COPY package.json .
RUN npm install
COPY . .
RUN npm install -g @angular/cli
RUN npm run build

#STAGE 2
FROM nginx:1.22-alpine
ENV APP_VER=${APP_VER:-0.1} \
    NGINX_PORT=${NGINX_PORT:-80}
LABEL version=${APP_VER}
RUN apk update && apk upgrade curl libxml2 && sync
COPY --from=build /usr/src/app/dist/cursova-kpi-ui /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE ${NGINX_PORT}

CMD ["nginx", "-g", "daemon off;"]
