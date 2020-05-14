package com.tanzible.teacherfinder.lerningActivity.teacherGraduation;

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

public class TeacherGraduationAdapter extends FirestoreRecyclerAdapter<TeacherGraduationModal, TeacherGraduationAdapter.TeacherGraduationHolder> {



    public TeacherGraduationAdapter(@NonNull FirestoreRecyclerOptions<TeacherGraduationModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherGraduationHolder teacherGraduationHolder, int i, @NonNull TeacherGraduationModal teacherGraduationModal) {
        teacherGraduationHolder.txt_name.setText(teacherGraduationModal.getName());
        teacherGraduationHolder.txt_experience.setText(teacherGraduationModal.getExperience());
        teacherGraduationHolder.txt_subjects.setText(teacherGraduationModal.getSubject());
        teacherGraduationHolder.txt_priority.setText(teacherGraduationModal.getPriority());
    }

    @NonNull
    @Override
    public TeacherGraduationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);
        return new TeacherGraduationHolder(view);
    }

    public class TeacherGraduationHolder extends RecyclerView.ViewHolder {


        TextView txt_name;
        TextView txt_experience;
        TextView txt_subjects;
        TextView txt_priority;

        public TeacherGraduationHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.teacherName);
            txt_experience = itemView.findViewById(R.id.teacher_exp);
            txt_subjects = itemView.findViewById(R.id.teacher_subject);
            txt_priority = itemView.findViewById(R.id.teacher_priority);
        }
    }
}
