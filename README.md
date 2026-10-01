```markdown
# 📚 Biblioteca Multimedia

**Aplicación de consola en Java para la gestión integral de una biblioteca multimedia con usuarios, recursos y préstamos.**

*Los datos se almacenan de forma permanente en archivos CSV, conservando el estado tras cerrar la aplicación.*

---

## 📌 Información del Proyecto

* **Asignatura:** Acceso a Datos (DAM2, curso 2026-2027)
* **Práctica:** Práctica 1 — Git y repaso de Programación

### 👥 Integrantes del Grupo
* **Josué Mateo**
* **Julen Ullibarri**
* **Ander Gago**
* **Erlantz Amigo**

---

## ✨ Funcionalidades

### 📦 Gestión de Recursos
* **Variedad de tipos:** Creación de Libros, Películas y Videojuegos.
* **Operaciones CRUD:** Listar, buscar por ID, modificar y eliminar recursos.
* **Estado en tiempo real:** Consulta inmediata de disponibilidad (*Disponible* / *Prestado*).

### 👤 Gestión de Usuarios
* **Operaciones CRUD:** Crear, listar, buscar por ID, modificar y eliminar usuarios.
* **IDs automáticos:** Generación automática de identificadores para garantizar que no se dupliquen.

### 🔄 Préstamos y Devoluciones
* **Validación previa:** Comprobación de existencia del usuario y del recurso antes de operar.
* **Control de disponibilidad:** Bloqueo de préstamos para recursos que ya están prestados.
* **Flujo dinámico:** Cambio automático de estado (`Disponible` ↔ `Prestado`).
* **Trazabilidad:** Registro de fecha de préstamo, estado y fecha de devolución.

### 🔍 Consultas y Búsquedas
* Búsqueda de recursos por título (coincidencias exactas o parciales).
* Consulta de recursos actualmente disponibles.
* Lista de préstamos activos en el sistema.
* Histórico y préstamos activos de un usuario específico.
* Filtrado de recursos agrupados por tipo (Libros, Películas, Videojuegos).
* **Consultas adicionales del grupo:**
  * Búsqueda de recursos por rango de años.
  * Búsqueda de libros por autor.

### 💾 Persistencia y Control de Errores
* **Persistencia CSV:** Carga automática al iniciar y guardado tras cada modificación/eliminación (`usuarios.csv`, `recursos.csv`, `prestamos.csv`).
* **Robustez y Validaciones:**
  * Control de opciones de menú incorrectas y captura de entradas no numéricas.
  * Validación de rangos (años mínimos: Libros ≥ 1500, Películas ≥ 1895, Juegos ≥ 1950; PEGI 0-18).
  * Control de usuarios/recursos inexistentes e imposibilidad de realizar préstamos o devoluciones inválidas.
  * Manejo de ficheros inexistentes o con líneas corruptas.

---

## 🗂️ Estructura del Proyecto

```text
TrabajoAccesoDatos/
├── src/
│   ├── model/
│   │   ├── Recurso.java         (Clase abstracta)
│   │   ├── Libro.java
│   │   ├── Pelicula.java
│   │   ├── Videojuego.java
│   │   ├── Usuario.java
│   │   └── Prestamo.java
│   ├── service/
│   │   ├── RecursoService.java  (Lógica de recursos)
│   │   ├── GestionUsuarios.java (Lógica de usuarios)
│   │   ├── GestorPrestamos.java (Lógica de préstamos y devoluciones)
│   │   ├── FicheroRecursos.java (Lectura/escritura de recursos.csv)
│   │   ├── FicheroUsuarios.java (Lectura/escritura de usuarios.csv)
│   │   └── FicheroPrestamos.java(Lectura/escritura de prestamos.csv)
│   └── view/
│       └── Main.java            (Menús interactivos y punto de entrada)
├── recursos.csv
├── usuarios.csv
├── prestamos.csv
├── .gitignore
└── README.md
```

### 🧩 Descripción de Componentes
* `model`: Clases de datos. `Recurso` es una clase abstracta de la que heredan `Libro` (autor, páginas), `Pelicula` (director, duración) y `Videojuego` (plataforma, PEGI).
* `service`: Contiene la lógica de negocio y el acceso a ficheros, separados de la interfaz.
* `view`: Gestión de menús de consola y captura de entrada del usuario.

---

## 📄 Formato de los Ficheros CSV

> Los campos dentro de los archivos CSV se delimitan por punto y coma (`;`).

| Fichero | Formato de Campos |
| :--- | :--- |
| **`usuarios.csv`** | `id;nombre;email` |
| **`recursos.csv`** | `TIPO;id;título;año;prestado;dato1;dato2` |
| **`prestamos.csv`** | `idUsuario;idRecurso;estado;fechaPréstamo;fechaDevolución` |

---

## ▶️ Cómo Ejecutar el Proyecto

> **Requisito previo:** Java JDK 17 o superior.

### 🔷 Desde Eclipse
1. Importar el proyecto: `File` > `Import` > `Existing Projects into Workspace`.
2. Seleccionar la carpeta raíz del proyecto.
3. Ejecutar la clase `view.Main`: Clic derecho sobre `Main.java` > `Run As` > `Java Application`.

### 🔶 Desde la Terminal
Es necesario ejecutar los comandos desde la **carpeta raíz del proyecto** para garantizar que encuentre los ficheros CSV.

#### 🐧 Linux / macOS:
```bash
# Crear carpeta de compilación
mkdir -p bin

# Compilar archivos Java
javac -d bin $(find src -name "*.java")

# Ejecutar
java -cp bin view.Main
```

#### 🪟 Windows (CMD):
```cmd
# Generar lista de fuentes
dir /s /b src\*.java > fuentes.txt

# Compilar
javac -d bin @fuentes.txt

# Ejecutar
java -cp bin view.Main
```

*Nota: Si los ficheros CSV no existen al iniciar, el programa arranca normalmente y los crea al guardar.*

---

## 🔀 Flujo de Trabajo con Git

* `main` contiene la versión funcional y `develop` la integración del trabajo en curso.
* Cada funcionalidad se desarrolla en su propia rama `feature/...`.
* Los cambios se incorporan mediante **Pull Request**, revisada por otro miembro del equipo.
* Las tareas y errores se organizan mediante **GitHub Issues**.
* Los commits siguen descripciones claras de los cambios realizados.

---

## ⚠️ Problemas Relevantes Encontrados

* **Identificadores duplicados:** Al principio se pedía el ID al usuario y se podían repetir. Se cambió la lógica para generar los IDs automáticamente.
* **Duplicidad de datos al cargar ficheros:** Al conectar la persistencia con los servicios aparecían datos repetidos; se corrigió la inicialización.
* **Integración de ramas:** Al trabajar varias personas en paralelo en modelos, ficheros y préstamos, la unión de ramas dio problemas y requirió coordinación para mantener `main` funcional.
* **Identidades de Git distintas:** Algunos commits se hicieron con configuraciones de usuario distintas en ordenadores diferentes, corrigiéndolo mediante configuración de nombre y correo.
* **Conflicto controlado:** Provocado y resuelto con éxito un conflicto entre ramas (feature/test-conflicto y feature/pulir codigo) sobre el archivo `README.md` registrado en el historial de Git.
