package com.tanzible.teacherfinder.lerningActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tanzible.teacherfinder.R;
import com.tanzible.teacherfinder.lerningActivity.teacherGraduation.TeacherGraduationAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherGraduation.TeacherGraduationModal;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryAdapter;
import com.tanzible.teacherfinder.lerningActivity.teacherPrimary.TeacherPrimaryModel;
import com.tanzible.teacherfinder.lerningActivity.teacherSecondary.TeacherSecondaryModal;

import java.util.ArrayList;
import java.util.List;

public class LearningGraduationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    FirebaseFirestore firestore;
    private TeacherGraduationAdapter adapter;
    List<TeacherGraduationModal> modalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_graduation);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Graduation Education");

        recyclerView = findViewById(R.id.recyclerView);


    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


    modalList = new ArrayList<TeacherGraduationModal>();

    adapter = new TeacherGraduationAdapter(modalList);
        recyclerView.setAdapter(adapter);


    firestore = FirebaseFirestore.getInstance();
        firestore.collection("GraduationLearning").orderBy("priority").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                    modalList.add(new TeacherGraduationModal(documentSnapshot.get("teacher_image").toString(), documentSnapshot.get("name").toString(), documentSnapshot.get("experience").toString()
                            , documentSnapshot.get("subject").toString(), documentSnapshot.get("priority").toString()));

                }
                adapter.notifyDataSetChanged();

            } else {
                String error = task.getException().getMessage();
                Toast.makeText(LearningGraduationActivity.this, error, Toast.LENGTH_SHORT).show();
            }

        }
    });


}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
