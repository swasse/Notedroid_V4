package be.ehb.notedroidv4.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ehb.notedroidv4.R;
import be.ehb.notedroidv4.model.Note;
import be.ehb.notedroidv4.model.NoteViewModel;
import be.ehb.notedroidv4.ui.util.NotesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesOverviewFragment extends Fragment {
    private RecyclerView notesLV;
    private NotesAdapter notesAdapter;
    private FragmentActivity myContext;

    @NonNull
    public static NotesOverviewFragment newInstance(){
        return new NotesOverviewFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        myContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes_overview, container, false);

        notesAdapter = new NotesAdapter(getActivity());
        notesLV = rootView.findViewById(R.id.rv_notes);
        notesLV.setAdapter(notesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        notesLV.setLayoutManager(linearLayoutManager);

        NoteViewModel model = new ViewModelProvider(myContext).get(NoteViewModel.class);
        model.getNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.addItems(notes);
                notesAdapter.notifyDataSetChanged();
            }
        });

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_noteDetailsFragment);
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actions_list, menu);

        MenuItem item = menu.findItem(R.id.mi_search_view);
        SearchView sv = (SearchView) item.getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                notesAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
