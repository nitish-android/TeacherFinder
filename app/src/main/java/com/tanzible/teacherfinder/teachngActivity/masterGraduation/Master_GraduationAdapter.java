package com.tanzible.teacherfinder.teachngActivity.masterGraduation;

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
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryAdapter;
import com.tanzible.teacherfinder.teachngActivity.masterPrimary.Master_PrimaryDetailActivity;

import java.util.List;

public class Master_GraduationAdapter extends RecyclerView.Adapter<Master_GraduationAdapter.ViewHolder> {

    List<Master_GraduationModal> master_graduationModals;

    public Master_GraduationAdapter(List<Master_GraduationModal> master_graduationModals) {
        this.master_graduationModals = master_graduationModals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String image = master_graduationModals.get(position).getStudent_image();
        String name = master_graduationModals.get(position).getName();
        String priority = master_graduationModals.get(position).getPriority();
        String subject = master_graduationModals.get(position).getSubject();
        String student_class = master_graduationModals.get(position).getStudent_class();

        holder.setData(name,student_class,priority,subject,position);
        holder.setProfileImage(image);

    }

    @Override
    public int getItemCount() {
        return master_graduationModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView student_name;
        TextView student_class;
        TextView student_subject;
        TextView student_priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_image);
            student_name = itemView.findViewById(R.id.teacherName);
            student_class = itemView.findViewById(R.id.teacher_exp);
            student_priority = itemView.findViewById(R.id.teacher_priority);
            student_subject = itemView.findViewById(R.id.teacher_subject);
        }

        private  void setProfileImage(String imageUrl){
            if (!imageUrl.equals("null")){
                Glide.with(itemView.getContext()).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.user)).into(imageView);
            }}

        private void setData(final String name, final String student_classes, final String priority, final String subject,final int position){
            student_name.setText(name);
            student_class.setText(student_classes);
            student_subject.setText(subject);
            student_priority.setText(priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != 0) {
                        Intent intent = new Intent(itemView.getContext(), Master_PrimaryDetailActivity.class);
                        intent.putExtra("Name", name);
                        intent.putExtra("Subject", subject);
                        intent.putExtra("Class", student_classes);
                        intent.putExtra("Priority", priority);

                        itemView.getContext().startActivity(intent);
                    }
                }
            });

        }

    }
}
