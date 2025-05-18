# 🎓 Proyecto de Gestión de Estudiantes y Carreras con Spring Boot y JPA

Este proyecto es una aplicación en Java que utiliza **Spring Boot**, **JPA** y **Hibernate** para interactuar con una base de datos relacional. Gestiona entidades principales como **Estudiantes**, **Carreras** y sus relaciones en **Estudiante_Carrera**.

---

## 🧑‍💻 Grupo de Trabajo
Este proyecto fue desarrollado por el **Grupo N° 7**

---

## 🤝 Integrantes del Grupo
- **Bascuñan Jazmín**
- **Fernandez Mateo**

---

## ✅ Requisitos

1. **Java**: JDK 21 o superior.
2. **Maven**: Para gestionar las dependencias del proyecto.
3. **Base de Datos**: Una instancia de MySQL en ejecución.
4. **Postman**: Para probar los endpoints con la colección incluida.

---

## 📂 Estructura del Proyecto

El proyecto sigue una estructura estándar de Maven:

-   `src/main/java/com/example/integrador3/`: Código fuente principal.
    -   `controller/`: Controladores REST que manejan las solicitudes HTTP.
    -   `model/`: Entidades JPA que representan las tablas de la base de datos.
    -   `repository/`: Interfaces de Spring Data JPA para las operaciones de base de datos.
    -   `service/`: Lógica de negocio de la aplicación.
        -   `dto/`: Data Transfer Objects para las respuestas y solicitudes de la API.
    -   `utils/`: Clases de utilidad, como `CargaDeDatos.java`.
    -   `Integrador3Application.java`: Clase principal de Spring Boot.
-   `src/main/resources/`:
    -   `application.properties`: Archivo de configuración de la aplicación (conexión a BD, etc.).
    -   `csv/`: Archivos CSV (`estudiantes.csv`, `carreras.csv`, `estudianteCarrera.csv`) con datos iniciales.
-   `postman/`:
    -   `Integrador 3.postman_collection.json`: Colección de Postman para probar todos los endpoints.
-   `pom.xml`: Archivo de configuración de Maven (dependencias, plugins).

---

## ⚙️ Ejecución del Proyecto

### Configuración Inicial
1. Asegúrate de que tu servidor MySQL esté corriendo en `localhost`.
2. Actualiza las credenciales de conexión en el archivo `application.properties`:
    - Usuario: `root`
    - Contraseña: *(cadena vacía)*

3. Ejecuta el proyecto con Maven:
    ```bash
    mvn spring-boot:run
    ```

---

## 📖 Funcionalidades Principales
- **Gestión de Estudiantes y Carreras**:
    - Alta de estudiantes.
    - Matriculación de estudiantes en carreras.
- **Consultas Avanzadas**:
    - Estudiantes por género.
    - Estudiantes por LU.
    - Carreras con estudiantes inscriptos.
    - Reporte de inscriptos y egresados por año.
- **Manejo de Datos Iniciales**:
    - Carga de datos desde archivos CSV.
    - Creación dinámica de registros en la base de datos.

---

## 📂 Endpoints Principales

### **Estudiantes**

1. **Buscar estudiante por LU**:
    * **URL**: `GET /estudiantes/lu/{lu}`
    * **Ejemplo**: `http://localhost:8080/estudiantes/lu/13413`

2. **Consultar estudiantes por género**:
    * **URL**: `GET /estudiantes/genero/{genero}`
    * **Ejemplo**: `http://localhost:8080/estudiantes/genero/Male`

### **Carreras**

3. **Carreras con estudiantes ordenadas**:
    * **URL**: `GET /carreras/conEstudiantes`
    * **Ejemplo**: `http://localhost:8080/carreras/conEstudiantes`

4. **Reporte de carreras**:
    * **URL**: `GET /carreras/reporte`
    * **Ejemplo**: `http://localhost:8080/carreras/reporte`

---

## 📂 Ejemplo de Interacción

1. **Prueba con Postman**:
    - Importa el archivo `integrador3.postman_collection.json` en Postman
    - La colección incluye:
        - Todos los endpoints configurados y listos para usar
        - Ejemplos de requests predefinidos con los parámetros necesarios
        - Organización por categorías (Estudiantes, Carreras, Matriculaciones)
2. **Reporte de Carreras**:
    * Generar un listado de carreras con inscriptos y egresados por año, ordenado alfabética y cronológicamente.

---

## 🛠️ Tecnologías Utilizadas

* **Spring Boot**: Framework para el desarrollo de aplicaciones web.
* **JPA**: Para la persistencia de datos.
* **MySQL**: Base de datos relacional.
* **Maven**: Gestión de dependencias.
* **Commons CSV**: Procesamiento de archivos CSV.
* **Postman**: Pruebas de los endpoints REST.