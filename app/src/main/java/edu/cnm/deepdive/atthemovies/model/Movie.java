package edu.cnm.deepdive.atthemovies.model;

import androidx.annotation.NonNull;

public class Movie {

  private static Long last_id = 0L;

  private Long id;

  private String title;

  public Movie() {
    id = ++last_id;
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

  @NonNull
  @Override
  public String toString() {
    return title;
  }

}
