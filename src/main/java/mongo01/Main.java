package mongo01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Main {

	public static void main(String[] args) {
		
		// Conectamos a mongodb
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

	}

}
