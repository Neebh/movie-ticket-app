FROM eclipse-temurin:21
EXPOSE 81
RUN mkdir /opt/app
COPY target/*.jar /opt/app/app.jar
RUN ls /opt/app/
CMD ["java" , "-jar", "/opt/app/app.jar"]