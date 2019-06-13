package edu.cnm.deepdive.atthemovies.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.atthemovies.model.Movie;
import java.util.List;

@Dao
public interface MovieDao {

  @Insert
  void insert(Movie movie);

  @Query("SELECT * FROM movie")
  LiveData<List<Movie>>getAll();

  @Query("SELECT * FROM movie WHERE id = :id")
  LiveData<Movie>findById(Long id);

}
