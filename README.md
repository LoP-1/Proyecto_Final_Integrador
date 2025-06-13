# 📚 Proyecto Final Integrador - Biblioteca Municipal

Bienvenido al proyecto de **Biblioteca Municipal**, una aplicación web full stack que permite la gestión de libros, reservas y administración de entidades relacionadas. Este sistema está diseñado para cubrir tanto la experiencia de usuario (préstamos y devoluciones de libros) como la administración de los recursos bibliográficos.

---

## Tabla de Contenidos

- [Características principales](#características-principales)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Funcionalidades Backend](#funcionalidades-backend)
- [Funcionalidades Frontend](#funcionalidades-frontend)
- [Instalación y ejecución](#instalación-y-ejecución)
- [API endpoints principales](#api-endpoints-principales)
- [Notas y consideraciones](#notas-y-consideraciones)

---

## Características principales

- Gestión de usuarios y autenticación básica.
- Préstamo y devolución de libros.
- Listado y búsqueda de libros disponibles.
- CRUD completo de libros (alta, baja, modificación).
- Gestión de autores, editoriales y categorías (auxiliares).
- Visualización de reservas activas y pasadas por usuario.
- Control de disponibilidad y estado de cada libro.
- Interfaz de usuario moderna y responsiva.

---

## Tecnologías utilizadas

### Backend
- **Java 21+**
- **Spring Boot** (REST API)
- **JPA / Hibernate** (ORM)
- **MySQL** (Base de datos relacional)
- **Maven** (Gestión de dependencias y build)

### Frontend
- **HTML5 / CSS3 / JavaScript**
- **Fetch API** (para comunicación con backend)
- **Diseño responsive** (CSS puro, sin frameworks)

---

## Estructura del proyecto

```
biblioteca-municipal/
│
├── backend/
│   ├── src/main/java/com/biblioteca/BibliotecaMunicipal/
│   │   ├── controller/
│   │   │   ├── LibroController.java
│   │   │   └── ...
│   │   ├── service/
│   │   ├── model/
│   │   └── ...
│   └── ...
│
├── frontend/
│   ├── index.html         # Vista principal de libros y reservas
│   ├── reservas.html      # Reserva y devolución de libros
│   ├── auth.html          # Login (si aplica)
│   ├── crud-libros.html   # CRUD de libros y auxiliares (admin)
│   └── ...
│
├── README.md
└── ...
```

---

## Funcionalidades Backend

### Libros
- **Listar libros:** Filtrado por título, categoría o autor (GET `/api/libros`)
- **Detalle de libro:** Información completa de un libro (GET `/api/libros/{id}`)
- **Crear libro:** Alta de nuevos libros (POST `/api/libros`)
- **Actualizar libro:** Edición de libros existentes (PUT `/api/libros/{id}`)
- **Eliminar libro:** Baja lógica/física, con manejo de restricciones (DELETE `/api/libros/{id}`)
- **Disponibilidad:** Consulta si el libro está disponible (GET `/api/libros/{id}/disponibilidad`)

### Reservas
- **Reservar libro:** Crear una reserva para uno o varios libros (POST `/api/reservas`)
- **Listar reservas de usuario:** Todas las reservas de un usuario (GET `/api/reservas/usuario/{usuarioId}`)
- **Devolver libro:** Marcar una reserva como devuelta (PUT `/api/reservas/{id}/devolver`)
- **Separación entre reservas activas y pasadas** según estado y fechas.

### Auxiliares (Autores, Editoriales, Categorías)
- **CRUD de autores:** GET / POST / PUT / DELETE `/api/autores`
- **CRUD de editoriales:** GET / POST / PUT / DELETE `/api/editorial`
- **CRUD de categorías:** GET / POST / PUT / DELETE `/api/Categorias`

### Seguridad
- Autenticación básica por usuario (puede ser por session/localStorage en frontend)
- Validaciones y control de errores para evitar operaciones inválidas (por ejemplo, eliminar libros con reservas activas)

---

## Funcionalidades Frontend

### index.html
- Muestra el listado de libros disponibles y su estado.
- Permite reservar libros (abre un modal para seleccionar fecha de devolución).
- Botón para ver “Mis Reservas” y gestionar devoluciones.
- Botón de cierre de sesión.

### reservas.html
- Lista todas las reservas del usuario logueado.
- Separación visual entre “Reservas Pendientes” y “Reservas Antiguas”.
- Botón de “Devolver” para reservas activas.
- Mensajes claros si no hay reservas en alguna sección.

### crud-libros.html
- **CRUD completo de libros** (alta, baja, modificación y listado).
- Selects para autores, categorías y editoriales que se obtienen de la base de datos en tiempo real.
- Formulario adicional para agregar nuevos autores, editoriales y categorías directamente desde la interfaz.
- Tabla con todos los libros y acciones de editar/eliminar.

---

## Instalación y ejecución

### Backend

1. Clona el repositorio:
   ```bash
   git clone https://github.com/LoP-1/Proyecto_Final_Integrador.git
   cd Proyecto_Final_Integrador/backend
   ```
2. Configura tu base de datos MySQL y el archivo `application.properties` con tus credenciales.
3. Construye y ejecuta el proyecto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Por defecto, el backend corre en `http://localhost:8080/`.

### Frontend

1. Entra a la carpeta `frontend/`.
2. Abre los archivos `index.html`, `reservas.html` o `crud-libros.html` directamente en tu navegador.
3. Asegúrate que el backend esté corriendo en el mismo host/puerto o ajusta las URLs de los fetch si es necesario.

---

## API endpoints principales

> **Libros**
> - `GET    /api/libros`
> - `GET    /api/libros/{id}`
> - `POST   /api/libros`
> - `PUT    /api/libros/{id}`
> - `DELETE /api/libros/{id}`

> **Reservas**
> - `POST   /api/reservas`
> - `GET    /api/reservas/usuario/{usuarioId}`
> - `PUT    /api/reservas/{id}/devolver`

> **Autores / Editoriales / Categorías**
> - `GET/POST/PUT/DELETE /api/autores`
> - `GET/POST/PUT/DELETE /api/editorial`
> - `GET/POST/PUT/DELETE /api/Categorias`

---

## Notas y consideraciones

- **Restricciones de integridad:** Eliminar un libro con reservas relacionadas puede lanzar errores de integridad referencial. 
  - Puedes optar por borrado en cascada o bloquear el borrado si hay reservas (ajustable en backend).
- **Validaciones:** El frontend y backend validan campos obligatorios y muestran mensajes claros de éxito o error.
- **Escalabilidad:** El diseño permite agregar funcionalidades de administración, autenticación avanzada o reportes en el futuro.
- **Customización:** Todos los estilos del frontend son fácilmente personalizables en CSS.

---
