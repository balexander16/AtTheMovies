package edu.cnm.deepdive.atthemovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.atthemovies.model.Movie;
import edu.cnm.deepdive.atthemovies.service.DatabaseService;
import java.util.HashMap;
import java.util.Map;

public class MoviesLiveData extends LiveData<Map<Long, Movie>> {

  private final Context context;
  private final DatabaseService databaseService;

  public MoviesLiveData(Context context){
    this.context = context;
    this.databaseService = new DatabaseService(context);
    loadData();
  }

  private void loadData() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try (SQLiteDatabase db = databaseService.getReadableDatabase()) {

          Cursor cursor = db.rawQuery("SELECT * FROM movies", null);
          Map<Long, Movie> movieMap = new HashMap<>();
          while (cursor.moveToNext()) {
            Movie movie = new Movie(cursor.getLong(cursor.getColumnIndex("id")));
            movie.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            movie.setScreenwriter(cursor.getString(cursor.getColumnIndex("screenwriter")));
            movie.setGenre(Movie.Genre.valueOf(cursor.getString(cursor.getColumnIndex("genre"))));
            movieMap.put(movie.getId(), movie);
          }
          postValue(movieMap);
        }
      }
    }).start();
  }

  public void addMovie(final Movie movie) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try(SQLiteDatabase db = databaseService.getWritableDatabase()) {
          ContentValues values = new ContentValues();
          values.put("id", movie.getId());
          values.put("title", movie.getTitle());
          values.put("screenwriter", movie.getScreenwriter());
          values.put("genre", movie.getGenre().name());
          db.insert("movies", null, values);
          getValue().put(movie.getId(), movie);
          postValue(getValue());
        }
        }
    }).start();
  }

}
