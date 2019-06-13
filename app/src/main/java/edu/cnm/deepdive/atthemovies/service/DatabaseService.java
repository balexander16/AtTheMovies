package edu.cnm.deepdive.atthemovies.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseService extends SQLiteOpenHelper {


  public DatabaseService(@Nullable Context context) {
    super(context, "movies.db", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE movies ("
          + "id INTEGER PRIMARY KEY,"
          + "title TEXT,"
          + "screenwriter TEXT,"
          + "genre TEXT)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Do Nothing
  }
}
