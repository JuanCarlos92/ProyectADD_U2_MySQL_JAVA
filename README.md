# Gestión de Productos y Categorías en una Base de Datos Relacional

## Descripción
Este proyecto es una aplicación Java que se conecta a una base de datos MySQL (usando JDBC) y realiza diversas operaciones relacionadas con la gestión de productos y categorías. Las operaciones incluyen la creación de tablas, inserción de datos, consultas y manejo de transacciones.

## Objetivo
Desarrollar una aplicación que realice las siguientes acciones:

- Crear una base de datos y tablas necesarias para gestionar productos y categorías.
- Insertar productos en las tablas asociándolos a categorías.
- Realizar consultas para mostrar productos con su categoría correspondiente y productos cuyo precio es mayor a 100.
- Utilizar transacciones para insertar un nuevo producto, gestionando errores con rollback si es necesario.

## Requisitos

- **Java 8 o superior**
- **MySQL 8 o superior**
- **Maven**
- Dependencia JDBC para MySQL

## Instrucciones

1. **Configurar el entorno de trabajo**:
   - Asegúrate de tener MySQL instalado y corriendo en tu máquina.
   - Configura el archivo `pom.xml` con la dependencia de MySQL JDBC.

2. **Conexión a la base de datos**:
   - Utiliza JDBC para establecer la conexión a MySQL. Se recomienda usar la configuración básica para una base de datos local.

3. **Creación de tablas**:
   - Se crean dos tablas: `Producto` y `Categoria`.
   - La tabla `Producto` contiene los campos:
     - `id`: Clave primaria, tipo `INT`.
     - `nombre`: Nombre del producto, tipo `VARCHAR(100)`.
     - `precio`: Precio del producto, tipo `DECIMAL(10,2)`.
     - `categoria_id`: Clave foránea que hace referencia a `Categoria`.
   - La tabla `Categoria` contiene los campos:
     - `id`: Clave primaria, tipo `INT`.
     - `nombre`: Nombre de la categoría, tipo `VARCHAR(50)`.

4. **Inserción de datos**:
   - Inserta productos con diferentes precios.
   - Inserta categorías para asociar los productos a ellas.

5. **Consultas**:
   - Realiza una consulta para mostrar todos los productos junto con su categoría correspondiente.
   - Realiza otra consulta para mostrar todos los productos cuyo precio sea mayor a 100.

6. **Transacciones**:
   - Utiliza transacciones para insertar un nuevo producto y asociarlo a una categoría.
   - Si ocurre algún error durante la inserción, realiza un rollback para deshacer los cambios.

7. **Cerrar recursos**:
   - Asegúrate de cerrar la conexión, `Statement` y `ResultSet` después de cada operación.

## Dependencias Maven

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.29</version>
</dependency>
