package edu.cnm.deepdive.atthemovies.model;

import androidx.annotation.NonNull;

public class Actor {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NonNull
  @Override
  public String toString() {
    return name;
  }

}
