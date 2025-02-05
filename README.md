# AD_UD06_01Mongo

Esta aplicación en Java interactúa con una base de datos MongoDB para manejar información sobre películas. Permite realizar operaciones como buscar una película por título, actualizar el título de una película y guardar los cambios realizados en la base de datos.

## Requisitos

JDK 11 o superior
MongoDB (local o remoto)
Dependencias de Maven:
mongodb-driver-sync
bson
Instalación
Clonar el repositorio:

```bash
git clone https://github.com/tuusuario/mongo01.git
```

Agrega las dependencias con Maven en el archivo pom.xml: 

```
<dependencies>
	<dependency>
 		<groupId>org.mongodb</groupId>
 		<artifactId>mongodb-driver-sync</artifactId>
 		<version>4.11.1</version>
	</dependency>
</dependencies>
```

En el directorio raíz de tu proyecto, ejecuta:

```bash
mvn install
```

Configurar MongoDB: Asegúrate de que tu servidor MongoDB esté en funcionamiento. 

##Esta mongo funcionando?

Ejecuta este comando en la terminal para ver si el servicio está activo:

```bash
sudo systemctl status mongod
```
Si aparece como inactive (dead), inícialo con:

```bash
sudo systemctl start mongod
```

Si quieres que se inicie automáticamente al arrancar el sistema:

```bash
sudo systemctl enable mongod
```

Si quieres que NO se inicie automáticamente al arrancar el sistema:

```bash
sudo systemctl disable mongod
```

##Uso

Conectar a MongoDB: La aplicación se conecta a una base de datos llamada mflix que contiene una colección llamada peliculas.

Búsqueda de películas: La aplicación busca una película cuyo título contenga "Jurassic World" (búsqueda no sensible a mayúsculas y minúsculas) y muestra la información de la película encontrada.

Actualización de una película: Una vez que se encuentra la película, la aplicación genera un número aleatorio entre 1 y 10 y lo agrega al título de la película, por ejemplo, cambiando de Jurassic World a Jurassic World 5.

Guardar los cambios: Después de actualizar el título, la aplicación guarda la modificación en la base de datos.

##Ejemplo de ejecución

Salida esperada:

```
BD disponibles en el servidor mongodb:
 - admin
 - config
 - local
 - mflix

Conectado a mflix

Obteniendo peliculas...

Resultado de la busqueda: 
Peliculas [title=Jurassic World, year=2015, directors=[Colin Trevorrow]]

Cambiamos el nombre de la pelicula

Peliculas [title=Jurassic World 3, year=2015, directors=[Colin Trevorrow]]

Guardamos la modificacion
```

##Estructura del proyecto

El proyecto tiene la siguiente estructura:

```
mongo01/
 ├── src/main/java
 │             └── mongo01/
 │                   ├── Main.java         # Clase principal con la lógica de negocio
 │                   └── Peliculas.java    # POJO para la colección 'peliculas'
 └── pom.xml                  		   # Archivo de configuración de Maven
```

##Notas
La clase Peliculas contiene los atributos id, title, year, y directors. El id es el identificador único de cada película en MongoDB.
El código utiliza la API oficial de MongoDB en Java para interactuar con la base de datos y realizar las operaciones CRUD.