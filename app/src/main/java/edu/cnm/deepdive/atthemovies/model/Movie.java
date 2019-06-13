package edu.cnm.deepdive.atthemovies.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Movie implements Serializable {

  private static final long serialVersionUID = 1L;

  public static Long last_id = 0L;

  @PrimaryKey(autoGenerate = true)
  private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  private String title;

  private String screenwriter;

  public enum Genre{
    HORROR, ACTION, ROMCOM, COMEDY, DOCUMENTARY, ANIME, SCYFY, FANTASY
  }

  @TypeConverters(GenreConverter.class)
  private Genre genre;

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getScreenwriter() {
    return screenwriter;
  }

  public void setScreenwriter(String screenwriter) {
    this.screenwriter = screenwriter;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  @NonNull
  @Override
  public String toString() {
    return title + ": " + genre;
  }


  public static class GenreConverter {

    @TypeConverter
    public static Genre stringToGenre(String value){
      return Genre.valueOf(value);
    }

    @TypeConverter
    public static String genreToString(Genre genre) {
      return genre.name();
    }

  }
}
