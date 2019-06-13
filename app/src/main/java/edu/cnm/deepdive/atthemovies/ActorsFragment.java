package edu.cnm.deepdive.atthemovies;


import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.atthemovies.MoviesFragmentDirections.ActionMoviesFragmentToActors;
import edu.cnm.deepdive.atthemovies.model.Actor;
import edu.cnm.deepdive.atthemovies.viewmodel.MoviesViewModel;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActorsFragment extends Fragment {

  private Context context;

  public ActorsFragment() {
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
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_actors, container, false);

    final MoviesViewModel viewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);

    final Long movieId = ActorsFragmentArgs.fromBundle(getArguments()).getMovieId();
    viewModel.getActorsLiveData().observe(this, new Observer<List<Actor>>() {
      @Override
      public void onChanged(List<Actor> actors) {
        final ArrayAdapter<Actor> adapter = new ArrayAdapter<>(context,
            android.R.layout.simple_list_item_1, actors);

        ListView actorsListView = view.findViewById(R.id.actors_list);
        actorsListView.setAdapter(adapter);
      }
    });



    Button newActorButton = view.findViewById(R.id.new_actor_button);
    final EditText newActorName = view.findViewById(R.id.new_actor_name);
    newActorButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Actor newActor = new Actor();
        newActor.setName(newActorName.getText().toString());
        viewModel.addActors(newActor);
        newActorName.setText("");
      }
    });


    return view;
  }

}
