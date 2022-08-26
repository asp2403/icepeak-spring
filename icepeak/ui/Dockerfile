FROM bitnami/git:latest as download
ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
RUN cd $PROJECT_DIR
RUN git clone https://github.com/asp2403/icepeak-angular.git


FROM node:current-alpine3.15 AS build
ENV PROJECT_DIR=/opt/project/icepeak-angular
RUN mkdir -p $PROJECT_DIR
COPY --from=download $PROJECT_DIR $PROJECT_DIR
RUN cd $PROJECT_DIR
WORKDIR $PROJECT_DIR
RUN npm install
RUN npm run build



FROM nginx:1.17.1-alpine
ENV PROJECT_DIR=/opt/project/icepeak-angular
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build  $PROJECT_DIR/dist/icepeak-angular /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]