package com.tanzible.teacherfinder.lerningActivity.teacherSecondary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryModel;

public class TeacherSecondaryAdapter  extends FirestoreRecyclerAdapter<TeacherSecondaryModal, TeacherSecondaryAdapter.TeacherSecondaryHolder> {

    public TeacherSecondaryAdapter(@NonNull FirestoreRecyclerOptions<TeacherSecondaryModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherSecondaryHolder teacherSecondaryHolder, int i, @NonNull TeacherSecondaryModal teacherSecondaryModal) {

        teacherSecondaryHolder.txt_name.setText(teacherSecondaryModal.getName());
        teacherSecondaryHolder.txt_experience.setText(teacherSecondaryModal.getExperience());
        teacherSecondaryHolder.txt_subjects.setText(teacherSecondaryModal.getSubject());
        teacherSecondaryHolder.txt_priority.setText(teacherSecondaryModal.getPriority());

    }

    @NonNull
    @Override
    public TeacherSecondaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);
        return new TeacherSecondaryHolder(view);
    }

    public class TeacherSecondaryHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        TextView txt_experience;
        TextView txt_subjects;
        TextView txt_priority;
        public TeacherSecondaryHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.teacherName);
            txt_experience = itemView.findViewById(R.id.teacher_exp);
            txt_subjects = itemView.findViewById(R.id.teacher_subject);
            txt_priority = itemView.findViewById(R.id.teacher_priority);
        }
    }
}
