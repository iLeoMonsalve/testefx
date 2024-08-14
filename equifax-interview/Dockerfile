# Utiliza una imagen base de OpenJDK
FROM openjdk:17-jdk

# Añade un argumento para especificar la versión de la aplicación
ARG JAR_FILE=target/*.jar

# Copia el archivo JAR a la imagen Docker
COPY ${JAR_FILE} app.jar

# Configura variables de entorno para la base de datos
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/efx
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=pass123
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=create
ENV SPRING_JPA_SHOW_SQL=true

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
