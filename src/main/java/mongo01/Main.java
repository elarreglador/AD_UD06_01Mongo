package mongo01;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Random;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;


public class Main {

	public static void main(String[] args) {
		
		// Configuramos el CodecRegistry para manejar POJOs automáticamente.
        CodecRegistry pojoCodecRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        // Creamos las configuraciones del cliente incluyendo el CodecRegistry.
        MongoClientSettings config = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .build();

        // Creamos el cliente con la config del parrafo anterior.
        MongoClient client = MongoClients.create(config);
        
		// Obtenemos lista de nombres de bases de datos
		MongoIterable<String> nombresBd = client.listDatabaseNames();
		System.out.println("BD disponibles en el servidor monbodb:");
		for (String nombreBD : nombresBd) {
			System.out.println(" - " + nombreBD);
		}

		// Conectamos a la bd mflix que contiene la coleccion peliculas
        MongoDatabase db = client.getDatabase("mflix");
        System.out.println("\nConectado a " + db.getName());
        
        // Obtenemos la colección de peliculas
        System.out.println("\nObteniendo peliculas...");
        MongoCollection<Peliculas> coleccion = db.getCollection(
        		"peliculas", Peliculas.class
        );

		// Buscamos Jurassic World
        Peliculas pelicula = coleccion.find(
        	// .eq hace busqueda exacta
    		// Filters.eq("title", "Jurassic World") 
        		
        	// .regex busca Jurassic World dentro del campo "title"
        	// // "i" = no case sensitive
    		Filters.regex("title", "Jurassic World", "i") 
        )
        .first();
        
        // Imprimimos la película obtenida
        if (pelicula != null) {
            System.out.println("\nResultado de la busqueda: \n" + pelicula);
        } else {
            System.out.println("\nNo se encontró la película.");
        }

        // Cambiamos el titulo de la pelicula
        System.out.println("\nCambiamos el nombre de la pelicula");
        Random random = new Random();
        int numeroAleatorio = random.nextInt(10) + 1;
        pelicula.setTitle("Jurassic World " + numeroAleatorio);
        System.out.println(pelicula);

        // Actualizamos el documento en la base de datos
        System.out.println("\nGuardamos la modificacion");
        pelicula.save(coleccion);
        
        // Cerramos la conexión
        client.close();
		

	}

}
