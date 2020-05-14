package com.tanzible.teacherfinder.lerningActivity.teacherHigher;

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

public class TeacherHigherAdapter extends FirestoreRecyclerAdapter<TeacherHigherModal, TeacherHigherAdapter.TeacherHigherHolder> {




    public TeacherHigherAdapter(@NonNull FirestoreRecyclerOptions<TeacherHigherModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TeacherHigherHolder teacherHigherHolder, int i, @NonNull TeacherHigherModal teacherHigherModal) {

        teacherHigherHolder.txt_name.setText(teacherHigherModal.getName());
        teacherHigherHolder.txt_experience.setText(teacherHigherModal.getExperience());
        teacherHigherHolder.txt_subjects.setText(teacherHigherModal.getSubject());
        teacherHigherHolder.txt_priority.setText(teacherHigherModal.getPriority());

    }

    @NonNull
    @Override
    public TeacherHigherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items,parent,false);

        return new TeacherHigherHolder(view);
    }

    public class TeacherHigherHolder extends RecyclerView.ViewHolder {


        TextView txt_name;
        TextView txt_experience;
        TextView txt_subjects;
        TextView txt_priority;


        public TeacherHigherHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.teacherName);
            txt_experience = itemView.findViewById(R.id.teacher_exp);
            txt_subjects = itemView.findViewById(R.id.teacher_subject);
            txt_priority = itemView.findViewById(R.id.teacher_priority);
        }
    }
}
