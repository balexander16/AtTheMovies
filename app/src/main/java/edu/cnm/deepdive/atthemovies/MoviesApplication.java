package edu.cnm.deepdive.atthemovies;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class MoviesApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
