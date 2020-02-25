package be.ehb.notedroidv4.ui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.threeten.bp.LocalDate;

import be.ehb.notedroidv4.R;
import be.ehb.notedroidv4.model.Note;
import be.ehb.notedroidv4.model.NoteViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteDetailsFragment extends Fragment {

    private EditText etTitle, etContent;
    private Note selected;

    public NoteDetailsFragment() {
    }

    public static NoteDetailsFragment newInstance(){
        return new NoteDetailsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_note_details, container, false);

        setHasOptionsMenu(true);

        selected = (getArguments() != null)?(Note) getArguments().getSerializable("passedNote"):null;

        etTitle = rootView.findViewById(R.id.title_et);
        etContent = rootView.findViewById(R.id.content_et);

        if(selected != null){
            etTitle.setText(selected.getTitle());
            etContent.setText(selected.getContent());
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.actions_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mi_save) {
            NoteViewModel model = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
            //new
            if(selected == null) {
                Note n = new Note(etTitle.getText().toString(), etContent.getText().toString());
                model.addNote(n);
            }
            else {
                selected.setTitle(etTitle.getText().toString());
                selected.setContent(etContent.getText().toString());
                selected.setLastModifiedDate(LocalDate.now());

                //TODO in real app with database
            }
            Navigation.findNavController(getView()).navigateUp();
        }
        hideKeyboardFrom(getActivity(), getView().getRootView());
        return super.onOptionsItemSelected(item);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
