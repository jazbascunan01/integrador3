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

# 🚀 API Endpoints

## **Estudiantes**

| Método | Endpoint                                                         | Descripción                          | Parámetros                                          |
|--------|------------------------------------------------------------------|--------------------------------------|-----------------------------------------------------|
| `POST` | `/estudiantes`                                                   | Registrar un nuevo estudiante        | `Estudiante` (JSON en body)                         |
| `POST` | `/estudiantes/matricular`                                        | Matricular estudiante en carrera     | `EstudianteCarreraRequestDTO` (JSON en body)        |
| `GET`  | `/estudiantes`                                                   | Obtener todos los estudiantes        | Opcionales: `sortBy` (campo), `sortDir` (ASC/DESC)  |
| `GET`  | `/estudiantes/lu`                                                | Obtener estudiantes ordenados por LU | -                                                   |
| `GET`  | `/estudiantes/lu/{LU}`                                           | Buscar estudiante por número de LU   | Path: `LU` (entero)                                 |
| `GET`  | `/estudiantes/genero/{genero}`                                   | Filtrar estudiantes por género       | Path: `genero` (String)                             |
| `GET`  | `/estudiantes/carrera/{nombreCarrera}/ciudad/{ciudadResidencia}` | Estudiantes por carrera y ciudad     | Path: `nombreCarrera`, `ciudadResidencia` (Strings) |
| `GET`  | `/estudiantes/{id}`                                              | Buscar estudiante por ID             | Path: `id` (entero)                                 |

## **Carreras**

| Método | Endpoint                   | Descripción                                          |
|--------|----------------------------|------------------------------------------------------|
| `GET`  | `/carreras/conEstudiantes` | Carreras con cantidad de estudiantes (ordenadas)     |
| `GET`  | `/carreras/reporte`        | Reporte de carreras con inscriptos/egresados por año |

---

## 📌 Ejemplos de Uso

### **1. Registrar Estudiante**
```json
{
    "dni": 40123456,
    "LU": 12345,
    "nombre": "Juan",
    "apellido": "Pérez",
    "edad": 22,
    "genero": "Male",
    "ciudad": "Buenos Aires"
}
```

### 2. Matricular Estudiante en Carrera
```json
{
    "idEstudiante": 1,
    "idCarrera": 3,
    "fechaInscripcion": "2020-05-15",
    "fechaGraduacion": null
}
```
### 3. Obtener Estudiantes
```http
GET /estudiantes?sortBy=apellido&sortDir=DESC
```

### 4. Buscar Estudiante por LU
```http
GET /estudiantes/lu/13413
```

### 5. Carreras con estudiantes
```http
GET /carreras/conEstudiantes
```

### 6. Reporte de Carreras
```http
GET /carreras/reporte
```
---
## 🔄 Consultas con Ordenamiento Dinámico

### **Estudiantes con Ordenamiento Personalizado**

El endpoint `/estudiantes` soporta parámetros para ordenamiento dinámico:

| Parámetro | Tipo   | Requerido | Valores                                    | Descripción                                         |
|-----------|--------|-----------|--------------------------------------------|-----------------------------------------------------|
| `sortBy`  | String | No        | LU, nombre, apellido, edad, genero, ciudad | Campo por el cual ordenar los resultados            |
| `sortDir` | String | No        | ASC, DESC                                  | Dirección del ordenamiento (ascendente/descendente) |

**Comportamiento:**
- Si no se especifica `sortBy`: Se devuelve el orden natural de la base de datos
- Si solo se especifica `sortBy`: Orden ascendente por defecto
- Si se especifican ambos: Ordenamiento personalizado completo

**Ejemplos:**

1. Ordenar por apellido descendente:
```http
GET /estudiantes?sortBy=apellido&sortDir=DESC
```
---
## 🔍 Consultas Avanzadas

### 1. Estudiantes por género
```http
GET /estudiantes/genero/Female
```
### 2. Estudiantes por carrera y ciudad
```http
GET /estudiantes/carrera/TUDAI/ciudad/Rauch
```
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

## 🛠️ Manejo de Errores

La API devuelve respuestas estructuradas con códigos HTTP apropiados:

- `200 OK` para operaciones exitosas
- `400 Bad Request` para parámetros inválidos
- `404 Not Found` cuando no se encuentra un recurso
- `409 Conflict` para duplicados o reglas de negocio violadas

**Ejemplo de error:**
```json
{
    "error": "El estudiante ya está matriculado en esta carrera"
}
```
---

## 🛠️ Tecnologías Utilizadas

* **Spring Boot**: Framework para el desarrollo de aplicaciones web.
* **JPA**: Para la persistencia de datos.
* **MySQL**: Base de datos relacional.
* **Maven**: Gestión de dependencias.
* **Commons CSV**: Procesamiento de archivos CSV.
* **Postman**: Pruebas de los endpoints REST.