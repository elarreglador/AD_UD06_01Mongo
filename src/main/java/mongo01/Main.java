package mongo01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;


public class Main {

	public static void main(String[] args) {
		
		// Conectamos a mongodb (mongodb://localhost:27017)
		MongoClient client = MongoClients.create();
		
		// Obtenemos lista de nombres de bases de datos
		MongoIterable<String> nombresBd = client.listDatabaseNames();
		
		System.out.println("BD disponibles en el servidor monbodb:");
		for (String nombreBD : nombresBd) {
			System.out.println(" - " + nombreBD);
		}
		
		// Conectamos a la bd llamada mflix
		MongoDatabase db = client.getDatabase("mflix");
		
		// Obtenemos lista de colecciones (tablas)
		MongoIterable<String> colecciones = db.listCollectionNames();
		
		System.out.println("\nTablas disponibles en " + db.getName());
		for (String nombreColeccion : colecciones) {
			System.out.println(" - " + nombreColeccion);
		}
		
		// Obtenemos la coleccion peliculas
		MongoCollection<org.bson.Document> col = db.getCollection("peliculas");
		
		// Buscamos Jurassic World
		String resultado = col.find(Filters.eq("title", "Jurassic World") )
			.projection( // Define qué campos se incluyen o excluyen en el resultado.
				Projections.fields(
					// Solo estos campos en el resultado
					Projections.include("title", "year", "directors"),
					// Excluye el campo _id
					Projections.excludeId()
				)
			)
			.first() // Solo el primero
			.toJson(); // Convierte el BSON formato JSON.
		
		System.out.println("\nResultado busqueda de 'Jurassic World':");
		System.out.println(resultado);
		
		
		client.close();
	}

}
