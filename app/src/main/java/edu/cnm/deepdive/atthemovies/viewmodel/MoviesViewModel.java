package edu.cnm.deepdive.atthemovies.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.atthemovies.MoviesLiveData;
import edu.cnm.deepdive.atthemovies.model.Movie;
import edu.cnm.deepdive.atthemovies.service.FileService;
import java.util.HashMap;
import java.util.Map;

public class MoviesViewModel extends ViewModel {

  private MoviesLiveData movies;

  public MoviesLiveData getMoviesLiveData(Context context) {
    if(movies == null) {
      movies = new MoviesLiveData(context.getApplicationContext());
    }
    return movies;
  }

  public void addMovie(Movie movie, Context context) {
    getMoviesLiveData(context).addMovie(movie);

  }

  public Movie getMovie(Long id, Context context){
    return getMoviesLiveData(context).getValue().get(id);
  }

}
