package edu.cnm.deepdive.atthemovies.service;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.atthemovies.model.Movie;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class FileService {

  public static final String FILE_NAME = "movie_data.ser";
  public static FileService instance;
  private Context context;

  public FileService(Context context) {
    this.context = context.getApplicationContext();
  }

  public static FileService getInstance(Context context){
    if(instance == null) {
      instance = new FileService(context);
    }
    return instance;
  }

  public void writeToFile(final Map<Long, Movie> data) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try(FileOutputStream fos = context.openFileOutput(FILE_NAME
                , Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
          oos.writeObject(data);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  public void getFromFile(final MutableLiveData<Map<Long,Movie>> movies){
    new Thread(new Runnable() {
      @Override
      public void run() {
        try(FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis)){
          Map<Long, Movie> movieMap = ((Map<Long, Movie>) ois.readObject());
          movies.postValue(movieMap);
          for (Long id:movieMap.keySet()){
            if (Movie.last_id < id){
              Movie.last_id = id;
            }
          }
        } catch (ClassNotFoundException | IOException e) {
          movies.postValue(new HashMap<Long, Movie>());
          e.printStackTrace();
        }
      }
    }).start();
  }
}
