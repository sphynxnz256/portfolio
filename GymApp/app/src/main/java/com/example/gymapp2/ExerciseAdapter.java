package com.example.gymapp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

//adapter for our Exercise
public class ExerciseAdapter extends ListAdapter<Exercise, ExerciseAdapter.ExerciseHolder> {
    private OnExerciseItemClickListener clickListener;
    private OnExerciseLongClickListener longClickListener;

    public ExerciseAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF_CALLBACK = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getSets() == (newItem.getSets()) &&
                    oldItem.getReps() == (newItem.getReps()) &&
                    oldItem.getWeight().equals(newItem.getWeight()) &&
                    oldItem.getWeb_link().equals(newItem.getWeb_link()) &&
                    oldItem.getVideo_link().equals(newItem.getVideo_link()) &&
                    oldItem.getWorkout_id() == newItem.getWorkout_id();
        }
    };

    @NonNull
    @Override
    public ExerciseAdapter.ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);
        return new ExerciseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ExerciseHolder holder, int position) {
        Exercise currentExercise = getItem(position);
        holder.textViewName.setText(currentExercise.getName());
    }

    public Exercise getExerciseAt(int position) {
        return getItem(position);
    }

    class ExerciseHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;

        public ExerciseHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (clickListener != null && position != RecyclerView.NO_POSITION) {
                        clickListener.onExerciseItemClick(getItem(position));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                        longClickListener.onExerciseLongClickListener(getItem(position));
                    }
                    return true;
                }
            });
        }
    }

    //interface for when exercise is clicked
    public interface OnExerciseItemClickListener {
        void onExerciseItemClick(Exercise exercise);
    }

    //method for when exercise is clicked
    public void setOnExerciseItemClickListener(OnExerciseItemClickListener listener) {
        this.clickListener = listener;
    }

    //interface for when exercise is long clicked
    public interface OnExerciseLongClickListener {
        void onExerciseLongClickListener(Exercise exercise);
    }

    //method for when exercise is long clicked
    public void setOnExerciseItemLongCLickListener(OnExerciseLongClickListener listener) {
        this.longClickListener = listener;
    }
}
