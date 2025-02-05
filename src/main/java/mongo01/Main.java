package mongo01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;

public class Main {

	public static void main(String[] args) {
		
		MongoClient client = MongoClients.create();
		MongoIterable<String> nombresBd = client.listDatabaseNames();
		
		System.out.println("BD disponibles en el servidor monbodb:");
		for (String nombre : nombresBd) {
			System.out.println(" - " + nombre);
		}

	}

}
