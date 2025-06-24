# 📚 Proyecto: Desarrollo de desafio alura, usando la Gutendex API + PostgreSQL

---

## 📑 Índice

- [📖 Descripción del Proyecto](#-descripción-del-proyecto)
- [🧭 Funcionalidades del Menú](#-funcionalidades-del-menú)
- [🖥️ Demostración de Funcionalidades](#-demostración-de-funcionalidades)
- [🛠️ Tecnologías Usadas](#-tecnologías-usadas)
- [👤 Desarrollado por](#-desarrollado-por)

---

## 📖 Descripción del Proyecto

Este proyecto consiste en un buscador de libros conectado a la API web de Gutendex [https://gutendex.com/], una plataforma que ofrece acceso gratuito a una amplia colección de libros del Proyecto Gutenberg.

La aplicación permite buscar libros por título, obteniendo los resultados directamente desde Gutendex. Además, los datos de los libros y autores recuperados pueden almacenarse en una base de datos PostgreSQL. Esto permite conservar la información localmente y trabajar con ella, realizando operaciones como listar, filtrar o consultar autores y libros desde nuestra propia base de datos.

En resumen, el sistema combina la consulta externa a la API con el uso de una base de datos local para facilitar el manejo de la información obtenida.

---

## 🧭 Funcionalidades del Menú

El menú principal de la aplicación incluye las siguientes funcionalidades:

### 1 - Buscar libro por título

### 2 - Listar libros registrados

### 3 - Listar autores registrados  

### 4 - Listar autores vivos en un determinado año  

### 5 - Listar libros por idioma  

---

## 🖥️ Demostración de Funcionalidades

### 🔍 Buscar libro por título
![Buscar libro](ruta/a/imagen_buscar_libro.png)  
*Se busca el libro por el titulo ingresado del usuario en la Api de Gutendex y luego se verifica si ya se ha registrado en la base de datos el autor y el libro, en caso de que el autor ya exista entonces solo se guarda el libro en la base de datos de PostresSQL.*

---

### 📚 Listar libros registrados
![Listar libros](ruta/a/imagen_listar_libros.png)  
*Muestra todos los libros guardados en la base de datos.*

---

### 🧑‍🎨 Listar autores registrados
![Autores registrados](ruta/a/imagen_autores_registrados.png)  
*Visualización de los autores que ya se han registrado en la base de datos.*

---

### 📅 Autores vivos en determinado año
![Autores vivos](ruta/a/imagen_autores_vivos.png)  
*Filtrado de autores que estaban vivos en un año ingresado por el usuario .*

---

### 🌐 Listar libros por idioma
![Libros por idioma](ruta/a/imagen_libros_idioma.png)  
*Filtrado de libros en la base de datos según idioma ingresado por el usuario.*

---

## 🛠️ Tecnologías Usadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **JPA / Hibernate**
- **PostgreSQL**
- **Gutendex API**
- **Jackson**

---

## 👤 Desarrollado por

**Nombre:** Fabian Torres R.
**Email:** tfabian@gmail.com
