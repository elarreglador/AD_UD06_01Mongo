package mongo01;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Peliculas {

	private ObjectId id;
	private String title;
	private int year;
	private List<String> directors;
	
	// Constructor
	public Peliculas() {}
	
	// Constructor
	public Peliculas(String title, int year, List<String> directors) {
		this.title = title;
		this.year = year;
		this.directors = directors;
	}

	@Override
	public String toString() {
		return "Peliculas [title=" + title + 
				", year=" + year + 
				", directors=" + directors + "]";
	}
	
	public void save(MongoCollection<Peliculas> coleccion) {
		// Actualiza alguno de los campos del objeto
	    coleccion.updateOne(
	        Filters.eq("_id", this.getId()),  // Usamos el id como filtro
	        Updates.combine(
	            Updates.set("title", this.getTitle()), 
	            Updates.set("director", this.getDirectors()),
	            Updates.set("year", this.getYear()) 
	        )
	    );
	}
	
	public void delete(MongoCollection<Peliculas> coleccion) {
	    // Elimina el objeto
	    if(this.id != null) {
	        coleccion.deleteOne(Filters.eq("_id", this.id));
	    } else {
	        System.out.println("El objeto no tiene un id asignado, no se puede eliminar.");
	    }
	}
	
	public void add(MongoCollection<Peliculas> coleccion) {
	    // Inserta el objeto actual en la colección
	    coleccion.insertOne(this);
	}



	// Getters y setters
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}
	

	
}
