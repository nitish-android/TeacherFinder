package com.tanzible.teacherfinder.lerningActivity.teacherPrimary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.tanzible.teacherfinder.R;

public class TeacherPrimaryAdapter extends FirestoreRecyclerAdapter<TeacherPrimaryModel,TeacherPrimaryAdapter.TeacherPrimaryHolder> {


    public TeacherPrimaryAdapter(@NonNull FirestoreRecyclerOptions<TeacherPrimaryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherPrimaryHolder teacherPrimaryHolder, int i, @NonNull TeacherPrimaryModel teacherPrimaryModel) {

        teacherPrimaryHolder.txt_name.setText(teacherPrimaryModel.getName());
        teacherPrimaryHolder.txt_experience.setText(teacherPrimaryModel.getExperience());
        teacherPrimaryHolder.txt_subjects.setText(teacherPrimaryModel.getSubject());
        teacherPrimaryHolder.txt_priority.setText(String.valueOf(teacherPrimaryModel.getPriority()));
    }

    @NonNull
    @Override
    public TeacherPrimaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);
        return new TeacherPrimaryHolder(view);

    }

    class TeacherPrimaryHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        TextView txt_experience;
        TextView txt_subjects;
        TextView txt_priority;
        public TeacherPrimaryHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.teacherName);
            txt_experience = itemView.findViewById(R.id.teacher_exp);
            txt_subjects = itemView.findViewById(R.id.teacher_subject);
            txt_priority = itemView.findViewById(R.id.teacher_priority);

        }
    }
}
