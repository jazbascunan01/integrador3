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

---

## 📂 Estructura del Proyecto


### 1. **⚙️ Paquetes Principales**
- **`controller`**:
    - Contiene los controladores REST para manejar las solicitudes HTTP.
- **`csv`**:
    - Archivos CSV con datos iniciales: `estudiantes.csv`, `carreras.csv`, `estudianteCarrera.csv`.
- **`repository`**:
    - Interfaces para realizar consultas a la base de datos.
- **`service`**:
    - Lógica de negocio para las operaciones de estudiantes y carreras.

### 2. **📜 Archivos CSV**
- `estudiantes.csv`, `carreras.csv`, `estudianteCarrera.csv`:
    - Datos iniciales para rellenar las tablas de la base de datos.

---

## ⚙️ Ejecución del Proyecto

### Configuración Inicial
1. Asegúrate de que tu servidor MySQL esté corriendo en `localhost`.
2. Crea una base de datos llamada `integrador3`:
    ```sql
    CREATE DATABASE integrador3;
    ```
3. Actualiza las credenciales de conexión en el archivo `application.properties`:
    - Usuario: `root`
    - Contraseña: *(cadena vacía)*

4. Ejecuta el proyecto con Maven:
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

## 📂 Ejemplo de Interacción
1. **Endpoints Principales**:
    - **Buscar estudiante por LU**:
        - URL: `GET /estudiantes/lu/{lu}`
        - Ejemplo: `http://localhost:8080/estudiantes/lu/13413`
    - **Consultar estudiantes por género**:
        - URL: `GET /estudiantes/genero/{genero}`
        - Ejemplo: `http://localhost:8080/estudiantes/genero/Male`
    - **Carreras con estudiantes ordenadas**:
        - URL: `GET /carreras/conEstudiantes`
        - Ejemplo: `http://localhost:8080/carreras/conEstudiantes`

2. **Reporte de Carreras**:
    - Generar un listado de carreras con inscriptos y egresados por año, ordenado alfabética y cronológicamente.

---

## 🛠️ Tecnologías Utilizadas
- **Spring Boot**: Framework para el desarrollo de aplicaciones web.
- **JPA**: Para la persistencia de datos.
- **MySQL**: Base de datos relacional.
- **Maven**: Gestión de dependencias.
- **Commons CSV**: Procesamiento de archivos CSV.
