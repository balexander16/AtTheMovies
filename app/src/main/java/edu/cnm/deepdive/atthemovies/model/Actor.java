package edu.cnm.deepdive.atthemovies.model;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class Actor implements Serializable {

  private static final long serialVersionUID = 1L;

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
