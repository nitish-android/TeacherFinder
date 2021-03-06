package com.tanzible.teacherfinder.lerningActivity.teacherPrimary;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.LearningPrimaryActivity;
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryAdapter;
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryDetailActivity;

import java.util.List;

public class TeacherPrimaryAdapter extends RecyclerView.Adapter<TeacherPrimaryAdapter.ViewHolder>{

    List<TeacherPrimaryModel> models;

    public TeacherPrimaryAdapter(List<TeacherPrimaryModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String image = models.get(position).getTeacher_image();
        String name = models.get(position).getName();
        String priority = models.get(position).getPriority();
        String subject = models.get(position).getSubject();
        String experience = models.get(position).getExperience();

        holder.setData(name,experience,priority,subject,position);
        holder.setProfileImage(image);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView teacher_name;
        TextView teacher_exp;
        TextView teacher_subject;
        TextView teacher_priority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profile_image);
            teacher_name = itemView.findViewById(R.id.teacherName);
            teacher_exp = itemView.findViewById(R.id.teacher_exp);
            teacher_subject = itemView.findViewById(R.id.teacher_subject);
            teacher_priority = itemView.findViewById(R.id.teacher_priority);

        }
        private  void setProfileImage(String imageUrl){
            if (!imageUrl.equals("null")){
                Glide.with(itemView.getContext()).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.user)).into(imageView);
            }}
        private void setData(final String name, final String experience, final String priority, final String subject,final int position){
            teacher_name.setText(name);
            teacher_exp.setText(experience);
            teacher_subject.setText(subject);
            teacher_priority.setText(priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != 0) {
                        Intent intent = new Intent(itemView.getContext(), TeacherPrimaryDetailActivity.class);
                        intent.putExtra("Name", name);
                        intent.putExtra("Subject", subject);
                        intent.putExtra("Experience", experience);
                        intent.putExtra("Priority", priority);

                        itemView.getContext().startActivity(intent);
                    }
                }
            });

        }

    }
}
