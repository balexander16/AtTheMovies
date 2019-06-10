package edu.cnm.deepdive.atthemovies.viewmodel;

import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.atthemovies.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class MoviesViewModel extends ViewModel {

  private List<Movie> movies = new ArrayList<>();

  public List<Movie> getMovies() {
    return movies;
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
  }
}
