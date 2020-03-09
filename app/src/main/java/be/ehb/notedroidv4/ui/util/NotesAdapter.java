package be.ehb.notedroidv4.ui.util;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.notedroidv4.R;
import be.ehb.notedroidv4.model.Note;
import be.ehb.notedroidv4.model.NoteViewModel;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> implements Filterable {

    private FragmentActivity activity;
    private List<Note> shownNotes;
    private List<Note> allNotes;

    class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView dateTV, titleTV;
        final Button btnDelete;
        final CardView card;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTV = itemView.findViewById(R.id.date_tv);
            titleTV = itemView.findViewById(R.id.title_tv);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnDelete.setOnClickListener(deleteListener);
            card = itemView.findViewById(R.id.note_card);
            card.setOnClickListener(detailsListener);
        }

        private View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Note toDelete = shownNotes.get(pos);

                NoteViewModel model = new ViewModelProvider(activity).get(NoteViewModel.class);
                model.deleteNote(toDelete);
                notifyDataSetChanged();
            }
        };

        private View.OnClickListener detailsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Note toPass = shownNotes.get(pos);

                Bundle data = new Bundle();
                data.putSerializable("passedNote", toPass);

                Navigation.findNavController(itemView).navigate(R.id.action_nav_home_to_noteDetailsFragment, data);
            }
        };
    }

    public NotesAdapter(FragmentActivity activity) {
        this.allNotes = new ArrayList<>();
        this.shownNotes = new ArrayList<>();
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View card = LayoutInflater.from(context).inflate(R.layout.note_row, viewGroup, false);
        return new NoteViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        Note n = shownNotes.get(i);

        noteViewHolder.dateTV.setText(n.getLastModifiedDate().toString());
        noteViewHolder.titleTV.setText(n.getTitle());
    }

    @Override
    public int getItemCount() {
        return shownNotes.size();
    }

    public void addItems(List<Note> items) {
        shownNotes.clear();
        allNotes.clear();
        shownNotes.addAll(items);
        allNotes.addAll(items);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String input = charSequence.toString();
                shownNotes = allNotes;
                if (!input.isEmpty()) {
                    ArrayList<Note> tempList = new ArrayList<>();

                    for (Note element : shownNotes) {
                        if (element.getTitle().toLowerCase().contains(input.toLowerCase())) {
                            tempList.add(element);
                        }
                    }
                    shownNotes = tempList;
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }
}