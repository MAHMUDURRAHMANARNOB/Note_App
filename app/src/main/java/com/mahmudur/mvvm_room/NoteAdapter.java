package com.mahmudur.mvvm_room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter/*RecyclerView.Adapter*/<Note,/*for listadapter*/NoteAdapter.NoteHolder> { //better to use List adapter here
    /*private List<Note> notes = new ArrayList<>();*/
    /*we can remove it, because instead of keeping our List directly on our note adapter,
     we will pass the list to the superclass to the list adapter. so the ListAdapter will take care of it
     and we don't have to store it ourself.?*/
    private OnItemClickListener listener;

    public NoteAdapter(/*@NonNull DiffUtil.ItemCallback<Note> diffCallback*/) {
        /*Created for List Adapter*/
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return  oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority().equals(newItem.getPriority());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_items, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
//        Note currentNote = notes.get(position);
        Note currentNote = getItem(position);
        holder.Title.setText(currentNote.getTitle());
        holder.Title.setText(currentNote.getTitle());
        holder.Description.setText(currentNote.getDescription());
        holder.Priority.setText(currentNote.getPriority());
    }


    /*@Override
    public int getItemCount() {
        return notes.size();
    }*/ //ListAdapter Will take care of this

    /*public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged(); //for simplicity but not recommanded
    }*/ //we will use a new part of LISTADAPTER

    public Note getNoteAt(int position){
//        return notes.get(position);
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView Title, Description, Priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            Description = itemView.findViewById(R.id.description);
            Priority = itemView.findViewById(R.id.priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }

                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
