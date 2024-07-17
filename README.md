# VargasAPI

## Descripción
**VargasAPI** es una aplicación RESTful diseñada para gestionar tópicos y usuarios en un sistema de foros educativos. Permite a los usuarios registrar tópicos, agregar mensajes y consultar información relevante sobre cursos y usuarios.

## Tecnologías Usadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Hibernate**
- **Spring Security**
- **Lombok**
- **Swagger/OpenAPI**
- **JUnit** (para pruebas)

## Estructura del Proyecto
/src
/main
/java
/foro
/project
/VargasAPI
/controller
/controller/dto
/model
/repository
/service
/resources
/application.properties

markdown
Copiar código

## Requisitos Previos
- **JDK 17 o superior**
- **MySQL Server**
- **Maven**

## Configuración del Proyecto
Clona el repositorio:
```bash
git clone <https://github.com/CarmenVar/foro_hub.git>
cd VargasAPI
Configura tu base de datos en application.properties:

properties:
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Crea la base de datos:
Asegúrate de que la base de datos forohub exista en tu servidor MySQL.

Endpoints Principales
Usuarios
Registrar Usuario

POST /usuarios/registro
Request Body: DatosNuevoUsuario
Response: Usuario creado.
Tópicos
Registrar Tópico

POST /topicos/registro
Request Body: DatosRegistroTopico
Response: Tópico creado.
Listar Tópicos

GET /topicos
Response: Lista de DatosListadoTopico.
Buscar Tópicos por Curso

GET /topicos/curso/{nombreCurso}
Response: Lista de DatosListadoTopico.
Ejecución de la Aplicación
Para iniciar la aplicación, ejecuta el siguiente comando en la raíz del proyecto:

./mvnw spring-boot:run

Contribuciones
Las contribuciones son bienvenidas. Si deseas contribuir, por favor sigue los siguientes pasos:

Haz un fork del repositorio.
Crea una nueva rama para tu feature (git checkout -b feature/nueva-feature).
Realiza tus cambios y haz commit (git commit -m 'Agrega nueva feature').
Haz push a la rama (git push origin feature/nueva-feature).
Crea un nuevo Pull Request.
Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Si tienes alguna pregunta o necesitas ayuda, no dudes en abrir un issue en el repositorio.