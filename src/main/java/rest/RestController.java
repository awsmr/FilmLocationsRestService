package rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.socrata.api.HttpLowLevel;
import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.exceptions.LongRunningQueryException;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.OrderByClause;
import com.socrata.model.soql.SoqlQuery;
import com.socrata.model.soql.SortOrder;
import com.sun.jersey.api.client.ClientResponse;

import model.FilmLocation;

@Path("/")
public class RestController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FilmLocation> getFilmLocations()
	{
		Soda2Consumer consumer = Soda2Consumer.newConsumer("https://data.sfgov.org/");
		
		SoqlQuery filmLocationQuery = new SoqlQueryBuilder()
				.addSelectPhrase("title")
				.addSelectPhrase("locations")
				.addOrderByPhrase(new OrderByClause(SortOrder.Descending, "title"))
				.build();
		
		try {
			List<FilmLocation> filmLocations = consumer.query("wwmu-gmzc", filmLocationQuery, FilmLocation.LIST_TYPE);
			return filmLocations;
		} catch (SodaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FilmLocation> getFilmLocationsByTitle(@PathParam("title") String title){
		
		Soda2Consumer consumer = Soda2Consumer.newConsumer("https://data.sfgov.org/");
		
		SoqlQuery filmLocationQuery = new SoqlQueryBuilder()
				.addSelectPhrase("title")
				.addSelectPhrase("locations")
				.setWhereClause("title=\'"+ title + "\'")
				.addOrderByPhrase(new OrderByClause(SortOrder.Descending, "title"))
				.build();
		
		try {
			List<FilmLocation> filmLocations = consumer.query("wwmu-gmzc", filmLocationQuery, FilmLocation.LIST_TYPE);
			return filmLocations;
		} catch (SodaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
