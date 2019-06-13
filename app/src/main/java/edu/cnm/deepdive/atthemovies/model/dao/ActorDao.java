package edu.cnm.deepdive.atthemovies.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.atthemovies.model.Actor;
import java.util.List;

@Dao
public interface ActorDao {

  @Insert
  void insert(Actor actor);

  @Query("SELECT * FROM actor")
  LiveData<List<Actor>> getAll();

}
