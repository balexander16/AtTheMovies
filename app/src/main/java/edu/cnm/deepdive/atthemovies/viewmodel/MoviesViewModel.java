package edu.cnm.deepdive.atthemovies.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.atthemovies.model.Movie;
import edu.cnm.deepdive.atthemovies.service.FileService;
import java.util.HashMap;
import java.util.Map;

public class MoviesViewModel extends ViewModel {

  private MutableLiveData<Map<Long,Movie>> movies;

  public MutableLiveData<Map<Long, Movie>> getMoviesLiveData(Context context) {
    if(movies == null) {
      movies = new MutableLiveData<>();
      FileService.getInstance(context).getFromFile(movies);
    }
    return movies;
  }

  public void addMovie(Movie movie, Context context) {
    final Map<Long, Movie> movieMap = getMoviesLiveData(context).getValue();
    movieMap.put(movie.getId(),movie);
    getMoviesLiveData(context).setValue(movieMap);

  }

  public Movie getMovie(Long id, Context context){
    return getMoviesLiveData(context).getValue().get(id);
  }

  public void saveData(Context context) {
    FileService.getInstance(context).writeToFile(getMoviesLiveData(context).getValue());
  }

}
