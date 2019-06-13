package edu.cnm.deepdive.atthemovies.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {

  private static final long serialVersionUID = 1L;

  public static Long last_id = 0L;

  private Long id;

  private String title;

  private String screenwriter;

  public enum Genre{
    HORROR, ACTION, ROMCOM, COMEDY, DOCUMENTARY, ANIME, SCYFY, FANTASY
  }

  private Genre genre;

  private List<Actor> actors = new ArrayList<>();

  public Movie() {
    id = ++last_id;
  }

  public Movie(Long id) {
      if(last_id < id){
        last_id = id;
      }
      this.id = id;
  }

  public Long getId() {
    return id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public List<Actor> getActors() {
    return actors;
  }

  public void setActors(List<Actor> actors) {
    this.actors = actors;
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


}
