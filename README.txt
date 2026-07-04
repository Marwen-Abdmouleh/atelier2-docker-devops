Atelier 2 - Mini-projet DevOps (Docker & Docker Hub)
=====================================================

Structure du projet :

projet_final/
├── backend/                          -> Application Spring Boot
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/main/java/com/iset/devops/
│       ├── DevopsBackendApplication.java
│       └── controller/HelloController.java
│   └── src/main/resources/application.properties
├── frontend/                         -> Page statique servie par Nginx
│   ├── Dockerfile
│   └── index.html
├── docker-compose.yml                -> Orchestration des 3 services
└── .env                              -> Variables d'environnement

Pour tout lancer une fois Docker installe :
    cd projet_final
    docker-compose up -d --build
    docker ps

Backend accessible sur : http://localhost:8080/api/hello
Frontend accessible sur : http://localhost:80
