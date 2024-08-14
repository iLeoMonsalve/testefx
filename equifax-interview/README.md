# testefx
# Equifax Interview Project

Este proyecto es una aplicación basada en Java y Maven. El propósito de este proyecto es demostrar habilidades técnicas para la entrevista en Equifax. A continuación se detallan las instrucciones para configurar y ejecutar el proyecto, así como para crear las tablas necesarias en la base de datos junto con un usuario de prueba.

## Requisitos previos

- Java 11 o superior
- Maven 3.6.3 o superior
- Docker y Docker Compose (opcional para ejecutar la aplicación en contenedores)

## Estructura del proyecto

- `Dockerfile`: Define la configuración del contenedor Docker para la aplicación.
- `docker-compose.yml`: Define la configuración de los servicios Docker, como la base de datos y la aplicación.
- `pom.xml`: Archivo de configuración de Maven.
- `src/`: Contiene el código fuente de la aplicación.
- `target/`: Directorio donde se generan los archivos compilados.

## Instrucciones de configuración

### Paso 1: Clonar el repositorio

```bash
git clone https://github.com/iLeoMonsalve/testefx.git
cd equifax-interview
mvn clean install
mvn spring-boot:run
docker-compose up --build
```

## DDL
```sql
-- Crear la tabla de ejemplo
CREATE TABLE user (
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
password VARCHAR(50)
);

-- Crear un usuario de prueba
CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';

-- Conceder permisos al usuario de prueba
GRANT ALL PRIVILEGES ON ejemplo.* TO 'test'@'localhost';

```


