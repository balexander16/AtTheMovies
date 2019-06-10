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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.atthemovies.model.Movie;
import edu.cnm.deepdive.atthemovies.viewmodel.MoviesViewModel;


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

    ListView moviesListView = view.findViewById(R.id.movies_list);

    final MoviesViewModel viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

    final ArrayAdapter<Movie> adapter =
        new ArrayAdapter<Movie>(context, android.R.layout.simple_list_item_1,
            viewModel.getMovies());

    moviesListView.setAdapter(adapter);

    Button newMovieButton = view.findViewById(R.id.new_movie_button);
    newMovieButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText newMovieNameEditText = view.findViewById(R.id.new_movie_name);
        Movie newMovie = new Movie();
        newMovie.setTitle(newMovieNameEditText.getText().toString());
        viewModel.addMovie(newMovie);
        adapter.notifyDataSetChanged();
        newMovieNameEditText.setText("");
      }
    });

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
    // Inflate the layout for this fragment

    return view;
  }

}
