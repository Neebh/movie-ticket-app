unset SPRING_DATASOURCE_URL
unset SPRING_JPA_HIBERNATE_DDL_AUTO
mvn clean install
source .env
docker-compose up --build --force-recreate
