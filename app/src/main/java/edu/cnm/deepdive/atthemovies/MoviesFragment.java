package edu.cnm.deepdive.atthemovies;


import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.atthemovies.model.Movie;
import edu.cnm.deepdive.atthemovies.model.Movie.Genre;
import edu.cnm.deepdive.atthemovies.viewmodel.MoviesViewModel;
import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


  private Context context;

  public MoviesFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_movies, container, false);

    final ListView moviesListView = view.findViewById(R.id.movies_list);

    final MoviesViewModel viewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);



    viewModel.getMoviesLiveData(context).observe(this, new Observer<Map<Long, Movie>>() {
      @Override
      public void onChanged(Map<Long, Movie> longMovieMap) {
        final ArrayAdapter<Movie> adapter =
            new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,
                new ArrayList<Movie>(longMovieMap.values()));
        moviesListView.setAdapter(adapter);
        moviesListView.setOnItemClickListener(new OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // This code opens the actor fragment, and passes the movie id that was clicked.
            MoviesFragmentDirections.ActionMoviesFragmentToActors action =
                MoviesFragmentDirections.actionMoviesFragmentToActors()
                    .setMovieId(adapter.getItem(position).getId());
            Navigation.findNavController(view).navigate(action);
          }
        });
      }
    });


    final Spinner genreSpinner = view.findViewById(R.id.new_movie_genre);
    ArrayAdapter<Movie.Genre> genreAdapter = new ArrayAdapter<>(context,
        android.R.layout.simple_spinner_item, Genre.values());
    genreSpinner.setAdapter(genreAdapter);

    Button newMovieButton = view.findViewById(R.id.new_movie_button);
    newMovieButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText newMovieNameEditText = view.findViewById(R.id.new_movie_name);
        EditText newMovieScreenwriter = view.findViewById(R.id.new_movie_screenwriter);
        Movie newMovie = new Movie();
        newMovie.setTitle(newMovieNameEditText.getText().toString());
        newMovie.setScreenwriter(newMovieScreenwriter.getText().toString());
        newMovie.setGenre((Genre) genreSpinner.getSelectedItem());
        viewModel.addMovie(newMovie, context);
        newMovieNameEditText.setText("");
      }
    });


    // Inflate the layout for this fragment

    return view;
  }

}
