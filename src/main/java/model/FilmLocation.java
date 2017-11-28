package model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jersey.api.client.GenericType;

public class FilmLocation {
	
    public static final GenericType<List<FilmLocation>> LIST_TYPE = new GenericType<List<FilmLocation>>() {};
	
	final String title;
	final String locations;
	
	@JsonCreator
	public FilmLocation(@JsonProperty("title") String title, @JsonProperty("locations") String locations)	{
		this.title = title;
		this.locations = locations;
	}

	public String getTitle() {
		return title;
	}

	public String getLocations() {
		return locations;
	}
	
	@Override
	public String toString() {
		return "FilmLocation [title=" + title + ", locations" + locations +"]";
				
	}
	
}
