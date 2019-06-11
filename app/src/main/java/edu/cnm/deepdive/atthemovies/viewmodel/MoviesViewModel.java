package edu.cnm.deepdive.atthemovies.viewmodel;

import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.atthemovies.model.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviesViewModel extends ViewModel {

  private Map<Long,Movie> movies = new HashMap<>();

  public List<Movie> getMovies() {
    return new ArrayList<Movie>(movies.values());
  }

  public void addMovie(Movie movie) {
    movies.put(movie.getId(),movie);
  }

  public Movie getMovie(Long id){
    return movies.get(id);
  }

}
