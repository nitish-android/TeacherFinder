package com.tanzible.teacherfinder.lerningActivity.teacherSecondary;


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
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryDetailActivity;

import java.util.List;

public class TeacherSecondaryAdapter extends RecyclerView.Adapter<TeacherSecondaryAdapter.ViewHolder>{

    List<TeacherSecondaryModal> modals;

    public TeacherSecondaryAdapter(List<TeacherSecondaryModal> modals) {
        this.modals = modals;
    }

    @NonNull
    @Override
    public TeacherSecondaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherSecondaryAdapter.ViewHolder holder, int position) {

        String image = modals.get(position).getTeacher_image();
        String name = modals.get(position).getName();
        String priority = modals.get(position).getPriority();
        String subject = modals.get(position).getSubject();
        String experience = modals.get(position).getExperience();

        holder.setData(name,experience,priority,subject,position);
        holder.setProfileImage(image);

    }

    @Override
    public int getItemCount() {
        return modals.size();
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
                        Intent intent = new Intent(itemView.getContext(), TeacherSecondaryDetailActivity.class);
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
