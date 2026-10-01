📚 Biblioteca Multimedia
Aplicación de consola en Java para gestionar una biblioteca multimedia con usuarios, recursos (libros, películas y videojuegos) y un sistema de préstamos y devoluciones. Los datos se guardan en ficheros CSV, por lo que se mantienen al cerrar el programa.

Proyecto de la Práctica 1 - Git y repaso de Programación de Acceso a Datos (DAM2, curso 2026-2027).
👥 Integrantes
Josué Mateo
Julen Ullibarri
Ander Gago
Erlantz Amigo
✨ Funcionalidades
Recursos
Crear libros, películas y videojuegos.
Listar, buscar por ID, modificar y eliminar recursos.
Consultar si un recurso está disponible o prestado.

Usuarios
Crear, listar, buscar por ID, modificar y eliminar usuarios.
Los identificadores se generan automáticamente, así que no se pueden duplicar.

Préstamos y devoluciones
Se comprueba que el usuario y el recurso existen.
No se puede prestar un recurso que ya está prestado.
Al prestar, el recurso pasa a "Prestado"; al devolverlo, vuelve a "Disponible".
Cada préstamo guarda su fecha de préstamo, su estado y su fecha de devolución.

Consultas y búsquedas
Búsqueda de recursos por título (también por parte del título).
Recursos disponibles.
Préstamos activos.
Préstamos de un usuario.
Recursos filtrados por tipo.

Persistencia y control de errores

Carga de datos al iniciar y guardado tras cada cambio (usuarios.csv, recursos.csv, prestamos.csv).
Control de opciones de menú incorrectas, texto cuando se espera un número, valores fuera de rango (por ejemplo, años mínimos según el tipo de recurso), usuarios o recursos inexistentes, préstamos o devoluciones imposibles y ficheros inexistentes o con líneas incorrectas.
🗂️ Estructura del proyecto
TrabajoAccesoDatos/

├── src/

│   ├── model/

│   │   ├── Recurso.java        (clase abstracta)

│   │   ├── Libro.java

│   │   ├── Pelicula.java

│   │   ├── Videojuego.java

│   │   ├── Usuario.java

│   │   └── Prestamo.java

│   ├── service/

│   │   ├── RecursoService.java     (lógica de recursos)

│   │   ├── GestionUsuarios.java    (lógica de usuarios)

│   │   ├── GestorPrestamos.java    (lógica de préstamos y devoluciones)

│   │   ├── FicheroRecursos.java    (lectura/escritura de recursos.csv)

│   │   ├── FicheroUsuarios.java    (lectura/escritura de usuarios.csv)

│   │   └── FicheroPrestamos.java   (lectura/escritura de prestamos.csv)

│   └── view/

│       └── Main.java           (menús de consola y punto de entrada)

├── recursos.csv

├── usuarios.csv

├── prestamos.csv

├── .gitignore

└── README.md

model: las clases de datos. Recurso es abstracta y Libro, Pelicula y Videojuego heredan de ella, cada una con sus datos propios (autor y páginas; director y duración; plataforma y PEGI).
service: la lógica de negocio y el acceso a ficheros, separados de la interfaz.
view: los menús y la lectura de datos por consola.
Formato de los ficheros
Los campos van separados por ;.

Fichero
Formato
usuarios.csv
id;nombre;email
recursos.csv
TIPO;id;título;año;prestado;dato1;dato2
prestamos.csv
idUsuario;idRecurso;estado;fechaPréstamo;fechaDevolución

▶️ Cómo ejecutarlo
Requisitos: Java 17 o superior.
Desde Eclipse
Importar el proyecto (File > Import > Existing Projects into Workspace).
Ejecutar la clase view.Main (Run As > Java Application).
Desde la terminal
Hay que ejecutarlo desde la carpeta raíz del proyecto, para que encuentre los ficheros CSV.

# Compilar

mkdir -p bin

javac -d bin $(find src -name "*.java")

# Ejecutar

java -cp bin view.Main

En Windows (CMD):

dir /s /b src\*.java > fuentes.txt

javac -d bin @fuentes.txt

java -cp bin view.Main

Si los ficheros CSV no existen, el programa arranca igualmente y los crea al guardar.
🔀 Flujo de trabajo con Git
main contiene la versión funcional y develop la integración del trabajo en curso.
Cada funcionalidad se desarrolla en su propia rama feature/....
Los cambios se incorporan mediante Pull Request, revisada por otro miembro del equipo.
Las tareas y errores se organizan con Issues de GitHub.
Los commits describen lo que se ha hecho.

⚠️ Problemas relevantes encontrados
Identificadores duplicados: al principio se pedía el ID al usuario y se podían repetir. Se cambió la lógica para generar los IDs automáticamente.
Duplicidad de datos al cargar ficheros: al conectar la persistencia con los servicios aparecían datos repetidos, y hubo que corregirlo.
Integración de ramas: al trabajar varias personas en paralelo en modelos, ficheros y préstamos, la unión de ramas dio problemas y hubo que coordinarse para dejar main funcional.
Identidades de Git distintas: algunos commits se hicieron con configuraciones de usuario distintas en ordenadores diferentes, y lo corregimos configurando cada uno su propio nombre y correo.
Conflicto controlado: (completar aquí: qué ramas chocaron, en qué fichero y cómo se resolvió).

Nota: version revisada por Mateo
