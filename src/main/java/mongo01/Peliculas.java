package mongo01;

import java.util.List;

public class Peliculas {

	private String title;
	private int year;
	private List<String> directors;
	
	// Constructor
	public Peliculas() {}
	
	// Constructor
	public Peliculas(String title, int year, List<String> directors) {
		super();
		this.title = title;
		this.year = year;
		this.directors = directors;
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

	@Override
	public String toString() {
		return "Peliculas [title=" + title + ", year=" + year + ", directors=" + directors + "]";
	}
	
	
}
